package com.solarise.client;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.solarise.entity.PlanEntity;

public class SavePlanServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PlanEntity pe=new PlanEntity();
		if(req.getParameter("panelId")!=null){
		    pe.panelId=Long.parseLong(req.getParameter("panelId"));
		}
		if(req.getParameter("number")!=null){
		    pe.number=Long.parseLong(req.getParameter("number"));
		}
		if(req.getParameter("address")!=null){
		    pe.address=req.getParameter("address");
		}
		if(req.getParameter("latitude")!=null){
		    pe.latitude=Double.parseDouble(req.getParameter("latitude"));
		}
		if(req.getParameter("longitude")!=null){
		    pe.longitude=Double.parseDouble(req.getParameter("longitude"));
		}
		if(req.getParameter("energyUse")!=null){
		    pe.energyUse=Double.parseDouble(req.getParameter("energyUse"));
		}
		if(req.getParameter("roofArea")!=null){
		    pe.roofArea=Double.parseDouble(req.getParameter("roofArea"));
		}
		String email=req.getParameter("email");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity newPlan=new Entity("plan");
		newPlan.setProperty("email", email);
		newPlan.setProperty("panelId", pe.panelId);
		newPlan.setProperty("number", pe.number);
		newPlan.setProperty("address", pe.address);
		newPlan.setProperty("latitude", pe.latitude);
		newPlan.setProperty("longitude", pe.longitude);
		newPlan.setProperty("energyUse", pe.energyUse);
		newPlan.setProperty("roofArea", pe.roofArea);
		
		datastore.put(newPlan);
		
		resp.sendRedirect("projects.jsp");
	}
}