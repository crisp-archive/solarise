<%@ include file="head.jsp" %>
<article class="content">
<%
Long projectId=(long)0;
if(request.getParameter("projectId")!=null){
    projectId=Long.parseLong(request.getParameter("projectId"));
}
%>
<form method="post" action="record">
	<table>
		<tr>
			<td>Electricity generated(kw*h)</td>
			<td><input type="text" name="data"></td>
		</tr>
		<tr>
			<td>Comment</td>
			<td><input type="text" name="comment"></td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="projectId" value="<%= projectId %>">
				<input type="hidden" name="email" value="<%= email %>">
			</td>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>
</article>
<%@ include file="foot.jsp" %>