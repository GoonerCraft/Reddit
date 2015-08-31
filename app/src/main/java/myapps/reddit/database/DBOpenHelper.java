package myapps.reddit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 8/21/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;

//    TABLE STRINGS
    private static final String TEXT_TYPE =" text"; //must leave space!! at the front and definition of variable should be in lower case.!
    private static final String INTEGER_TYPE =" integer";
    private static final String COMMA =", ";

///*SQL CREATE TABLE STATEMENT

    private static final String CREATE_POST_TABLE = "CREATE TABLE "
            + DatabaseContract.PostTable.TABLE_NAME + " ("
            + DatabaseContract.PostTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.LINK + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.IMAGELINK + TEXT_TYPE + " )";

//IF TABLE EXISTS DROP IT.
    public static final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.PostTable.TABLE_NAME;

    public DBOpenHelper(Context context){
        super(context, DatabaseContract.DB_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Called only the first time.
        db.execSQL(CREATE_POST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_POST_TABLE);
        onCreate(db);
    }
}
