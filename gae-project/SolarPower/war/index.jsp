<%@ include file="head.jsp" %>
<article class="content">
   	<div id="intro_wrapper">
   		<div class="intro_obj">
   			<img src="images/harness.jpg" alt="Harness the power of sun">
   			Solarise provides integrated solutions to build a powerful and environmentally friendly solar power system.
   		</div>
   		<div class="intro_obj">
   			<img src="images/puzzle.png" alt="covers all">
   			Solarise covers all aspects of customers, including geolocation, daylight, roof area, different panels and energy consuming.
   		</div>
   		<div class="intro_obj">
   			<img src="images/roi.jpg" alt="ROI">
   			Solarise helps customers to get a good return on investment.
   		</div>
   	</div>
   	
   		
<%
    if(!isLogged){
        out.print("<div>To enable youself to harness the power of sun, please sign up for free.<p><table>");
        out.print("<form action=\"signup\" method=\"POST\">");
        out.print("<tr><td>First Name</td><td><input name=\"firstname\" type=\"text\"></td></tr>");
    	out.print("<tr><td>Last Name</td><td><input name=\"lastname\" type=\"text\"></td></tr>");
    	out.print("<tr><td>Email</td><td><input name=\"email\" type=\"email\"></td></tr>");
    	out.print("<tr><td>Password</td><td><input name=\"password\" type=\"password\"></td></tr>");
    	out.print("<tr><td>Gender</td><td><select name=\"gender\"><option value=\"Male\">Male</option><option value=\"Female\">Female</option></select></td></tr>");
    	out.print("<tr><td>Address</td><td><textarea name=\"address\"></textarea></td></tr>");
    	out.print("<tr><td>Post Code</td><td><input name=\"postcode\" type=\"text\"></td></tr>");
    	out.print("<tr><td></td><td><input type=\"submit\" value=\"Sign Up\"></td></tr>");
        out.print("</form></table></p></div>");
    }
%>
</article><!-- end .content -->
<%@ include file="foot.jsp" %>
