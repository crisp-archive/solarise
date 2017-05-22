package com.solarise.client;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.solarise.entity.PlanEntity;

public class DeletePlanServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PlanEntity pe=new PlanEntity();
		int projectId=0;
		if(req.getParameter("projectId")!=null){
			projectId=Integer.parseInt(req.getParameter("projectId"));
		}
		String email=req.getParameter("email");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q=new Query("plan").setFilter(new FilterPredicate("email", Query.FilterOperator.EQUAL, email));
		PreparedQuery pq=datastore.prepare(q);
		int i=0;
		for(Entity result:pq.asIterable()){
			if(i++==projectId){
				datastore.delete(result.getKey());
				break;
			}
		}
		resp.sendRedirect("projects.jsp");
	}
}