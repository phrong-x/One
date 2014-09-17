package com.kaiqi.love_family.database;

import java.util.ArrayList;
import java.util.List;

import com.kaiqi.love_family.comment.DBComment;
import com.kaiqi.love_family.comment.TableDBComment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库进行操作。
 * 
 * @author 李鑫 18290545819
 * 
 */
public class OperationDataBase {

	/*
	 * 向数据库中插入数据。
	 */
	public static void Insert(SQLiteDatabase db, String name,
			String introduction, String star, String property, String address,
			String telephone) {
		String str = "";
		List<TableDBComment> list = new ArrayList<TableDBComment>();
		list = QuaryDB(db);
		int len = list.size();
		for (int i = 0; i < len; i++) {
			str = list.get(i).getCompany_name();
		}
		if (name.equals(str)) {
			// UpdeteDB(db, name, introduction, star, property, address);//
			// 已经存在。更新。
			// toast用不了。//提示已经存在。
		} else {
			TableDBComment tb = new TableDBComment();
			tb.setStars(star);
			tb.setCompany_name(name);
			tb.setCompany_content(introduction);
			tb.setProperty(property);
			tb.setTelephone(telephone);
			tb.setAddress(address);
			// contenvalues只能存储基本类型的数据，而HashTable却可以存储对象。
			ContentValues values = new ContentValues();
			values.put(DBComment.C_NAME, tb.getCompany_name());
			values.put(DBComment.C_INTRODUCTION, tb.getCompany_content());
			values.put(DBComment.C_STAR, tb.getStars());
			values.put(DBComment.C_PROPERTY, tb.getProperty());
			values.put(DBComment.C_TELEPHONE, tb.getTelephone());
			values.put(DBComment.C_ADDRESS, tb.getAddress());
			db.insert(DBComment.TABLE_NAME, null, values);
		}
	}

	/**
	 * 查询数据库。
	 * 
	 * @param db
	 * @return
	 */
	public static List<TableDBComment> QuaryDB(SQLiteDatabase db) {
		List<TableDBComment> list = new ArrayList<TableDBComment>();
		Cursor cursor = db.rawQuery("select * from " + DBComment.TABLE_NAME,
				null);
		while (cursor.moveToNext()) {
			int index = 0;
			TableDBComment comment = new TableDBComment();
			comment.setId(cursor.getInt(index++));
			comment.setCompany_name(cursor.getString(index++));
			comment.setCompany_content(cursor.getString(index++));
			comment.setStars(cursor.getString(index++));
			comment.setProperty(cursor.getString(index++));
			comment.setAddress(cursor.getString(index++));
			list.add(comment);
		}
		cursor.close();
		return list;

	}


	public static void UpdeteDB(SQLiteDatabase db, String name,
			String introduction, String star, String property, String address) {
		ContentValues values = new ContentValues();
		values.put("_name", name);
		values.put("_star", star);
		values.put("_introduction", introduction);
		values.put("_property", property);
		values.put("_address", address);
		db.update(DBComment.TABLE_NAME, values, DBComment.C_NAME + "=?",
				new String[] { name });
	}
}
