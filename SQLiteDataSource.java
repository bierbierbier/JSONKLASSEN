package sebastian.allianz.de.AllianzNavigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Stefan on 12.05.17.
 */

public class SQLiteDataSource {

    private static final String LOG_TAG = SQLiteDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private String[] columns_map = {
            SQLiteHelper.MAP_COLUMN_MAP_ID,
            SQLiteHelper.MAP_COLUMN_MAP_NAME
    };

    private String[] columns_pos = {
            SQLiteHelper.POS_COLUMN_POS_ID,
            SQLiteHelper.POS_COLUMN_MAP_ID,
            SQLiteHelper.POS_COLUMN_X,
            SQLiteHelper.POS_COLUMN_Y,
            SQLiteHelper.POS_COLUMN_POS_TS
    };

    private String[] columns_poi = {
            SQLiteHelper.POI_COLUMN_POI_ID,
            SQLiteHelper.POI_COLUMN_MAP_ID,
            SQLiteHelper.POI_COLUMN_X,
            SQLiteHelper.POI_COLUMN_Y,
            SQLiteHelper.POI_COLUMN_IMG_SRC,
            SQLiteHelper.POI_COLUMN_ROOM_NAME,
            SQLiteHelper.POI_COLUMN_POI_TS
    };

    private String[] columns_wifi = {
            SQLiteHelper.WIFI_COLUMN_WIFI_ID,
            SQLiteHelper.WIFI_COLUMN_POS_ID,
            SQLiteHelper.WIFI_COLUMN_MAC,
            SQLiteHelper.WIFI_COLUMN_LVL
    };

    private String[] columns_room = {
            SQLiteHelper.ROOM_COLUMN_MAP_ID,
            SQLiteHelper.ROOM_COlUMN_ROOM_ID,
            SQLiteHelper.ROOM_COLUMN_ROOM_NAME
    };

    private String[] columns_newpos = {
            SQLiteHelper.NEWPOS_COLUMN_POS_ID,
            SQLiteHelper.NEWPOS_COLUMN_MAP_ID,
            SQLiteHelper.NEWPOS_COLUMN_X,
            SQLiteHelper.NEWPOS_COLUMN_Y
    };

    private String[] columns_newpoi = {
            SQLiteHelper.NEWPOI_COLUMN_POI_ID,
            SQLiteHelper.NEWPOI_COLUMN_MAP_ID,
            SQLiteHelper.NEWPOI_COLUMN_X,
            SQLiteHelper.NEWPOI_COLUMN_Y,
            SQLiteHelper.NEWPOI_COLUMN_IMG_SRC,
            SQLiteHelper.NEWPOI_COLUMN_ROOM_NAME
    };

    private String[] columns_newwifi = {
            SQLiteHelper.NEWWIFI_COLUMN_WIFI_ID,
            SQLiteHelper.NEWWIFI_COLUMN_MAC,
            SQLiteHelper.NEWWIFI_COLUMN_LVL,
            SQLiteHelper.NEWWIFI_COLUMN_POS_ID
    };



    public SQLiteDataSource(Context context) {
        Log.d(LOG_TAG, "DataSource created DBHelper");
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "DB-Reference received. Path: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "DB closed with dbHelper.");
    }

    /*public int insertPos(String map_id, int x, int y) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.NEWPOS_COLUMN_MAP_ID, map_id);
        values.put(SQLiteHelper.NEWPOS_COLUMN_X, x);
        values.put(SQLiteHelper.NEWPOS_COLUMN_Y, y);

        long insertId = database.insert(SQLiteHelper.TABLE_NEWPOS, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_NEWPOS );

        Cursor cursor = database.query(SQLiteHelper.TABLE_NEWPOS, columns_newpos, SQLiteHelper.NEWPOS_COLUMN_POS_ID + "=" + insertId, null, null, null, null);
        cursor.moveToFirst();
        int indexId = cursor.getColumnIndex(SQLiteHelper.NEWPOS_COLUMN_POS_ID);
        int pos_id = cursor.getInt(indexId);
        cursor.close();

        return pos_id;
    }
    */
    
