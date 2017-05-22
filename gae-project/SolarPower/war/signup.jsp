<%@ include file="head.jsp" %>
<article class="content">
<section>
    <h3>Sign Up</h3>
    <p>
    <table>
    <form action="signup" method="POST">
    	<tr>
    		<td>First Name</td>
    		<td><input name="firstname" type="text"></td>
    	</tr>
    	<tr>
    		<td>Last Name</td>
    		<td><input name="lastname" type="text"></td>
    	</tr>
    	<tr>
    		<td>Email</td>
    		<td><input name="email" type="email"></td>
    	</tr>
    	<tr>
    		<td>Password</td>
    		<td><input name="password" type="password"></td>
    	</tr>
    	<tr>
    		<td>Gender</td>
    		<td>
    			<select name="gender">
    			<option value="Male">Male</option>
    			<option value="Female">Female</option>
    			</select>
			</td>
    	</tr>
    	<tr>
    		<td>Address</td>
    		<td><textarea name="address"></textarea></td>
    	</tr>
    	<tr>
    		<td>Post Code</td>
    		<td><input name="postcode" type="text"></td>
    	</tr>
    	
    	<tr>
    		<td></td>
    		<td><input type="submit" value="Sign Up"></td>
    	</tr>
    </form>
    </table>
    </p>
    </section>
</article>
<%@ include file="foot.jsp" %>