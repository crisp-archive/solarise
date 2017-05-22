package com.solarise.server;

import java.util.ArrayList;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.solarise.entity.PlanEntity;

public class QueryPlan {
	DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	Query q;
	ArrayList<PlanEntity> al=new ArrayList<PlanEntity>();
	public QueryPlan(String email){
		q=new Query("plan").setFilter(new FilterPredicate("email", Query.FilterOperator.EQUAL, email));
		PreparedQuery pq=datastore.prepare(q);
		for(Entity result:pq.asIterable()){
			PlanEntity p=new PlanEntity();
			p.address=(String) result.getProperty("address");
			p.energyUse=(Double) result.getProperty("energyUse");
			p.latitude=(Double) result.getProperty("latitude");
			p.longitude=(Double) result.getProperty("longitude");
			p.number=(Long) result.getProperty("number");
			p.panelId=(Long) result.getProperty("panelId");
			p.roofArea=(Double) result.getProperty("roofArea");
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
				PlanEntity p=al.get(i);
				xml+="<plan id=\""+i+"\">\n";
				xml+="<address>"+p.address+"</address>\n";
				xml+="<energyUse>"+p.energyUse+"</energyUse>\n";
				xml+="<latitude>"+p.latitude+"</latitude>\n";
				xml+="<longitude>"+p.longitude+"</longitude>\n";
				xml+="<number>"+p.number+"</number>\n";
				xml+="<panelId>"+p.panelId+"</panelId>\n";
				xml+="<roofArea>"+p.roofArea+"</roofArea>\n";
				xml+="</plan>\n";
			}
		}
		xml+="</solarise>\n";
		return xml;
	}
	
	public String getJSON(){
		String JSON=new String();
		JSON+="var plans=\"";
		JSON+="{\\\"function\\\":\\\"Get All Plans\\\",";
		JSON+="\\\"number\\\":\\\""+al.size()+"\\\",";
		if(al.size()==0){
			JSON+="}\";";
			return JSON;
		}
		
		JSON+="\\\"plan\\\":";
		JSON+="[";
		
		for(int i=0;i<al.size();i++){
			PlanEntity p=al.get(i);
			JSON+="{";
			JSON+="\\\"id\\\": \\\""+i+"\\\",";
			JSON+="\\\"address\\\": \\\""+p.address+"\\\",";
			JSON+="\\\"energyUse\\\": \\\""+p.energyUse+"\\\",";
			JSON+="\\\"latitude\\\": \\\""+p.latitude+"\\\",";
			JSON+="\\\"longitude\\\": \\\""+p.longitude+"\\\",";
			JSON+="\\\"number\\\": \\\""+p.number+"\\\",";
			JSON+="\\\"panelId\\\": \\\""+p.panelId+"\\\",";
			JSON+="\\\"roofArea\\\": \\\""+p.roofArea+"\\\"";
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
