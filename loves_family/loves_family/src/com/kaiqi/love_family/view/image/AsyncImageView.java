package com.kaiqi.love_family.view.image;

import java.io.FileOutputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.view.image.ImageRequest.ImageRequestCallback;

//com.android.zhangyi.sina.view.AsyncImageView
public class AsyncImageView extends ImageView implements ImageRequestCallback {

	private static final String LOG_TAG = AsyncImageView.class.getSimpleName();

	public static interface OnImageViewLoadListener {

		void onLoadingStarted(AsyncImageView imageView);

		void onLoadingEnded(AsyncImageView imageView, Bitmap image);

		void onLoadingFailed(AsyncImageView imageView, Throwable throwable);
	}

	private static final int IMAGE_SOURCE_UNKNOWN = -1;

	private static final int IMAGE_SOURCE_RESOURCE = 0;

	private static final int IMAGE_SOURCE_DRAWABLE = 1;

	private static final int IMAGE_SOURCE_BITMAP = 2;

	private int mImageSource;

	private Bitmap mDefaultBitmap;

	private Drawable mDefaultDrawable;

	private int mDefaultResId;

	private String mUrl;

	private ImageRequest mRequest;

	private boolean mPaused;

	private Bitmap mBitmap;

	private OnImageViewLoadListener mOnImageViewLoadListener;

	private ImageProcessor mImageProcessor;

	private BitmapFactory.Options mOptions;

	private Paint paint;

	private int roundWidth = 5;

	private int roundHeight = 5;

	public AsyncImageView(Context context) {
		this(context, null);
	}

