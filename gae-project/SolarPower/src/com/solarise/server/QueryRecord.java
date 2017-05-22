package com.solarise.server;

import java.util.ArrayList;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.solarise.entity.RecordEntity;

public class QueryRecord {
	DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	Query q;
	ArrayList<RecordEntity> al=new ArrayList<RecordEntity>();
	public QueryRecord(String email){
		q=new Query("record").setFilter(new FilterPredicate("email", Query.FilterOperator.EQUAL, email));
		PreparedQuery pq=datastore.prepare(q);
		for(Entity result:pq.asIterable()){
			RecordEntity p=new RecordEntity();
			p.data=(Double) result.getProperty("data");
			p.comment=(String) result.getProperty("comment");
			p.projectId=(Long) result.getProperty("projectId");
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
				RecordEntity p=al.get(i);
				xml+="<record id=\""+i+"\">\n";
				xml+="<projectId>"+p.projectId+"</projectId>\n";
				xml+="<data>"+p.data+"</data>\n";
				xml+="<comment>"+p.comment+"</comment>\n";
				xml+="</record>\n";
			}
		}
		xml+="</solarise>\n";
		return xml;
	}
	
	public String getJSON(){
		String JSON=new String();
		JSON+="var records=\"";
		JSON+="{\\\"function\\\":\\\"Get All Records\\\",";
		JSON+="\\\"number\\\":\\\""+al.size()+"\\\",";
		if(al.size()==0){
			JSON+="}\";";
			return JSON;
		}
		
		JSON+="\\\"record\\\":";
		JSON+="[";
		
		for(int i=0;i<al.size();i++){
			RecordEntity p=al.get(i);
			JSON+="{";
			JSON+="\\\"id\\\": \\\""+i+"\\\",";
			JSON+="\\\"projectId\\\": \\\""+p.projectId+"\\\",";
			JSON+="\\\"data\\\": \\\""+p.data+"\\\",";
			JSON+="\\\"comment\\\": \\\""+p.comment+"\\\"";
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
