<%@ include file="head.jsp" %>
<article class="content">
<section>
<h3>Log In</h3>
<form action="doLogin.jsp" method="POST">
    <table>
    	<tr>
    		<td>Email</td>
    		<td><input name="email" type="email"></td>
    	</tr>
    	<tr>
    		<td>Password</td>
    		<td><input name="password" type="password"></td>
    	</tr>
    	<tr>
    		<td></td>
    		<td>
                <input type="submit" value="Log In">
                <input name="redir" type="hidden" value="<%= request.getParameter("redir") %>">
                <input name="type" type="hidden" value="json">
            </td>
    	</tr>
    </table>    
</form>
</section>
</article>
<%@ include file="foot.jsp" %>