	public AsyncImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AsyncImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeDefaultValues();

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AsyncImageView, defStyle, 0);

		Drawable d = a.getDrawable(R.styleable.AsyncImageView_defaultSrc);
		if (d != null) {
		setDefaultImageDrawable(d);
		}

		final int inDensity = a.getInt(R.styleable.AsyncImageView_inDensity, -1);
		if (inDensity != -1) {
			setInDensity(inDensity);
		}

		setUrl(a.getString(R.styleable.AsyncImageView_url));
		a.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Path path = new Path();
		int w = this.getWidth();
		int h = this.getHeight();
		path.addRoundRect(new RectF(0, 0, w, h), 10.0f, 10.0f, Path.Direction.CW);
		canvas.clipPath(path);
		canvas.drawColor(Color.TRANSPARENT);
		super.onDraw(canvas);
	}

	private void initializeDefaultValues() {
		mImageSource = IMAGE_SOURCE_UNKNOWN;
		mPaused = false;
	}

	public boolean isLoading() {
		return mRequest != null;
	}

	public boolean isLoaded() {
		return mRequest == null && mBitmap != null;
	}

	public void setPaused(boolean paused) {
		if (mPaused != paused) {
			mPaused = paused;
			if (!paused) {
				reload();
			}
		}
	}

	public void setInDensity(int inDensity) {
		if (mOptions == null) {
			mOptions = new BitmapFactory.Options();
			mOptions.inDither = true;
			mOptions.inScaled = true;
			mOptions.inTargetDensity = getContext().getResources().getDisplayMetrics().densityDpi;
		}

		mOptions.inDensity = inDensity;
	}

	public void setOptions(BitmapFactory.Options options) {
		mOptions = options;
	}

	public void reload() {
		reload(false);
	}

	public void reload(boolean force) {
		if (mRequest == null && mUrl != null) {

			mBitmap = null;
			if (!force) {
				mBitmap = GDUtils.getImageCache(getContext()).get(mUrl);
			}

			if (mBitmap != null) {
				setImageBitmap(mBitmap);
				return;
			}

			if (Config.GD_INFO_LOGS_ENABLED) {
				Log.i(LOG_TAG, "Cache miss. Starting to load the image at the given URL");
			}

			setDefaultImage();
			mRequest = new ImageRequest(mUrl, this, mImageProcessor, mOptions);
			mRequest.load(getContext());
		}
	}

	/**
	 * Force the loading to be stopped.
	 */
	public void stopLoading() {
		if (mRequest != null) {
			mRequest.cancel();
			mRequest = null;
		}
	}

	public void setOnImageViewLoadListener(OnImageViewLoadListener listener) {
		mOnImageViewLoadListener = listener;
	}

	public void setUrl(String url) {

		if (mBitmap != null && url != null && url.equals(mUrl)) {
			setImageBitmap(mBitmap);
			return;
		}

		stopLoading();
		mUrl = url;

		if (TextUtils.isEmpty(mUrl)) {
			mBitmap = null;
			setDefaultImage();
		} else {
			if (!mPaused) {
				reload();
			} else {
				mBitmap = GDUtils.getImageCache(getContext()).get(mUrl);
				if (mBitmap != null) {
					Log.i("456456456456456456456", "000000") ;
					 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						 Log.i("456456456456456456456", "") ;
					 CompressFormat format= Bitmap.CompressFormat.JPEG ; 
					 try {
						OutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()) ;
						mBitmap.compress(format, 100, out) ;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
					setImageBitmap(mBitmap); 
					return;
				} else {
					setDefaultImage();
				}
			}
		}
	}

	public void setDefaultImageBitmap(Bitmap bitmap) {
		mImageSource = IMAGE_SOURCE_BITMAP;
		mDefaultBitmap = bitmap;
		setDefaultImage();
	}

	public void setDefaultImageDrawable(Drawable drawable) {
		mImageSource = IMAGE_SOURCE_DRAWABLE;
		mDefaultDrawable = drawable;
		setDefaultImage();
	}

	public void setDefaultImageResource(int resId) {
		mImageSource = IMAGE_SOURCE_RESOURCE;
		mDefaultResId = resId;
		setDefaultImage();
	}

	public void setImageProcessor(ImageProcessor imageProcessor) {
		mImageProcessor = imageProcessor;
	}

	private void setDefaultImage() {
		if (mBitmap == null) {
			switch (mImageSource) {
			case IMAGE_SOURCE_BITMAP:
				setImageBitmap(mDefaultBitmap);
				break;
			case IMAGE_SOURCE_DRAWABLE:
				setImageDrawable(mDefaultDrawable);
				break;
			case IMAGE_SOURCE_RESOURCE:
				setImageResource(mDefaultResId);
				break;
			default:
				setImageDrawable(null);
				break;
			}
		}
	}

	static class SavedState extends BaseSavedState {

		String url;

		SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			url = in.readString();
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeString(url);
		}

		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		SavedState ss = new SavedState(superState);

		ss.url = mUrl;

		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState ss = (SavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());

		setUrl(ss.url);
	}

	public void onImageRequestStarted(ImageRequest request) {
		if (mOnImageViewLoadListener != null) {
			mOnImageViewLoadListener.onLoadingStarted(this);
		}
	}

	public void onImageRequestFailed(ImageRequest request, Throwable throwable) {
		mRequest = null;
		if (mOnImageViewLoadListener != null) {
			mOnImageViewLoadListener.onLoadingFailed(this, throwable);
		}
	}

	public void onImageRequestEnded(ImageRequest request, Bitmap image) {
		mBitmap = image;
		setImageBitmap(image);
		if (mOnImageViewLoadListener != null) {
			mOnImageViewLoadListener.onLoadingEnded(this, image);
		}
		mRequest = null;
	}

	public void onImageRequestCancelled(ImageRequest request) {
		mRequest = null;
		if (mOnImageViewLoadListener != null) {
			mOnImageViewLoadListener.onLoadingFailed(this, null);
		}
	}

	private void drawLiftUp(Canvas canvas) {
		Path path = new Path();
		path.moveTo(0, roundHeight);
		path.lineTo(0, 0);
		path.lineTo(roundWidth, 0);
		path.arcTo(new RectF(0, 0, roundWidth * 2, roundHeight * 2), -90, -90);
		path.close();
		canvas.drawPath(path, paint);
	}

	private void drawLiftDown(Canvas canvas) {
		Path path = new Path();
		path.moveTo(0, getHeight() - roundHeight);
		path.lineTo(0, getHeight());
		path.lineTo(roundWidth, getHeight());
		path.arcTo(new RectF(0, getHeight() - roundHeight * 2, 0 + roundWidth * 2, getWidth()), 90, 90);
		path.close();
		canvas.drawPath(path, paint);
	}

	private void drawRightDown(Canvas canvas) {
		Path path = new Path();
		path.moveTo(getWidth() - roundWidth, getHeight());
		path.lineTo(getWidth(), getHeight());
		path.lineTo(getWidth(), getHeight() - roundHeight);
		path.arcTo(new RectF(getWidth() - roundWidth * 2, getHeight() - roundHeight * 2, getWidth(), getHeight()), 0, 90);
		path.close();
		canvas.drawPath(path, paint);
	}

	private void drawRightUp(Canvas canvas) {
		Path path = new Path();
		path.moveTo(getWidth(), roundHeight);
		path.lineTo(getWidth(), 0);
		path.lineTo(getWidth() - roundWidth, 0);
		path.arcTo(new RectF(getWidth() - roundWidth * 2, 0, getWidth(), 0 + roundHeight * 2), -90, 90);
		path.close();
		canvas.drawPath(path, paint);
	}
}
