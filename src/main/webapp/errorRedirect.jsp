<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%
    response.sendRedirect(request.getContextPath()+"/home/"+response.getStatus());
%>
