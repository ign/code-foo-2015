package com.corti.ignapi;


import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class APITest {

    public static void main(String[] args) throws IOException, JSONException {
        parseJson("http://ign-apis.herokuapp.com/videos");
        parseJson("http://ign-apis.herokuapp.com/articles");
    }

    //this will read and convert url into readable JSON 
    private static String getURL(Reader link) throws IOException 
    {
        StringBuilder strBuilder = new StringBuilder();
        int index;
        
        while ((index = link.read()) != -1) {
            strBuilder.append((char) index);
        }
        return strBuilder.toString();
    }

    //parse through JSON data.
    public static JSONObject parseJson(String url) throws IOException, JSONException {
        InputStream instream = new URL(url).openStream();
        String strSub;
        BufferedReader link = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
        String strLink = getURL(link);
        try {
            JSONObject jsonGet = new JSONObject(strLink);
            JSONArray jsonDataArray = jsonGet.getJSONArray("data");
            
      	  //loop through data and pick out key components
            for(int index=0; index < jsonDataArray.length(); index++){
            	String aDescription;
            	String vDescription;


              	JSONObject jsonData = jsonDataArray.getJSONObject(index).getJSONObject("metadata");
              	
               	if(jsonData.has("headline")){
            		aDescription = ("\nArticle-\"" + jsonData.getString("headline") +"\"");
            		System.out.println(aDescription);
           	}
               	if(jsonData.has("subHeadline")){
               		strSub = (jsonData.getString("subHeadline") );
            		System.out.println(strSub);
           	}
               	if(jsonData.has("name")){
                		vDescription = ("\nVideo-" + jsonData.getString("name") + 
                			"\n" + "link: " + jsonData.getString("url"));
                		System.out.println(vDescription);
               	}
            }
        }finally { 
            	instream.close();
        }
		return null;
    }
}
