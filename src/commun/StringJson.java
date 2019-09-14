package commun;

import org.json.JSONException;
import org.json.JSONObject;

import entitis.ContentChat;

public class StringJson {
	// Tạo chuỗi array
//	public static JSONObject Content(ContentChat contentChat) {
//		JSONObject jo = new JSONObject();
//		try {
//			jo.put("id", contentChat.getId());
//			jo.put("nameUsr", contentChat.getNameUs());
//			jo.put("content", contentChat.getContent());
//			jo.put("dateTime", contentChat.getDateTime());
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return jo;
//	}
	
	//---------------------------------------------
	public static JSONObject Content(ContentChat contentChat) {
		JSONObject jo = new JSONObject();
//		JSONObject jo2 = new JSONObject();
		try {
			jo.put("id", contentChat.getId());
			jo.put("nameUsr", contentChat.getNameUs());
			jo.put("content", contentChat.getContent());
			jo.put("dateTime", contentChat.getDateTime());

//			jo2.put("idUs", jo);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo;
	}
	
}
