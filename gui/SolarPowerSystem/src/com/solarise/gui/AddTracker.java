package com.solarise.gui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.solarise.entity.RecordEntity;

public class AddTracker{
	public AddTracker(String email, RecordEntity re){
		try{
			URL url=new URL("http://solarise-qut.appspot.com/record?projectId="+re.projectId+"&comment="+URLEncoder.encode(re.comment)+"&data="+re.data+"&email="+email);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			InputStream in = conn.getInputStream();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}