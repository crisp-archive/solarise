package com.solarise.server;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.solarise.entity.UserEntity;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		UserEntity ue=new UserEntity();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String email=req.getParameter("email");
		String password="";
		String type="xml";
		if(req.getParameter("type")==null){
    		type="xml";
    	}
    	else{
    		type=req.getParameter("type");
    	}
		// encoding with MD5
		try {
			MessageDigest alg=MessageDigest.getInstance("MD5");
			alg.update(req.getParameter("password").getBytes());
			byte[] digesta=alg.digest();
			password=ue.byte2str(digesta);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		Query q = new Query("user").
				setFilter(CompositeFilterOperator.and(
				new FilterPredicate("email", Query.FilterOperator.EQUAL, email),
				new FilterPredicate("password",Query.FilterOperator.EQUAL, password)
				));

		PreparedQuery pq=datastore.prepare(q);
		// return xml or json file
		if(pq.countEntities()==1){
			if(type.equals("json")){
				resp.setContentType("application/json");
				String json="var login={\\\"status\\\":\\\""+200+"\\\",\\\"email\\\":\\\""+email+"\\\"}";
				resp.getWriter().print(json);
			}
			else{
				resp.setContentType("text/xml");
				String xml="<?xml version=\"1.0\" ?><solarise><status>200</status><email>"+email+"</email></solarise>";
				resp.getWriter().print(xml);
			}
		}
		else{
			if(type.equals("json")){
				resp.setContentType("application/json");
				String json="var login={\\\"status\\\":\\\""+401+"\\\",\\\"email\\\":\\\""+email+"\\\"}";
				resp.getWriter().print(json);
			}
			else{
				resp.setContentType("text/xml");
				String xml="<?xml version=\"1.0\" ?><solarise><status>401</status><email>"+email+"</email></solarise>";
				resp.getWriter().print(xml);
			}
		}
    }
    
}