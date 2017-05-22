package com.solarise.gui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class MapHandler extends BrowserFunction{
	private String addr;
	private Double lat;
	private Double lng;
	public MapHandler(Browser browser, String name) {
		super(browser, name);
		// TODO Auto-generated constructor stub
	}
	
	public Object function(Object[] arguments) { 
		addr = ((String)arguments[0]);
		lat = ((Double)arguments[1]);
		lng = ((Double)arguments[2]);
	    return null;
	}
	
	public String getAddress(){
		return addr;
	}
	
	public double getLatitude(){
		return lat;
	}
	
	public double getLongitude(){
		return lng;
	}
}