    public int insertPos(String map_id, int x, int y) { 
         ContentValues values = new ContentValues(); 
         values.put(SQLiteHelper.POS_COLUMN_MAP_ID, map_id); 
         values.put(SQLiteHelper.POS_COLUMN_X, x); 
         values.put(SQLiteHelper.POS_COLUMN_Y, y); 
 
 
         long insertId = database.insert(SQLiteHelper.TABLE_POS, null, values); 
         Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_POS ); 
 
 
         Cursor cursor = database.query(SQLiteHelper.TABLE_POS, columns_pos, SQLiteHelper.POS_COLUMN_POS_ID + "=" + insertId, null, null, null, null); 
        int indexId; 
        int pos_id = -1; 
        if ( cursor != null && cursor.moveToFirst() ) { 
             indexId = cursor.getColumnIndex(SQLiteHelper.POS_COLUMN_POS_ID); 
             pos_id = cursor.getInt(indexId); 
             cursor.close(); 
        } 
 
 
         return pos_id; 
     } 
  
/*
    public void insertPoi(String map_id, int x, int y, String img_src) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.NEWPOI_COLUMN_MAP_ID, map_id);
        values.put(SQLiteHelper.NEWPOI_COLUMN_X, x);
        values.put(SQLiteHelper.NEWPOI_COLUMN_Y, y);
        values.put(SQLiteHelper.NEWPOI_COLUMN_IMG_SRC, img_src);

        database.insert(SQLiteHelper.TABLE_NEWPOI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_NEWPOI );
    }
*/

/*
    public void insertPoi(String map_id, int x, int y, String img_src, String room_name) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.NEWPOI_COLUMN_MAP_ID, map_id);
        values.put(SQLiteHelper.NEWPOI_COLUMN_X, x);
        values.put(SQLiteHelper.NEWPOI_COLUMN_Y, y);
        values.put(SQLiteHelper.NEWPOI_COLUMN_IMG_SRC, img_src);
        values.put(SQLiteHelper.NEWPOI_COLUMN_ROOM_NAME, room_name);

        database.insert(SQLiteHelper.TABLE_NEWPOI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_NEWPOI );
    }
*/

		 public void insertPoi(String map_id, int x, int y, String img_src, String room_name) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.POI_COLUMN_MAP_ID, map_id);
        values.put(SQLiteHelper.POI_COLUMN_X, x);
        values.put(SQLiteHelper.POI_COLUMN_Y, y);
        values.put(SQLiteHelper.POI_COLUMN_IMG_SRC, img_src);
        values.put(SQLiteHelper.POI_COLUMN_ROOM_NAME, room_name);

        database.insert(SQLiteHelper.TABLE_POI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_POI );
    }
    
        public void insertPoi(String map_id, int x, int y, String img_src) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.POI_COLUMN_MAP_ID, map_id);
        values.put(SQLiteHelper.POI_COLUMN_X, x);
        values.put(SQLiteHelper.POI_COLUMN_Y, y);
        values.put(SQLiteHelper.POI_COLUMN_ROOM_NAME, img_src);

        database.insert(SQLiteHelper.TABLE_POI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_POI );
    }
    
    /*
    public void insertWifi(int pos_id, String mac, int lvl) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.NEWWIFI_COLUMN_POS_ID, pos_id);
        values.put(SQLiteHelper.NEWWIFI_COLUMN_MAC, mac);
        values.put(SQLiteHelper.NEWWIFI_COLUMN_LVL, lvl);

        database.insert(SQLiteHelper.TABLE_NEWWIFI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_NEWWIFI );
    }
    
    */
    
     public void insertWifi(int pos_id, String mac, int lvl) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.WIFI_COLUMN_POS_ID, pos_id);
        values.put(SQLiteHelper.WIFI_COLUMN_MAC, mac);
        values.put(SQLiteHelper.WIFI_COLUMN_LVL, lvl);

        database.insert(SQLiteHelper.TABLE_WIFI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_WIFI );
    }

    private POIPosition getPOI(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_POI_ID);
        int idMap = cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_MAP_ID);
        int idX = cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_X);
        int idY = cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_Y);
        int idImgSrc= cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_IMG_SRC);
        int idRoom = cursor.getColumnIndex(SQLiteHelper.POI_COLUMN_ROOM_NAME);

        int pos_id = cursor.getInt(idPos);
        String map_id = cursor.getString(idMap);
        int x = cursor.getInt(idX);
        int y = cursor.getInt(idY);
        String img_src=cursor.getString(idImgSrc);
        String roomName = cursor.getString(idRoom);
        int classid=0;
        switch(img_src){
            case "koffer": classid=1; break;
            case "ersthelfer": classid=2; break;
            case "sammelpl": classid=3; break;
            case "defi": classid=4; break;
            case "feuerloescher": classid=5; break;
            case "room": classid=6; break;
        }

        POIPosition position = null;
        if (roomName ==  null) {
            position = new POIPosition(pos_id, map_id, x, y, classid);
        } else {
            position = new POIPosition(pos_id, map_id, x, y, classid, roomName);
        }

        return position;
    }

