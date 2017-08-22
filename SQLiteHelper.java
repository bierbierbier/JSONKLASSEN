package sebastian.allianz.de.AllianzNavigation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Stefan on 12.05.17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = SQLiteHelper.class.getSimpleName();

    //Allgemeine Namensdefinition
    public static final String DB_NAME               = "mapgo.db";
    public static final String BACKUP_DB_NAME        = "backupMapgo.db";
    public static final int DB_VERSION               = 1;



    //Namensdefinitionen der Tabelle für Maps
    public static final String TABLE_MAP                = "maps";
    public static final String MAP_COLUMN_MAP_ID        = "map_id";
    public static final String MAP_COLUMN_MAP_NAME      = "map_name";

    //Namensdefinitionen der Tabelle für Positionen
    public static final String TABLE_POS                = "position";
    public static final String POS_COLUMN_POS_ID        = "pos_id";
    public static final String POS_COLUMN_MAP_ID        = "map_id";
    public static final String POS_COLUMN_X             = "x";
    public static final String POS_COLUMN_Y             = "y";
    public static final String POS_COLUMN_POS_TS        = "pos_timestamp";

    //Namensdefinitionen der Tabelle für POIs
    public static final String TABLE_POI                = "POI";
    public static final String POI_COLUMN_POI_ID        = "poi_id";
    public static final String POI_COLUMN_MAP_ID        = "map_id";
    public static final String POI_COLUMN_X             = "x";
    public static final String POI_COLUMN_Y             = "y";
    public static final String POI_COLUMN_IMG_SRC       = "image_src";
    public static final String POI_COLUMN_ROOM_NAME     = "roomName";
    public static final String POI_COLUMN_POI_TS        = "poi_timestamp";

    //Namensdefinitionen der Tabelle für Wifi
    public static final String TABLE_WIFI               = "wifi";
    public static final String WIFI_COLUMN_WIFI_ID      = "wifi_id";
    public static final String WIFI_COLUMN_POS_ID       = "pos_id";
    public static final String WIFI_COLUMN_MAC          = "mac_adress";
    public static final String WIFI_COLUMN_LVL          = "lvl";

    //Namensdefinitionen der Tabelle für Room
    public static final String TABLE_ROOM               = "room";
    public static final String ROOM_COLUMN_MAP_ID       = "map_id";
    public static final String ROOM_COlUMN_ROOM_ID      = "room_id";
    public static final String ROOM_COLUMN_ROOM_NAME    = "room_name";

    //Namensdefinitionen der Tabelle für neue Positionen
    public static final String TABLE_NEWPOS             = "neuePosition";
    public static final String NEWPOS_COLUMN_POS_ID     = "pos_id";
    public static final String NEWPOS_COLUMN_MAP_ID     = "map_id";
    public static final String NEWPOS_COLUMN_X          = "x";
    public static final String NEWPOS_COLUMN_Y          = "y";

    //Namensdefinition der Tabelle für neue POIs
    public static final String TABLE_NEWPOI             = "neuePOI";
    public static final String NEWPOI_COLUMN_POI_ID     = "poi_id";
    public static final String NEWPOI_COLUMN_MAP_ID     = "map_id";
    public static final String NEWPOI_COLUMN_X          = "x";
    public static final String NEWPOI_COLUMN_Y          = "y";
    public static final String NEWPOI_COLUMN_IMG_SRC    = "image_src";
    public static final String NEWPOI_COLUMN_ROOM_NAME  = "roomName";

    //Namensdefinition der Tabelle für neue Wifi
    public static final String TABLE_NEWWIFI            = "neueWifi";
    public static final String NEWWIFI_COLUMN_WIFI_ID   = "wifi_id";
    public static final String NEWWIFI_COLUMN_MAC       = "mac_adress";
    public static final String NEWWIFI_COLUMN_LVL       = "lvl";
    public static final String NEWWIFI_COLUMN_POS_ID    = "pos_id";



    //Statements zur Erzeugung der Datenpank für Maps
    public static final String SQL_CREATE_MAP =
            "CREATE TABLE " + TABLE_MAP +
                    "(" + MAP_COLUMN_MAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MAP_COLUMN_MAP_NAME + " TEXT UNIQUE NOT NULL);";

    //Statements zur Erzeugung der Datenpank für Positionen
    public static final String SQL_CREATE_POS =
            "CREATE TABLE " + TABLE_POS +
                    "(" + POS_COLUMN_POS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    POS_COLUMN_MAP_ID + " TEXT NOT NULL REFERENCES maps(map_id), " +
                    POS_COLUMN_X + " INTEGER NOT NULL , " +
                    POS_COLUMN_Y + " INTEGER NOT NULL , " +
                    POS_COLUMN_POS_TS + " TEXT);";

    //Statements zur Erzeugung der Datenpank für POIs
    public static final String SQL_CREATE_POI =
            "CREATE TABLE " + TABLE_POI +
                    "(" + POI_COLUMN_POI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    POI_COLUMN_MAP_ID + " TEXT NOT NULL REFERENCES maps(map_id), " +
                    POI_COLUMN_X + " INTEGER NOT NULL, " +
                    POI_COLUMN_Y + " INTEGER NOT NULL, " +
                    POI_COLUMN_IMG_SRC + " TEXT NOT NULL," +
                    POI_COLUMN_POI_TS + " TEXT, " +
                    POI_COLUMN_ROOM_NAME + " TEXT UNIQUE);";

    //Statements zur Erzeugung der Datenpank für Room
    public static final String SQL_CREATE_ROOM =
            "CREATE TABLE " + TABLE_ROOM +
                    "(" + ROOM_COlUMN_ROOM_ID + " INTEGER PRIMARY KEY, " +
                    ROOM_COLUMN_ROOM_NAME + " TEXT NOT NULL, " +
                    ROOM_COLUMN_MAP_ID + " INTEGER REFERENCES maps('map_id')";

    //Statements zur Erzeugung der Datenpank für Wifi
    public static final String SQL_CREATE_WIFI =
            "CREATE TABLE " + TABLE_WIFI +
                    "(" + WIFI_COLUMN_WIFI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WIFI_COLUMN_MAC + " TEXT NOT NULL, " +
                    WIFI_COLUMN_LVL + " TEXT NOT NULL," +
                    WIFI_COLUMN_POS_ID + " INTEGER NOT NULL REFERENCES position('pos_id'));";

    //Statement zur Erzeugung der Datenbank für neue Positionen
    public static final String SQL_CREATE_NEWPOS =
            "CREATE TABLE " + TABLE_NEWPOS +
                    "(" + NEWPOS_COLUMN_POS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NEWPOS_COLUMN_MAP_ID + " TEXT NOT NULL REFERENCES maps('map_id'), " +
                    NEWPOS_COLUMN_X + " INTEGER NOT NULL, " +
                    NEWPOS_COLUMN_Y + " INTEGER NOT NULL);";

    //Statements zur Erzeugung der Datenbank für neue POIS
    public static final String SQL_CREATE_NEWPOI =
            "CREATE TABLE " + TABLE_NEWPOI +
                    "(" + NEWPOI_COLUMN_POI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NEWPOI_COLUMN_MAP_ID + " TEXT NOT NULL REFERENCES maps(map_id), " +
                    NEWPOI_COLUMN_X + " INTEGER NOT NULL, " +
                    NEWPOI_COLUMN_Y + " INTEGER NOT NULL, " +
                    NEWPOI_COLUMN_IMG_SRC + " TEXT NOT NULL, " +
                    NEWPOI_COLUMN_ROOM_NAME + " TEXT UNIQUE);";

    //Statements zur Erzeugung der Datenpank für Wifi
    public static final String SQL_CREATE_NEWWIFI =
            "CREATE TABLE " + TABLE_NEWWIFI +
                    "(" + NEWWIFI_COLUMN_WIFI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NEWWIFI_COLUMN_MAC + " TEXT NOT NULL, " +
                    NEWWIFI_COLUMN_LVL + " TEXT NOT NULL," +
                    NEWWIFI_COLUMN_POS_ID + " INTEGER NOT NULL REFERENCES neuePosition('pos_id'));";



    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper created Database: " + getDatabaseName());
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //Versuch, die Tabellen anzulegen
        try {
            //Anlegen der Tablle MAP
            db.execSQL(SQL_CREATE_MAP);
            Log.d(LOG_TAG, "Created table: " + TABLE_MAP + " with SQL: " + SQL_CREATE_MAP );

            //Anlegen der Tablle POS
            db.execSQL(SQL_CREATE_POS);
            Log.d(LOG_TAG, "Created table: " + TABLE_POS + " with SQL: " + SQL_CREATE_POS );

            //Anlegen der Tablle POI
            db.execSQL(SQL_CREATE_POI);
            Log.d(LOG_TAG, "Created table: " + TABLE_POI + " with SQL: " + SQL_CREATE_POI );

            //Anlegen der Tablle ROOM
            db.execSQL(SQL_CREATE_ROOM);
            Log.d(LOG_TAG, "Created table: " + TABLE_ROOM + " with SQL: " + SQL_CREATE_ROOM );

            //Anlegen der Tablle WIFI
            db.execSQL(SQL_CREATE_WIFI);
            Log.d(LOG_TAG, "Created table: " + TABLE_WIFI + " with SQL: " + SQL_CREATE_WIFI );

            //Anlegen der Tablle neue Positionen
            db.execSQL(SQL_CREATE_NEWPOS);
            Log.d(LOG_TAG, "Created table: " + TABLE_NEWPOS + " with SQL: " + SQL_CREATE_NEWPOS );

            //Anlegen der Tablle neue POI
            db.execSQL(SQL_CREATE_NEWPOI);
            Log.d(LOG_TAG, "Created table: " + TABLE_NEWPOI + " with SQL: " + SQL_CREATE_NEWPOI );

            //Anlegen der Tablle NEWWIFI
            db.execSQL(SQL_CREATE_NEWWIFI);
            Log.d(LOG_TAG, "Created table: " + TABLE_NEWWIFI + " with SQL: " + SQL_CREATE_NEWWIFI );
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Error: DB couldn't be created: " + ex.getMessage());
        }
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}