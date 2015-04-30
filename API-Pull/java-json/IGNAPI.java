import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class IGNAPI{

//convert url into parseable json text
	public static String getURL(Reader r) throws IOException{
		StringBuilder sb = new StringBuilder();
        int n;

		while((n = r.read()) != -1){
			sb.append((char) n);
		}
		return sb.toString();
	}	

	public static JSONObject parseJSON(String url) throws IOException, JSONException{
	  InputStream is = new URL(url).openStream();
	  BufferedReader link = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String object = getURL(link);
      JSONObject obj = new JSONObject(object);

      //Can now get items within data.
      JSONArray array = obj.getJSONArray("data");

      for(int i=0; i < array.length(); i++){
      	String articleDescription;
      	String videoDescription;

      	JSONObject info = array.getJSONObject(i).getJSONObject("metadata");

      	if(info.has("headline")){
      		articleDescription = info.getString("headline");
      		System.out.println(articleDescription);
      	}
      }
  	is.close();
    return obj;
}

    public static void main(String[] args) throws IOException, JSONException{
    	//parseJSON("http://ign-apis.herokuapp.com/videos");
    	//System.out.println("------------------------------------------------------------");
        JSONObject obj = parseJSON("http://ign-apis.herokuapp.com/articles");
        //System.out.println(obj.toString());
        //System.out.println(obj.get("data"));
    }
}