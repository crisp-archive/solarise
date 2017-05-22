<%@ page import="java.net.URL" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.Element" %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ include file="head.jsp" %>
<article class="content">
<%
String logEmail=request.getParameter("email");
String redir=request.getParameter("redir");
String password=request.getParameter("password");
try{
	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder builder=factory.newDocumentBuilder();
    Document doc=builder.parse("http://solarise-qut.appspot.com/login?email="+logEmail+"&password="+password+"&type=xml");
    Element root=doc.getDocumentElement();
    NodeList list=root.getChildNodes();
    Long status=Long.parseLong(list.item(0).getTextContent());
    if(status==200){
		// remove all previous cookies
		Cookie[] cookies=request.getCookies();
		try{
			for(int i=0;i<cookies.length;i++)
			{
				Cookie newCookie=new Cookie("email",null);
				newCookie.setMaxAge(0);
				response.addCookie(newCookie);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		// set cookies
		Cookie cEmail=new Cookie("email", logEmail);
		cEmail.setMaxAge(3600*24*30);
		response.addCookie(cEmail);

    	response.sendRedirect(redir);
	}
	else{
		out.print("Login error.<br>");
		out.print("<a href=\"login.jsp\">Retry Log In</a>");
	}
}catch(Exception e){
	e.printStackTrace();
}
%>
</article>
<%@ include file="foot.jsp" %>
