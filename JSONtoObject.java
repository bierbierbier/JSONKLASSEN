package sebastian.allianz.de.AllianzNavigation;

import android.net.wifi.ScanResult;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONtoObject {
	
	public JSONtoObject() {
		
	}
	
	public static List<CoordWFP> readPositions(JSONArray response) {
		 int length = response.length();
		List<CoordWFP> l_cwfp = new LinkedList<>();
		JSONObject dasObjekt;
		Position p;
		for(int i = 0; i < length; i++) {
			 try {
			 		dasObjekt = response.getJSONObject(i);
			 		p.x = (int) dasObjekt.get("x");
			 		p.y = (int) dasObjekt.get("y");
			 		p.map_id = dasObjekt.get("map_id").toString();
			 		JSONArray wifis;
			 		CoordWFP cwfp;
			 		JSONArray wifis = dasObjekt.getJSONArray("wifis");
			 		wifisLength = wifis.length();
			 		if(wifisLength > 0) {
			 			 JSONObject wifiJSON;
			 			 SSIDLevelPair pair;
			 			 List<SSIDLevelPair> Scans = new LinkedList<>();
			 			 for(int s = 0; s < wifis.length(); s++) {
			 			 	wifiJSON = (JSONObject) wifis.get(s);
			 			 	pair = new SSIDLevelPair(wifiJSON.getString("mac_adress"), wifiJSON.getInt("lvl"));
			 			 	Scans.add(pair);
			 			}
			 		}
			 		MyScanResult myScanResult = new MyScanResult(Scans, 48152342);
			 		cWFP = new CoordWFP(p,myScanResult);
			 		l_cwfp.add(cWFP);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int imgSrcToClassId(String imgSrc) {
		int classid;
			switch(imgSrc) {
           case "koffer": classid=1; break; 
           case "ersthelfer": classid=2; break; 
           case "sammelpl": classid=3; break; 
           case "defi": classid=4; break; 
           case "feuerloescher": classid=5; break; 
           case "room": classid=6; break;
           default: classid=0; break;
			}
			return classid;
	}
	
	public static List<POI> readPOIs(JSONArray response) {
		 POI poi;
		 int length = response.length();
		 List<POI> pois = new LinkedList<>();
		 JSONObject dasObjekt;
		 for(int i = 0; i < length; i++) {
		 		try {
		 			poi = readPOI(response.getJSONObject(i));
		 			pois.add(poi);
		 		}catch (Exception e) {
		 			
		 		}
		}
	} 
	
	public static POI readPOI(JSONObject response) {
		POI poi;
		int map_id;
		String map_name;
		String img_src;
		int poi_id;
		int x;
		int y;
		
		map_name = response.get("map_id").toString(); 
		//Wir müssen noch map_name einbauen
		img_src = response.get("img_src");
		poi_id = (int) response.get("poi_id");
		x = (int) response.get("x");
		y = (int) response.get("y");
		int classId = imgSrcToClassId(img_src);
		poi = new POI(map_name, classId, x, y, -1, -1, "");
		return poi;
}