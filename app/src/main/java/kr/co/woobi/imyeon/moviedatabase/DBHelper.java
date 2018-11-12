package kr.co.woobi.imyeon.moviedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyMoives.db";
    private static final int DATABASE_VESION = 2;
    private static final String MOVIE_TABLE_NAME = "moives";
    private static final String MOVIE_COLUMN_ID = "id";
    public static final String MOVIE_COLUMN_NAME = "name";
    public static final String MOVIE_COLUMN_DIRECTOR = "director";
    public static final String MOVIE_COLUMN_YEAR = "year";
    public static final String MOVIE_COLUMN_NATION = "nation";
    public static final String MOVIE_COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table moives " + "(id integer primary key, name text, director text, year text, nation text, rating text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);

    }

    public boolean insertMoive(String name, String director, String year, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("director", director);
        contentValues.put("year", year);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);

        db.insert("movies", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from movies where id=" + id + "", null);
        return cursor;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MOVIE_TABLE_NAME);
        return numRows;
    }

    public boolean updateMovie(Integer id, String name, String director, String year, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("director", director);
        contentValues.put("year", year);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);
        db.update("moives", contentValues, "id=?", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMoive(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("movies", "id=?", new String[]{Integer.toString(id)});
    }

    public ArrayList getAllMovies() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from movies", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
        arrayList.add(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_NAME)));
        cursor.moveToNext();
    }
    return arrayList;
    }
}
