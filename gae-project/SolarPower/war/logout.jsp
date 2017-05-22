<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<%
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
%>
<article class="content">
<p>You have logged out.</p>
</article>
<%@ include file="foot.jsp" %>