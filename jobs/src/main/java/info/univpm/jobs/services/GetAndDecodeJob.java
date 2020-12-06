package info.univpm.jobs.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.el.parser.ParseException; //mai usato

//TOGLIERE MAIN
public class GetAndDecodeJob {

	public static void main(String[] args) {
	String url = "https://jobs.github.com/positions.json";
	
	try {
		

		HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
		InputStream in = openConnection.getInputStream();
		
		 String data = "";
		 String line = "";
		 try {
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		  
		   while ( ( line = buf.readLine() ) != null ) {
			   data+= line;
		   }
		 } 
		 finally {
		   in.close();
		 }
		System.out.println( data );
		//JSONArray obj = (JSONArray) JSONValue.parseWithException(data);	//parse JSON Array
		//JSONObject obj = (JSONObject) JSONValue.parseWithException(data);	 //parse JSON Object
		System.out.println( "OK" );
	}catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
}}
