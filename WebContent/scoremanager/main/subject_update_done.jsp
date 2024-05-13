<%--科目変更完了画面JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>
<c:import url="/common/base.jsp">
<c:param name="title">
		得点管理システム
</c:param>
<c:param name="scripts"></c:param>

	<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4" style="background-color: #f2f2f2; padding: 10px;">科目情報変更</h2>
<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<div class="col-16 bg-success bg-opacity-50">
<p class="text-center"style="color: black; background-color: #aad4aa; text-align: center;">変更が完了しました</p>
</div>
</div>
<a href="subject_update.jsp" class="mt-3">科目一覧</a>
</section>
</c:param>
</c:import>
