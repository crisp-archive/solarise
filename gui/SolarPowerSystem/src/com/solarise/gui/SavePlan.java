package com.solarise.gui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.solarise.entity.PlanEntity;

public class SavePlan{
	public SavePlan(String email, PlanEntity pe){
		try{
			URL url=new URL("http://solarise-qut.appspot.com/savePlan?"+pe.panelId+"&address="+URLEncoder.encode(pe.address)+"&number="+pe.number+"&latitude="+pe.latitude+
					"&longitude="+pe.longitude+"&energyUse="+pe.energyUse+"&email="+email+"&roofArea="+pe.roofArea);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			InputStream in = conn.getInputStream();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}