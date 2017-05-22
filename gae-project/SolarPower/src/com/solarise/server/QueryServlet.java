package com.solarise.server;

import java.io.IOException;
import javax.servlet.http.*;

import com.solarise.server.QueryPanel;

public class QueryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		proceedQuery(req,resp);
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	proceedQuery(req,resp);      
    }
    
    public void proceedQuery(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	/*
    	 * if type is null, then set default type as XML
    	 * if type is not either XML or JSON, then error
    	 * if func is null, then error
    	 * is func is not supported, then error
    	 */
    	String type;
    	String func;
        String email;
          
    	if(req.getParameter("type")==null){
    		type="xml";
    	}
    	else{
    		type=req.getParameter("type");
    	}
    	if(req.getParameter("email")==null){
    		email="";
    	}
    	else{
    		email=req.getParameter("email");
    	}
    	if(!type.equals("xml")&&!type.equals("json")){
    		String error="Such Type Is Not Supported.";
    		printError(resp,error,type);
    		return;
    	}
    	if(req.getParameter("function")==null){
    		String error="No Function Specified.";
    		printError(resp,error,type);
    		return;
    	}
    	else{
    		func=req.getParameter("function");
    		
    		if(func.equals("get_all_panels")){
    			QueryPanel q=new QueryPanel();
    			if(type.equals("xml")){
    				resp.setContentType("text/xml");
    				resp.getWriter().print(q.getXML());
    			}
    			else{
    				resp.setContentType("application/json");
    				resp.getWriter().print(q.getJSON());
    			}
    		}
    		else if(func.equals("get_all_plans")){
    			QueryPlan q=new QueryPlan(email);
    			if(type.equals("xml")){
    				resp.setContentType("text/xml");
    				resp.getWriter().print(q.getXML());
    			}
    			else{
    				resp.setContentType("application/json");
    				resp.getWriter().print(q.getJSON());
    			}
    		}
    		else if(func.equals("get_all_records")){
    			QueryRecord q=new QueryRecord(email);
    			if(type.equals("xml")){
    				resp.setContentType("text/xml");
    				resp.getWriter().print(q.getXML());
    			}
    			else{
    				resp.setContentType("application/json");
    				resp.getWriter().print(q.getJSON());
    			}
			}
    		else{
    			String error="Unsupported function.";
    			printError(resp,error,type);
    			return;
    		}
    	
    		 
    	}
    }
    public void printError(HttpServletResponse resp, String error, String type) throws IOException{
    	if(type.equals("json")){
    		resp.setContentType("application/json");
        	resp.getWriter().println("{");
        	resp.getWriter().println("\t\"error\": \""+error+"\"");
        	resp.getWriter().println("}");
    	}
    	else{
    		resp.setContentType("text/xml");
    		resp.getWriter().println("<?xml version=\"1.0\" ?>");
    		resp.getWriter().println("<solarise>");
    		resp.getWriter().println("<error>"+error+"</error>");
    		resp.getWriter().println("</solarise>");
    	}
    }
}