<%@ include file="head.jsp" %>
<%@ page import="com.solarise.server.QueryPanel" %>
<%@ page import="com.solarise.entity.PlanEntity" %>
<%
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
    <table>
      <tr>
        <td><b>Panel Model</b></td>
        <td><select></select></td>
      </tr>
      <tr>
        <td><b>Number of Panel</b></td>
        <td><input type="number" id="number" value="<%= pe.number %>"></td>
        <td>Roof Area (Square Meters)</td>
        <td><input type="number" id="roofArea" value="<%= pe.roofArea %>"></td>
        <td><input type="button" id="recommend" value="Recommend"></td>
      </tr>
      <tr>
        <td><b>Energy Usage per Day(kw*h)</b></td>
        <td><input type="number" id="energy" value="<%= pe.energyUse %>"></td>
      </tr>

      <tr>
        <td><b>Manufacturer</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Length</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Width</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Height</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Weight</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Power Output</b></td>
        <td></td>
      </tr>

      <tr>
        <td><input type="checkbox" id="detailsBtn">Show Details</td>
        <td></td>
      </tr>

      <tr>
        <td><b>Technology</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Efficiency</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Efficiency Loss</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Inverter Lifespan</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Price</b></td>
        <td></td>
      </tr>
      <tr>
        <td><b>Building Fee</b></td>
        <td></td>
      </tr>
    </table>
    <div>
      <iframe id="e_chart" src="" style="border:0;width:600px;height:300px;">
      </iframe>
      <iframe id="m_chart" src="" style="border:0;width:600px;height:300px;">
      </iframe>
    </div>
  <script type="text/javascript">
  <%
  QueryPanel q=new QueryPanel();
  out.print(q.getJSON());
  %>
  var num=0;
  $(document).ready(function(){
    panelData=eval("("+panels+")");
    num=parseInt(panelData.number);
    for(i=0;i<num;i++){
      $("select").append("<option>"+panelData.panel[i].model+"</option>");
    }
    showPanel(<%= pe.panelId %>);
    $("select>option:eq(<%= pe.panelId %>)").attr("selected","true");

    $("tr>td:eq(0)").each(function(){
      $(this).css("width","180px");
    });
    $("tr:gt(9)").hide();
  });

  $("select").change(function(){
    idx=$("option:selected").index();
    showPanel(idx);
  });

  $("#detailsBtn").click(function(){
    if($(this).is(":checked")==true)
      $("tr:gt(9)").show();
    else
      $("tr:gt(9)").hide();
  });

  $("#recommend").click(function(){
    idx=$("option:selected").index();
    var roofArea=$("#roofArea").val();
    var capable=Math.floor(roofArea/(panelData.panel[idx].length*panelData.panel[idx].width/1000000));
    $("#number").val(capable);
  });

  function showPanel(idx){
    $("tr:eq(3)>td:eq(1)").html(panelData.panel[idx].manufacturer);
    // size
    $("tr:eq(4)>td:eq(1)").html(panelData.panel[idx].length+" mm");
    $("tr:eq(5)>td:eq(1)").html(panelData.panel[idx].width+" mm");
    $("tr:eq(6)>td:eq(1)").html(panelData.panel[idx].height+" mm");
    $("tr:eq(7)>td:eq(1)").html(panelData.panel[idx].weight+" mm");
    $("tr:eq(8)>td:eq(1)").html(panelData.panel[idx].powerOutput+" W");
    // details
    $("tr:eq(10)>td:eq(1)").html(panelData.panel[idx].technology);
    $("tr:eq(11)>td:eq(1)").html(panelData.panel[idx].efficiency+"%");
    $("tr:eq(12)>td:eq(1)").html(panelData.panel[idx].efficiencyLoss*100+"% per year");
    $("tr:eq(13)>td:eq(1)").html(panelData.panel[idx].inverterLife+" years");
    $("tr:eq(14)>td:eq(1)").html(panelData.panel[idx].price+" $");
    $("tr:eq(15)>td:eq(1)").html("50 $");
    
    $("iframe#e_chart").attr("src","eChart.jsp?output="+panelData.panel[idx].powerOutput+"&loss=0.02");
    $("iframe#m_chart").attr("src","mChart.jsp?output="+panelData.panel[idx].powerOutput+"&loss=0.02");
  }
  </script>
  </article>
  <div id="wizardBtn"><input type="button" value="Back"><input type="button" value="Next"></div>
  <script>
    $("#wizard>span:eq(1)").css("font-weight","bold");
    $("#wizardBtn>input:eq(0)").click(function(){
        var number=$("#number").val();
        var panelId=$("option:selected").index();
        var roofArea=$("#roofArea").val();
        var energyUse=$("#energy").val();
        window.location.href="gettingStarted.jsp?panelId="+panelId+"&number="+number+"&address=<%= pe.address %>&latitude=<%= pe.latitude %>&longitude=<%= pe.longitude %>&energyUse="+energyUse+"&roofArea="+roofArea;
    });
    $("#wizardBtn>input:eq(1)").click(function(){
        var number=$("#number").val();
        var panelId=$("option:selected").index();
        var roofArea=$("#roofArea").val();
        var energyUse=$("#energy").val();
        window.location.href="geolocation.jsp?panelId="+panelId+"&number="+number+"&address=<%= pe.address %>&latitude=<%= pe.latitude %>&longitude=<%= pe.longitude %>&energyUse="+energyUse+"&roofArea="+roofArea;
    });
  </script>
<%@ include file="foot.jsp" %>
