<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
  // redireciona automaticamente para a listagem de SLAs
  response.sendRedirect(request.getContextPath() + "/sla?action=list");
%>
