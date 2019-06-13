<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%
    Cookie[] cks = request.getCookies();
    if (cks != null) {
        for (int i = 0; i < cks.length; i++) {
            String name = cks[i].getName();
            String value = cks[i].getValue();   //THIS VALUE IS TO CHECK THE
            if (name.equals("auth")) {
                break; // exit the loop and continue the page
            }
            if (i == (cks.length - 1)) // if all cookie are not valid redirect to error page
            {
                response.sendRedirect("sessionExpired.html");
                return; // to stop further execution
            }
            i++;
        }
    } else {
        response.sendRedirect("sessionExpired.html");
        return; // to stop further execution
    }
%>
<h3>You had successfully logged in.</h3><br>
your session is set to expire in 10min<br>
try reloading after 10 min <br>
<a href="/Sample_war/Sample/Test.html">Test Inside</a>
<form action="ServletSignout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>