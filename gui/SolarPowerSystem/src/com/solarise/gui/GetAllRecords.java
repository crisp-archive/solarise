package com.solarise.gui;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.solarise.entity.RecordEntity;

public class GetAllRecords {
	ArrayList<RecordEntity> recordList;
	String email;
    public GetAllRecords(String email){
    	recordList=new ArrayList<RecordEntity>();
    	this.email=email;
    }
    
    public ArrayList<RecordEntity> getAllRecords(){
    	try{
    		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder=factory.newDocumentBuilder();
    		Document doc=builder.parse("http://solarise-qut.appspot.com/query?function=get_all_records&email="+email);
    		
    		Element root=doc.getDocumentElement();
    		NodeList records=root.getChildNodes();
    		for(int i=0;i<records.getLength();i++){
    			Node record=records.item(i);
    			RecordEntity re=new RecordEntity();
    			if(record.getNodeType()==Node.ELEMENT_NODE){
    				NodeList p=record.getChildNodes();
    				for(int j=0;j<p.getLength();j++){
    					if(p.item(j).getNodeType()==Node.ELEMENT_NODE){
    						if(p.item(j).getNodeName().equals("projectId")){
    							re.projectId=Long.parseLong(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("data")){
    							re.data=Double.parseDouble(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("comment")){
    							re.comment=p.item(j).getTextContent();
    						}
    						else{
    							continue;
    						}
    					}
    				}
    				recordList.add(re);
    			}
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return recordList;
    }
}