/*
    private POIPosition getnewPOI(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_POI_ID);
        int idMap = cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_MAP_ID);
        int idX = cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_X);
        int idY = cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_Y);
        int idImgSrc= cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_IMG_SRC);
        int idRoom = cursor.getColumnIndex(SQLiteHelper.NEWPOI_COLUMN_ROOM_NAME);

        int pos_id = cursor.getInt(idPos);
        String map_id = cursor.getString(idMap);
        int x = cursor.getInt(idX);
        int y = cursor.getInt(idY);
        String img_src=cursor.getString(idImgSrc);
        String roomName = cursor.getString(idRoom);
        int classid=0;
        switch(img_src){
            case "koffer": classid=1; break;
            case "ersthelfer": classid=2; break;
            case "sammelpl": classid=3; break;
            case "defi": classid=4; break;
            case "feuerloescher": classid=5; break;
            case "room": classid=6; break;
        }

        POIPosition position = null;
        if (roomName ==  null) {
            position = new POIPosition(pos_id, map_id, x, y, classid);
        } else {
            position = new POIPosition(pos_id, map_id, x, y, classid, roomName);
        }

        return position;
    }
*/

    private Position getPosition(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.POS_COLUMN_POS_ID);
        int idMap = cursor.getColumnIndex(SQLiteHelper.POS_COLUMN_MAP_ID);
        int idX = cursor.getColumnIndex(SQLiteHelper.POS_COLUMN_X);
        int idY = cursor.getColumnIndex(SQLiteHelper.POS_COLUMN_Y);

        int pos_id = cursor.getInt(idPos);
        String map_id = cursor.getString(idMap);
        int x = cursor.getInt(idX);
        int y = cursor.getInt(idY);

        Position position = new Position(pos_id, map_id, x, y);

        return position;
    }

/*
    private Position getnewPosition(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.NEWPOS_COLUMN_POS_ID);
        int idMap = cursor.getColumnIndex(SQLiteHelper.NEWPOS_COLUMN_MAP_ID);
        int idX = cursor.getColumnIndex(SQLiteHelper.NEWPOS_COLUMN_X);
        int idY = cursor.getColumnIndex(SQLiteHelper.NEWPOS_COLUMN_Y);

        int pos_id = cursor.getInt(idPos);
        String map_id = cursor.getString(idMap);
        int x = cursor.getInt(idX);
        int y = cursor.getInt(idY);

        Position position = new Position(pos_id, map_id, x, y);

        return position;
    }
    */
    private SSIDLevelPair getWifi(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.WIFI_COLUMN_POS_ID);
        int idMac = cursor.getColumnIndex(SQLiteHelper.WIFI_COLUMN_MAC);
        int idLvl = cursor.getColumnIndex(SQLiteHelper.WIFI_COLUMN_LVL);

        int pos_id = cursor.getInt(idPos);
        String mac_id = cursor.getString(idMac);
        int lvl = cursor.getInt(idLvl);

        SSIDLevelPair ssidLevelPair = new SSIDLevelPair(pos_id, mac_id, lvl);

        return ssidLevelPair;
    }
    

