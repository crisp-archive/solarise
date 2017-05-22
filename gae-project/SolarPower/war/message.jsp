<%@ include file="head.jsp" %>
<article class="content">
<div>
	<%= request.getParameter("message") %>
</div>
<div>
	<a href="<%= request.getHeader("Referer") %>">Go Back</a>
</div>
</article>
<%@ include file="foot.jsp" %>