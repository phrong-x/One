package com.kaiqi.love_family.view.image;

import java.io.File;

import android.content.Context;

public class FileCache {

	private File cacheDir;

	private File fileCacheDir;

	private File audioCacheDir;

	private File imageCacheDir;

	private File chatImageCacheDir;

	public File getCacheDir() {
		return cacheDir;
	}

	public File getFileCacheDir() {
		fileCacheDir = new File(cacheDir, "/file/");
		if (!fileCacheDir.exists())
			fileCacheDir.mkdirs();
		return fileCacheDir;
	}

	public File getAudioCacheDir() {
		audioCacheDir = new File(cacheDir, "/audio/");
		if (!audioCacheDir.exists())
			audioCacheDir.mkdirs();
		return audioCacheDir;
	}

	public File getImageCacheDir() {
		imageCacheDir = new File(cacheDir, "/image/");
		if (!imageCacheDir.exists())
			imageCacheDir.mkdirs();
		return imageCacheDir;
	}

	public File getImageCacheDirPath() {
		chatImageCacheDir = new File(cacheDir, "/image/chat/");
		if (!chatImageCacheDir.exists())
			chatImageCacheDir.mkdirs();
		return chatImageCacheDir;
	}

	public FileCache(Context context) {

		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

			cacheDir = new File(Config.FILE_CACHE_DIR, "/cache");
		} else {

			cacheDir = context.getCacheDir();
		}
		if (!cacheDir.exists())
			cacheDir.mkdirs();
	}

	public File getFile(String url) {
		String filename = String.valueOf(url.hashCode());
		File f = new File(cacheDir, filename);
		return f;

	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		for (File f : files)
			f.delete();
	}

}