/*
    private SSIDLevelPair getnewWifi(Cursor cursor) {
        int idPos = cursor.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_POS_ID);
        int idMac = cursor.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_MAC);
        int idLvl = cursor.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_LVL);

        int pos_id = cursor.getInt(idPos);
        String mac_id = cursor.getString(idMac);
        int lvl = cursor.getInt(idLvl);

        SSIDLevelPair ssidLevelPair = new SSIDLevelPair(pos_id, mac_id, lvl);

        return ssidLevelPair;
    }
*/
    public List<CoordWFP> getAllcoordWFP() {
        List<CoordWFP> l_coordWFP = new ArrayList<>();

        //Aufrufen der mit dem Server synchronen Daten
        Cursor cursor_pos = database.query(SQLiteHelper.TABLE_POS,
                columns_pos, null, null, null, null, null);

        cursor_pos.moveToFirst();
        Cursor cursor_wifi = database.query(SQLiteHelper.TABLE_WIFI,
                columns_wifi, null, null, null, null, null);
        cursor_wifi.moveToFirst();
        CoordWFP coordWFP;

        while(!cursor_pos.isAfterLast()) {
            Position position = getPosition(cursor_pos);
            List<SSIDLevelPair> ssidLevelPairs = new ArrayList<SSIDLevelPair>();
            SSIDLevelPair ssidLevelPair = null;
            do {
                ssidLevelPair = getWifi(cursor_wifi);
                if (ssidLevelPair.pos_id == position.pos_id) {
                    Log.d(LOG_TAG, "ID: " + ssidLevelPair.pos_id + ", Inhalt: " + ssidLevelPair.toString());
                    ssidLevelPairs.add(ssidLevelPair);
                }
            } while (ssidLevelPair.pos_id == position.pos_id && cursor_wifi.moveToNext());
            MyScanResult myScanResult = new MyScanResult(ssidLevelPairs, 0);
            coordWFP = new CoordWFP(position, myScanResult);
            l_coordWFP.add(coordWFP);
            cursor_pos.moveToNext();
        }
        cursor_wifi.close();
        cursor_pos.close();

				return l_coordWFP;
 /*       //Aufrufen der mit dem Server nicht synchronen Daten
        Cursor cursor_newpos = database.query(SQLiteHelper.TABLE_NEWPOS,
                columns_newpos, null, null, null, null, null);

        cursor_newpos.moveToFirst();
        Cursor cursor_newwifi = database.query(SQLiteHelper.TABLE_NEWWIFI,
                columns_newwifi, null, null, null, null, null);
        cursor_newwifi.moveToFirst();
        CoordWFP newcoordWFP;

        while(!cursor_newpos.isAfterLast()) {
            Position newposition = getnewPosition(cursor_newpos);
            List<SSIDLevelPair> ssidLevelPairs = new ArrayList<SSIDLevelPair>();
            SSIDLevelPair ssidLevelPair = null;
            do {
                ssidLevelPair = getnewWifi(cursor_newwifi);
                if (ssidLevelPair.pos_id == newposition.pos_id) {
                    Log.d(LOG_TAG, "ID: " + ssidLevelPair.pos_id + ", Inhalt: " + ssidLevelPair.toString());
                    ssidLevelPairs.add(ssidLevelPair);
                }
            } while (ssidLevelPair.pos_id == newposition.pos_id && cursor_newwifi.moveToNext());
            MyScanResult myScanResult = new MyScanResult(ssidLevelPairs, 0);
            newcoordWFP = new CoordWFP(newposition, myScanResult);
            l_coordWFP.add(newcoordWFP);
            cursor_newpos.moveToNext();
        }
        cursor_newwifi.close();
        cursor_newpos.close();

        return l_coordWFP;
        */
    }

    public List<POI> getAllPois(String mapid){
        List<POI> l_poi = new ArrayList<>();

        //Aufrufen der mit dem Server synchronen Daten
        String s =SQLiteHelper.POI_COLUMN_MAP_ID+"='"+mapid+"'";
        Cursor cursorPoi = database.query(SQLiteHelper.TABLE_POI,
                columns_poi, s, null, null, null, null);

        cursorPoi.moveToFirst();

        POI poi;

        while(!cursorPoi.isAfterLast()) {
            POIPosition pos = getPOI(cursorPoi);
            poi = null;
           if (pos.roomName == null) {
                poi = new POI(pos.map_id, pos.class_id, pos.x, pos.y, -1, -1, "");
            } else {
                poi = new POI(pos.map_id, pos.class_id, pos.x, pos.y, -1, -1, "", pos.roomName);
            }
            l_poi.add(poi);
            cursorPoi.moveToNext();
        }
        cursorPoi.close();

				return l_poi;
				/*
        //Aufrufen der mit dem Server nicht synchronen Daten
        String news =SQLiteHelper.NEWPOI_COLUMN_MAP_ID+"='"+mapid+"'";
        Cursor cursornewPoi = database.query(SQLiteHelper.TABLE_NEWPOI,
                columns_newpoi, news, null, null, null, null);

        cursornewPoi.moveToFirst();

        POI newpoi;

        while(!cursornewPoi.isAfterLast()) {
            POIPosition newpos = getnewPOI(cursornewPoi);
            newpoi = null;
            if (newpos.roomName == null) {
                newpoi = new POI(newpos.map_id, newpos.class_id, newpos.x, newpos.y, -1, -1, "");
            } else {
                newpoi = new POI(newpos.map_id, newpos.class_id, newpos.x, newpos.y, -1, -1, "", newpos.roomName);
            }
            l_poi.add(newpoi);
            cursornewPoi.moveToNext();
        }
        cursornewPoi.close();
        return l_poi;*/
    }

    public List<POI> searchRoom(String roomName){
        List<POI> l_poi2 = new ArrayList<>();
        String s =SQLiteHelper.POI_COLUMN_ROOM_NAME+"='"+roomName+"'";
        Cursor cursorPoi = database.query(SQLiteHelper.TABLE_POI,
                columns_poi, s, null, null, null, null);
				//WICHTIG: ROOM NOCH AUS POI RAUSNEHMEN
        cursorPoi.moveToFirst();

        POI poi;

        while(!cursorPoi.isAfterLast()) {
            POIPosition pos = getPOI(cursorPoi);
            poi = null;
            if (pos.roomName.equals(roomName)) {
                poi = new POI(pos.map_id, pos.class_id, pos.x, pos.y, -1, -1, "", pos.roomName);
            }
            l_poi2.add(poi);
            cursorPoi.moveToNext();
        }
        cursorPoi.close();
        return l_poi2;
    }


    //Methode wird nur solange gebraucht, bis Serververbindung besteht
   /* private void simulateServer(){
        Cursor cursor_pos = database.query(SQLiteHelper.TABLE_NEWPOS, columns_newpos, null, null, null, null, null);
        cursor_pos.moveToFirst();

        while(!cursor_pos.isAfterLast()){
            Position pos = this.getPosition(cursor_pos);
            this.insertOldPos(pos);

        }

        Cursor cursor_poi = database.query(SQLiteHelper.TABLE_NEWPOI, columns_newpoi, null, null, null, null, null);
        cursor_poi.moveToFirst();

        while(!cursor_poi.isAfterLast()){
            POIPosition poi = this.getPOI(cursor_poi);
            this.insertOldPoi(poi);
        }

        this.insertWIFI();
    }

*/
/*
    private void insertOldPos(Position pos){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.POS_COLUMN_MAP_ID, pos.map_id);
        values.put(SQLiteHelper.POS_COLUMN_X, pos.x);
        values.put(SQLiteHelper.POS_COLUMN_Y, pos.y);

        long insertId = database.insert(SQLiteHelper.TABLE_POS, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_POS );
    }


    private void insertOldPoi(POIPosition poi){
        String img_src = "";
        switch(poi.class_id){
            case 1: img_src ="koffer"; break;
            case 2: img_src ="ersthelfer"; break;
            case 3: img_src ="sammelpl"; break;
            case 4: img_src ="defi"; break;
            case 5: img_src ="feuerloescher"; break;
            case 6: img_src ="room"; break;
        }
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.POI_COLUMN_MAP_ID, poi.map_id);
        values.put(SQLiteHelper.POI_COLUMN_X, poi.x);
        values.put(SQLiteHelper.POI_COLUMN_Y, poi.y);
        values.put(SQLiteHelper.POI_COLUMN_IMG_SRC, img_src);
        values.put(SQLiteHelper.POI_COLUMN_ROOM_NAME, poi.roomName);

        database.insert(SQLiteHelper.TABLE_POI, null, values);
        Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_POI );
    }


    private void insertWIFI(){
        Cursor cursor_wifi = database.query(SQLiteHelper.TABLE_NEWWIFI, columns_newwifi, null, null, null, null, null);
        cursor_wifi.moveToFirst();

        while(!cursor_wifi.isAfterLast()){
            int idWifi = cursor_wifi.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_WIFI_ID);
            int idPos = cursor_wifi.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_POS_ID);
            int idMac = cursor_wifi.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_MAC);
            int idLvl = cursor_wifi.getColumnIndex(SQLiteHelper.NEWWIFI_COLUMN_LVL);

            int pos_id = cursor_wifi.getInt(idPos);
            String mac_id = cursor_wifi.getString(idMac);
            int lvl = cursor_wifi.getInt(idLvl);

            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.WIFI_COLUMN_WIFI_ID, cursor_wifi.getInt(idWifi));
            values.put(SQLiteHelper.WIFI_COLUMN_POS_ID, cursor_wifi.getInt(idPos));
            values.put(SQLiteHelper.WIFI_COLUMN_MAC, cursor_wifi.getString(idMac));
            values.put(SQLiteHelper.WIFI_COLUMN_LVL, cursor_wifi.getInt(idLvl));

            database.insert(SQLiteHelper.TABLE_WIFI, null, values);
            Log.d(LOG_TAG, "Succesfully insert values: " + values.toString() + " into " + SQLiteHelper.TABLE_WIFI );
        }
    }
    */
}