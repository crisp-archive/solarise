package com.solarise.client;

import java.io.IOException;
import javax.servlet.http.*;
import java.security.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.solarise.entity.UserEntity;

public class SignupServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		UserEntity ue=new UserEntity();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ue.email=req.getParameter("email");
		
		Query q = new Query("user").setFilter(new FilterPredicate("email", Query.FilterOperator.EQUAL, ue.email));

		PreparedQuery pq=datastore.prepare(q);
		if(pq.countEntities()>0){
			resp.sendRedirect("message.jsp?message=Error: Email is registered by other user.");
			return;
		}
		
		ue.address=req.getParameter("address");
		ue.firstname=req.getParameter("firstname");
		ue.gender=req.getParameter("gender");
		ue.lastname=req.getParameter("lastname");
		ue.postCode=req.getParameter("postcode");
		ue.role="Prospective Purchaser";
		try {
			MessageDigest alg=MessageDigest.getInstance("MD5");
			alg.update(req.getParameter("password").getBytes());
			byte[] digesta=alg.digest();
			ue.password=ue.byte2str(digesta);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Entity newUser = new Entity("user");
		newUser.setProperty("address", ue.address);
        newUser.setProperty("email", ue.email);
        newUser.setProperty("firstname", ue.firstname);
        newUser.setProperty("gender", ue.gender);
        newUser.setProperty("lastname", ue.lastname);
        newUser.setProperty("password", ue.password);       
        newUser.setProperty("postCode", ue.postCode);
        newUser.setProperty("role", ue.role);

        datastore.put(newUser);
        
        resp.sendRedirect("message.jsp?message=Sign Up Successfully.");
    }
    
}