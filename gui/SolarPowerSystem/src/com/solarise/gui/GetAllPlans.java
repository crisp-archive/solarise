package com.solarise.gui;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.solarise.entity.PlanEntity;

public class GetAllPlans {
	ArrayList<PlanEntity> planList;
	String email;
    public GetAllPlans(String email){
    	planList=new ArrayList<PlanEntity>();
    	this.email=email;
    }
    
    public ArrayList<PlanEntity> getAllPlans(){
    	try{
    		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder=factory.newDocumentBuilder();
    		Document doc=builder.parse("http://solarise-qut.appspot.com/query?function=get_all_plans&email="+email);
    		
    		Element root=doc.getDocumentElement();
    		NodeList plans=root.getChildNodes();
    		for(int i=0;i<plans.getLength();i++){
    			Node plan=plans.item(i);
    			PlanEntity pe=new PlanEntity();
    			if(plan.getNodeType()==Node.ELEMENT_NODE){
    				NodeList p=plan.getChildNodes();
    				for(int j=0;j<p.getLength();j++){
    					if(p.item(j).getNodeType()==Node.ELEMENT_NODE){
    						if(p.item(j).getNodeName().equals("address")){
    							pe.address=p.item(j).getTextContent();
    						}
    						else if(p.item(j).getNodeName().equals("energyUse")){
    							pe.energyUse=Double.parseDouble(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("latitude")){
    							pe.latitude=Double.parseDouble(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("longitude")){
    							pe.longitude=Double.parseDouble(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("number")){
    							pe.number=Long.parseLong(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("panelId")){
    							pe.panelId=Long.parseLong(p.item(j).getTextContent());
    						}
    						else if(p.item(j).getNodeName().equals("roofArea")){
    							pe.roofArea=Double.parseDouble(p.item(j).getTextContent());
    						}
    						else{
    							continue;
    						}
    					}
    				}
    				planList.add(pe);
    			}
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return planList;
    }
}