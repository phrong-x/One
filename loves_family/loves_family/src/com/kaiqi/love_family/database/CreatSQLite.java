package com.kaiqi.love_family.database;

import com.kaiqi.love_family.comment.DBComment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 建表：
 * 
 * @author 李鑫 18290545819
 * 
 */
public class CreatSQLite extends SQLiteOpenHelper {
	public final String CREATE_TABLE = DBComment.CREATE_TABLE_HEAD
			+ DBComment.TABLE_NAME + "(" + DBComment.C_ID + DBComment.KEY
			+ DBComment.C_NAME + " text" + DBComment.C_INTRODUCTION + " text"
			+ DBComment.C_STAR + " text" + DBComment.C_PROPERTY + " text"
			+ DBComment.C_TELEPHONE + " text" + DBComment.C_ADDRESS + " text)";

	public CreatSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DBComment.DROP_TABLE + DBComment.TABLE_NAME);
		onCreate(db);
	}

}
