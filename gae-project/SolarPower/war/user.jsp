<%@ include file="head.jsp" %>
<%
if(!isLogged){
	response.sendRedirect("index.jsp");
}
%>
  <article class="content">
    <section>
    <p>Log in as: <%= email %></p>
    <p><a href="logout.jsp">Log out</a></p>
	</section>
    <!-- end .content --></article>
<%@ include file="foot.jsp" %>
