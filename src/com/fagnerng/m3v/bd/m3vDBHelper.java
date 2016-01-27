package com.fagnerng.m3v.bd;

import com.fagnerng.m3v.entity.Tree;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class m3vDBHelper extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "";
	private final static int VERSION_NUMBER = 1;
	private static m3vDBHelper mInstance = null;
	
	private m3vDBHelper(Context context) {
		 super(context, DATABASE_NAME, null, VERSION_NUMBER);
	}
	
	public static m3vDBHelper getInstance(Context context) {
		if (mInstance == null || !mInstance.getWritableDatabase().isOpen()) {
			mInstance = new m3vDBHelper(context);
		}
		
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(M3VContract.SQL_CREATE_TABLE);
		
	}
	
	public long insert(Tree tree) {
		ContentValues cv = new ContentValues();
		cv.put(M3VContract.TABLE.COLUMN.INDENTIFICACAO, tree.getIndentificacao());
		cv.put(M3VContract.TABLE.COLUMN.ESPECIE, tree.getEspecie());
		cv.put(M3VContract.TABLE.COLUMN.LATITUDE, tree.getLatitude());
		cv.put(M3VContract.TABLE.COLUMN.LONGITUDE, tree.getLongitude());
		cv.put(M3VContract.TABLE.COLUMN.FUSTE, tree.getFuste());
		cv.put(M3VContract.TABLE.COLUMN.ALTURA, tree.getAltura());
		cv.put(M3VContract.TABLE.COLUMN.DAP, tree.getDap());
		cv.put(M3VContract.TABLE.COLUMN.CAP, tree.getCap());
		cv.put(M3VContract.TABLE.COLUMN.BIOLOGICA, tree.isBiologica());
		cv.put(M3VContract.TABLE.COLUMN.ANTROPICA, tree.isAntropica());
		cv.put(M3VContract.TABLE.COLUMN.FOTO, tree.getFoto());
		long id = getWritableDatabase().insert(M3VContract.TABLE.NAME, null, cv);
        close();
				
		return id;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (db != null) {
			db.execSQL(M3VContract.SQL_DROP);
			onCreate(db);
		}
		
	}
	
	public class M3VContract {
		public class TABLE {
			public static final String NAME = "M3VTable";
			public static final String COMMA = ", ";
			class COLUMN implements BaseColumns {
				public static final String _ID = "_id";
				public static final String INDENTIFICACAO = "indentificacao";
				public static final String ESPECIE = "especie";
				public static final String LATITUDE = "latitude";
				public static final String LONGITUDE = "longitude";
				public static final String FUSTE = "fuste";
				public static final String ALTURA = "altura";
				public static final String DAP = "dap";
				public static final String CAP = "cap";
				public static final String BIOLOGICA = "biologica";
				public static final String ANTROPICA = "antropica";
				public static final String FOTO = "foto";
				
				
			}
			
			class TYPE implements BaseColumns {
				public static final String _ID = " INTEGER PRIMARY KEY AUTOINCREMENT ";
				public static final String INDENTIFICACAO = " INTEGER PRIMARY KEY ";
				public static final String ESPECIE =  "TEXT ";
				public static final String LATITUDE = " REAL ";
				public static final String LONGITUDE = " REAL ";
				public static final String FUSTE = " REAL ";
				public static final String ALTURA = " REAL ";
				public static final String DAP = " REAL ";
				public static final String CAP = " REAL ";
				public static final String BIOLOGICA = " INTEGER ";
				public static final String ANTROPICA = " INTEGER ";
				public static final String FOTO = "TEXT";
			}
		}
		
		public static final String SQL_CREATE_TABLE = "CREATE TABLE "+
				TABLE.NAME +" ( " +
				TABLE.COLUMN._ID + TABLE.TYPE._ID + TABLE.COMMA +
				TABLE.COLUMN.INDENTIFICACAO + TABLE.TYPE.INDENTIFICACAO + TABLE.COMMA + 
				TABLE.COLUMN.ESPECIE + TABLE.TYPE.ESPECIE + TABLE.COMMA + 
				TABLE.COLUMN.LATITUDE + TABLE.TYPE.LATITUDE + TABLE.COMMA + 
				TABLE.COLUMN.LONGITUDE + TABLE.TYPE.LONGITUDE + TABLE.COMMA + 
				TABLE.COLUMN.FUSTE + TABLE.TYPE.FUSTE + TABLE.COMMA + 
				TABLE.COLUMN.ALTURA + TABLE.TYPE.ALTURA + TABLE.COMMA +
				TABLE.COLUMN.DAP + TABLE.TYPE.DAP + TABLE.COMMA + 
				TABLE.COLUMN.CAP + TABLE.TYPE.CAP + TABLE.COMMA + 
				TABLE.COLUMN.BIOLOGICA + TABLE.TYPE.BIOLOGICA + TABLE.COMMA + 
				TABLE.COLUMN.ANTROPICA + TABLE.TYPE.ANTROPICA + TABLE.COMMA +
				TABLE.COLUMN.FOTO + TABLE.TYPE.FOTO + ")";
			
		public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE.NAME;
	}
}
