package com.solarise.gui;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.solarise.entity.PanelStringEntity;

public class GetAllPanels {
	ArrayList<PanelStringEntity> panelList=new ArrayList<PanelStringEntity>();
    public GetAllPanels(){
    	try{
    		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder=factory.newDocumentBuilder();
    		Document doc=builder.parse("http://solarise-qut.appspot.com/query?function=get_all_panels");
    		
    		Element root=doc.getDocumentElement();
    		NodeList panels=root.getChildNodes();
    		for(int i=0;i<panels.getLength();i++){
    			Node panel=panels.item(i);
    			PanelStringEntity pe=new PanelStringEntity();
    			if(panel.getNodeType()==Node.ELEMENT_NODE){
    				NodeList p=panel.getChildNodes();
    				for(int j=0;j<p.getLength();j++){
    					if(p.item(j).getNodeType()==Node.ELEMENT_NODE){
    						if(p.item(j).getNodeName().equals("model") && p.item(j).getTextContent()!=null){
    							if( p.item(j).getTextContent()!=null){
    								pe.model=p.item(j).getTextContent();
    							}
    							else{
    								pe.model="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("height")){
    							if( p.item(j).getTextContent()!=null){
    								pe.height=p.item(j).getTextContent();
    							}
    							else{
    								pe.height="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("length")){
    							if( p.item(j).getTextContent()!=null){
    								pe.length=p.item(j).getTextContent();
    							}
    							else{
    								pe.length="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("manufacturer")){
    							if( p.item(j).getTextContent()!=null){
    								pe.manufacturer=p.item(j).getTextContent();
    							}
    							else{
    								pe.manufacturer="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("efficiency")){
    							if( p.item(j).getTextContent()!=null){
    								pe.efficiency=p.item(j).getTextContent();
    							}
    							else{
    								pe.efficiency="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("efficiencyLoss")){
    							if( p.item(j).getTextContent()!=null){
    								pe.efficiencyLoss=p.item(j).getTextContent();
    							}
    							else{
    								pe.efficiencyLoss="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("price")){
    							if( p.item(j).getTextContent()!=null){
    								pe.price=p.item(j).getTextContent();
    							}
    							else{
    								pe.price="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("powerOutput")){
    							if( p.item(j).getTextContent()!=null){
    								pe.powerOutput=p.item(j).getTextContent();
    							}
    							else{
    								pe.powerOutput="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("technology")){
    							if( p.item(j).getTextContent()!=null){
    								pe.technology=p.item(j).getTextContent();
    							}
    							else{
    								pe.technology="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("weight")){
    							if( p.item(j).getTextContent()!=null){
    								pe.weight=p.item(j).getTextContent();
    							}
    							else{
    								pe.weight="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("width")){
    							if( p.item(j).getTextContent()!=null){
    								pe.width=p.item(j).getTextContent();
    							}
    							else{
    								pe.width="null";
    							}
    						}
    						else if(p.item(j).getNodeName().equals("inverterLife")){
    							if( p.item(j).getTextContent()!=null){
    								pe.inverterLife=p.item(j).getTextContent();
    							}
    							else{
    								pe.inverterLife="null";
    							}
    						}
    						else{
    							continue;
    						}
    					}
    				}
    				panelList.add(pe);
    			}
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public ArrayList<PanelStringEntity> getAllPanels(){
    	return panelList;
    }
}