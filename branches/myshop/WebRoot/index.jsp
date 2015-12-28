<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.sendRedirect("../shop/index!all.action");




%>
<!--request.getRequestDispatcher("../shop/index!all.action").forward(request,response); 
response.sendRedirect("shop/index!all.action"); -->

