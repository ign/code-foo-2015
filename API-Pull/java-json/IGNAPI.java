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

	public static void parseJSON(String url) throws IOException, JSONException{
	  InputStream is = new URL(url).openStream();
	  BufferedReader link = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String object = getURL(link);
      JSONObject obj = new JSONObject(object);
        System.out.println(obj);
    }

    public static void main(String[] args) throws IOException, JSONException{
    	//parseJSON("http://ign-apis.herokuapp.com/videos");
    	//System.out.println("------------------------------------------------------------");
        parseJSON("http://ign-apis.herokuapp.com/articles");
    }
}