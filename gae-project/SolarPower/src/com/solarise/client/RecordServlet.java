package com.solarise.client;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.solarise.entity.PanelEntity;
import com.solarise.entity.RecordEntity;

public class RecordServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		RecordEntity re=new RecordEntity();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String email=req.getParameter("email");;
		re.projectId=Long.parseLong(req.getParameter("projectId"));
		re.data=Double.parseDouble(req.getParameter("data"));
		re.comment=req.getParameter("comment");
		
		Entity record = new Entity("record");
		record.setProperty("email", email);
		record.setProperty("projectId", re.projectId);
		record.setProperty("data", re.data);
        record.setProperty("comment", re.comment);
        datastore.put(record);
        
        resp.sendRedirect("tracker.jsp");
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		RecordEntity re=new RecordEntity();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String email=req.getParameter("email");;
		re.projectId=Long.parseLong(req.getParameter("projectId"));
		re.data=Double.parseDouble(req.getParameter("data"));
		re.comment=req.getParameter("comment");
		
		Entity record = new Entity("record");
		record.setProperty("email", email);
		record.setProperty("projectId", re.projectId);
		record.setProperty("data", re.data);
        record.setProperty("comment", re.comment);
        datastore.put(record);
        
        resp.sendRedirect("tracker.jsp");
        /*
		PanelEntity pe=new PanelEntity();
		pe.efficiencyLoss=0.02;
		pe.efficiency=14.1;
		pe.height=(long)35;
		pe.inverterLife=(long)10;
		pe.length=(long)1580;
		pe.manufacturer="Suntech";
		pe.model="STP170S-24Ad";
        pe.powerOutput=(long)170;
        pe.price=391.0;
        pe.technology="Monocrystalline Silicon";
        pe.weight=(double)35;
        pe.width=(long)808;
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        Entity panel = new Entity("panel");
        panel.setProperty("efficiency", pe.efficiency);
        panel.setProperty("efficiencyLoss", pe.efficiencyLoss);
        panel.setProperty("height", pe.height);
        panel.setProperty("inverterLife", pe.inverterLife);
        panel.setProperty("length", pe.length);
        panel.setProperty("manufacturer", pe.manufacturer);
        panel.setProperty("model", pe.model);
        panel.setProperty("powerOutput", pe.powerOutput);
        panel.setProperty("price", pe.price);
        panel.setProperty("technology", pe.technology);
        panel.setProperty("weight", pe.weight);
        panel.setProperty("width", pe.width);
        datastore.put(panel);
        */
    }
    
}