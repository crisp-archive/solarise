<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Solarise Solar Power</title>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="icon" href="images/logo.png" type="image/png">
<link rel="shortcut icon" href="images/logo.png" type="image/png">
<script type="text/javascript" src="jquery-1.8.1.min.js"></script>
</head>

<body>

<div class="container">
  <header>
    <span class="header_span"><a href="index.jsp"><img src="images/logo.png"></a></span>
    <span class="header_span">
      <div><a href="index.jsp">Solarise</a><br></div>
      <div id="second_title">&nbsp;Solar Power Calculator</div>
    </span>
  </header>
  <div class="sidebar">
    <nav>
      <span class="menuitem"><a href="index.jsp">Home</a></span>
      <span class="menuitem"><a href="gettingStarted.jsp">Calculator</a></span>
      <span class="menuitem"><a href="projects.jsp">Project</a></span>
      <span class="menuitem"><a href="tracker.jsp">Tracker</a></span>
      <%
      boolean isLogged=false;
      String email="";
      Cookie[] c=request.getCookies();
      if(c!=null){
        for(int i=0;i<c.length;i++){
          if(c[i].getName().equals("email")){
            email=c[i].getValue();
            isLogged=true;
            break;
          }
        }
      }
      if(!isLogged){
        out.print("<span class=\"menuitem\"><a href=\"login.jsp?redir=index.jsp\">Log In</a></span>");
        out.print("<span class=\"menuitem\"><a href=\"signup.jsp\">Sign Up</a></span>");
      }
      else{
        out.print("<span class=\"menuitem\"><a href=\"user.jsp\">User</a></span>");
      }
      %>
    </nav>
  </div><!-- end .sidebar -->