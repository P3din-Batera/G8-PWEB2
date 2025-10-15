<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>SLAApp</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <style> body { padding-top: 70px; } .table-actions button { margin-right:6px; } </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/sla?action=list">SLAApp</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sla?action=list">SLAs</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/responsavel?action=list">Responsáveis</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/tiposervico">Tipos</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
