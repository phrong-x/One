/*
 * Copyright (C) 2010 Cyril Mottier (http://www.cyrilmottier.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kaiqi.love_family.view.image;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;


/**
 * An ImageLoader asynchronously loads image from a given url. Client may be
 * notified from the current image loading state using the {@link ImageLoaderCallback}.
 * <p>
 * <em><strong>Note: </strong>You normally don't need to use the {@link ImageLoader}
 * class directly in your application. You'll generally prefer using an
 * {@link ImageRequest} that takes care of the entire loading process.</em>
 * </p>
 * 
 * @author Cyril Mottier
 */
public class ImageLoader {

	private static final String LOG_TAG = ImageLoader.class.getSimpleName();

	private FileCache fileCache;

	/**
	 * @author Cyril Mottier
	 */
	public static interface ImageLoaderCallback {

		void onImageLoadingStarted(ImageLoader loader);

		void onImageLoadingEnded(ImageLoader loader, Bitmap bitmap);

		void onImageLoadingFailed(ImageLoader loader, Throwable exception);
	}

	private static final int ON_START = 0x100;

	private static final int ON_FAIL = 0x101;

	private static final int ON_END = 0x102;

	private static ImageCache sImageCache;

	private static ExecutorService sExecutor;

	private static BitmapFactory.Options sDefaultOptions;

	private static AssetManager sAssetManager;

	public ImageLoader(Context context) {
		if (sImageCache == null) {
			sImageCache = GDUtils.getImageCache(context);
		}
		if (sExecutor == null) {
			sExecutor = GDUtils.getExecutor(context);
		}
		if (sDefaultOptions == null) {
			sDefaultOptions = new BitmapFactory.Options();
			sDefaultOptions.inDither = true;
			sDefaultOptions.inScaled = true;
			sDefaultOptions.inDensity = DisplayMetrics.DENSITY_MEDIUM;
			sDefaultOptions.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
		}
		sAssetManager = context.getAssets();
		fileCache = new FileCache(context);
	}

	public Future<?> loadImage(String url, ImageLoaderCallback callback) {
		return loadImage(url, callback, null);
	}

	public Future<?> loadImage(String url, ImageLoaderCallback callback, ImageProcessor bitmapProcessor) {
		return loadImage(url, callback, bitmapProcessor, null);
	}

	public Future<?> loadImage(String url, ImageLoaderCallback callback, ImageProcessor bitmapProcessor, BitmapFactory.Options options) {
		return sExecutor.submit(new ImageFetcher(url, callback, bitmapProcessor, options));
	}

	private class ImageFetcher implements Runnable {

		private String mUrl;

		private ImageHandler mHandler;

		private ImageProcessor mBitmapProcessor;

		private BitmapFactory.Options mOptions;

		public ImageFetcher(String url, ImageLoaderCallback callback, ImageProcessor bitmapProcessor, BitmapFactory.Options options) {
			mUrl = url;
			mHandler = new ImageHandler(url, callback);
			mBitmapProcessor = bitmapProcessor;
			mOptions = options;
		}

		public void run() {

			Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

			final Handler h = mHandler;
			Bitmap bitmap = null;
			Throwable throwable = null;

			h.sendMessage(Message.obtain(h, ON_START));

			try {

				if (TextUtils.isEmpty(mUrl)) {
					throw new Exception("The given URL cannot be null or empty");
				}

				InputStream inputStream = null;

				if (mUrl.startsWith("file:///android_asset/")) {
					inputStream = sAssetManager.open(mUrl.replaceFirst("file:///android_asset/", ""));
				} else {
					HttpGet httpRequest = new HttpGet(mUrl);
					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
					HttpEntity entity = response.getEntity();
					BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(entity);
					inputStream = bufferedHttpEntity.getContent();
				}

				// TODO Cyril: Use a AndroidHttpClient?
				bitmap = BitmapFactory.decodeStream(inputStream, null, (mOptions == null) ? sDefaultOptions : mOptions);
				// 保存图片到sdcard
				saveImage(mUrl, bitmap);
				if (mBitmapProcessor != null && bitmap != null) {
					final Bitmap processedBitmap = mBitmapProcessor.processImage(bitmap);
					if (processedBitmap != null) {
						bitmap = processedBitmap;
					}
				}

			} catch (Exception e) {
				// An error occured while retrieving the image
				if (Config.GD_ERROR_LOGS_ENABLED) {
				}
				throwable = e;
			}

			if (bitmap == null) {
				if (throwable == null) {
					// Skia returned a null bitmap ... that's usually because
					// the given url wasn't pointing to a valid image
					throwable = new Exception("Skia image decoding failed");
				}
				h.sendMessage(Message.obtain(h, ON_FAIL, throwable));
			} else {
				h.sendMessage(Message.obtain(h, ON_END, bitmap));
			}
		}
	}

	// 保存图片
	private void saveImage(String url, Bitmap bitmap) {
		// try {
		// File f = new File(fileCache.getImageCacheDirPath().getAbsolutePath() + File.separator +
		// url);
		// if (!f.exists()) {
		// f.createNewFile();
		// FileOutputStream fOut = null;
		// try {
		// fOut = new FileOutputStream(f);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		// try {
		// fOut.flush();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// try {
		// fOut.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// } catch (Exception e) {
		// LogHelper.e(LOG_TAG, e.getMessage());
		// }
	}

	private class ImageHandler extends Handler {

		private String mUrl;

		private ImageLoaderCallback mCallback;

		private ImageHandler(String url, ImageLoaderCallback callback) {
			mUrl = url;
			mCallback = callback;
		}

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case ON_START:
				if (mCallback != null) {
					mCallback.onImageLoadingStarted(ImageLoader.this);
				}
				break;

			case ON_FAIL:
				if (mCallback != null) {
					mCallback.onImageLoadingFailed(ImageLoader.this, (Throwable) msg.obj);
				}
				break;

			case ON_END:

				final Bitmap bitmap = (Bitmap) msg.obj;
				sImageCache.put(mUrl, bitmap);

				if (mCallback != null) {
					mCallback.onImageLoadingEnded(ImageLoader.this, bitmap);
				}
				break;

			default:
				super.handleMessage(msg);
				break;
			}
		};
	}

}
