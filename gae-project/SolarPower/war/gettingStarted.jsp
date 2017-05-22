<%@ include file="head.jsp" %>
<%@ page import="com.solarise.entity.PlanEntity" %>
<%
  if(!isLogged){
        response.sendRedirect("login.jsp?redir=gettingStarted.jsp");
    }
PlanEntity pe=new PlanEntity();
if(request.getParameter("panelId")!=null && request.getParameter("panelId")!=""){
    pe.panelId=Long.parseLong(request.getParameter("panelId"));
}
else{
    pe.panelId=(long)0;
}
if(request.getParameter("number")!=null && request.getParameter("number")!=""){
    pe.number=Long.parseLong(request.getParameter("number"));
}
else{
    pe.number=(long)0;
}
if(request.getParameter("address")!=null){
    pe.address=request.getParameter("address");
}
if(request.getParameter("latitude")!=null && request.getParameter("latitude")!=""){
    pe.latitude=Double.parseDouble(request.getParameter("latitude"));
}
else{
    pe.latitude=(double)0;
}
if(request.getParameter("longitude")!=null && request.getParameter("longitude")!=""){
    pe.longitude=Double.parseDouble(request.getParameter("longitude"));
}
else{
    pe.longitude=(double)0;
}
if(request.getParameter("energyUse")!=null && request.getParameter("energyUse")!=""){
    pe.energyUse=Double.parseDouble(request.getParameter("energyUse"));
}
else{
    pe.energyUse=(double)0;
}
if(request.getParameter("roofArea")!=null && request.getParameter("roofArea")!=""){
    pe.roofArea=Double.parseDouble(request.getParameter("roofArea"));
}
else{
    pe.roofArea=(double)0;
}
%>
  <div id="wizard">
    <span>Getting Started</span> >
    <span>Solar Panels</span> >
    <span>Geolocation</span> >
    <span>Estimation</span>
  </div>
  <article class="content">
    <div>
      <b>Welcome to Solarise solar power calculator!</b>
    </div>
    <div>
      Tip: For a better user experience, we recommend you to log in to the system.
    </div>
    <div>
      <br>
      <li>Step 1: Choose a panel and set energy usage per day</li>
      <li>Step 2: Get geolocation</li>
      <li>Step 3: Calculate results</li>
    </div>
  </article><!-- end .content -->
  <div id="wizardBtn"><input type="button" value="Back" readonly><input type="button" value="Next"></div>
  <script>
    $("#wizard>span:eq(0)").css("font-weight","bold");
    $("#wizardBtn>input:eq(1)").click(function(){
        window.location.href="panels.jsp?panelId=<%= pe.panelId %>&number=<%= pe.number %>&address=<%= pe.address %>&latitude=<%= pe.latitude %>&longitude=<%= pe.longitude %>&energyUse=<%= pe.energyUse %>&roofArea=<%= pe.roofArea %>";
    });
  </script>
<%@ include file="foot.jsp" %>
