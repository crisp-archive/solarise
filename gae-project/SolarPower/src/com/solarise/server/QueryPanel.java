package com.solarise.server;

import java.util.ArrayList;

import com.google.appengine.api.datastore.*;
import com.solarise.entity.PanelEntity;

public class QueryPanel {
	DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	Query q = new Query("panel");
	ArrayList<PanelEntity> al=new ArrayList<PanelEntity>();
	public QueryPanel(){
		PreparedQuery pq=datastore.prepare(q);
		for(Entity result:pq.asIterable()){
			PanelEntity p=new PanelEntity();
			// basic panel info
			p.height=(Long) result.getProperty("height");
			p.length=(Long) result.getProperty("length");
			p.weight=(Double) result.getProperty("weight");
			p.width=(Long) result.getProperty("width");
			
			p.manufacturer=(String) result.getProperty("manufacturer");
			p.model=(String) result.getProperty("model");
			p.efficiency=(Double) result.getProperty("efficiency");
			p.powerOutput=(Long) result.getProperty("powerOutput");
			p.technology=(String) result.getProperty("technology");
			
			p.price=(Double) result.getProperty("price");
			p.efficiencyLoss=(Double) result.getProperty("efficiencyLoss");
			p.inverterLife=(Long) result.getProperty("inverterLife");
			//
			al.add(p);
		}
	}
	
	public String getXML(){
		String xml=new String();
		xml+="<?xml version=\"1.0\" ?>\n";
		xml+="<solarise>\n"; 
		if(al.size()==0){
			xml+="<error>No entry.</error>";
			return xml;
		}
		else{
			for(int i=0;i<al.size();i++){
				PanelEntity p=al.get(i);
				xml+="<panel id=\""+i+"\">\n";
				xml+="<height>"+p.height+"</height>\n";
				xml+="<length>"+p.length+"</length>\n";
				xml+="<weight>"+p.weight+"</weight>\n";
				xml+="<width>"+p.width+"</width>\n";
				xml+="<manufacturer>"+p.manufacturer+"</manufacturer>\n";
				xml+="<model>"+p.model+"</model>\n";
				xml+="<efficiency>"+p.efficiency+"</efficiency>\n";
				xml+="<powerOutput>"+p.powerOutput+"</powerOutput>\n";
				xml+="<price>"+p.price+"</price>\n";
				xml+="<technology>"+p.technology+"</technology>\n";
				xml+="<inverterLife>"+p.inverterLife+"</inverterLife>\n";
				xml+="<efficiencyLoss>"+p.efficiencyLoss+"</efficiencyLoss>\n";
				xml+="</panel>\n";
			}
		}
		xml+="</solarise>\n";
		return xml;
	}
	
	public String getJSON(){
		String JSON=new String();
		JSON+="var panels=\"";
		JSON+="{\\\"function\\\":\\\"Get All Panels\\\",";
		JSON+="\\\"number\\\":\\\""+al.size()+"\\\",";
		if(al.size()==0){
			JSON+="}";
			return JSON;
		}
		
		JSON+="\\\"panel\\\":";
		JSON+="[";
		
		for(int i=0;i<al.size();i++){
			PanelEntity p=al.get(i);
			JSON+="{";
			JSON+="\\\"id\\\": \\\""+i+"\\\",";
			JSON+="\\\"height\\\": \\\""+p.height+"\\\",";
			JSON+="\\\"length\\\": \\\""+p.length+"\\\",";
			JSON+="\\\"manufacturer\\\": \\\""+p.manufacturer+"\\\",";
			JSON+="\\\"model\\\": \\\""+p.model+"\\\",";
			JSON+="\\\"efficiency\\\": \\\""+p.efficiency+"\\\",";
			JSON+="\\\"powerOutput\\\": \\\""+p.powerOutput+"\\\",";
			JSON+="\\\"price\\\": \\\""+p.price+"\\\",";
			JSON+="\\\"technology\\\": \\\""+p.technology+"\\\",";
			JSON+="\\\"weight\\\": \\\""+p.weight+"\\\",";
			JSON+="\\\"width\\\": \\\""+p.width+"\\\",";
			JSON+="\\\"inverterLife\\\": \\\""+p.inverterLife+"\\\",";
			JSON+="\\\"efficiencyLoss\\\": \\\""+p.efficiencyLoss+"\\\"";
			JSON+="}";
			if(i!=al.size()-1){
				JSON+=",";
			}
		}
		JSON+="]";
		JSON+="}\";";
		return JSON;
	}
}
