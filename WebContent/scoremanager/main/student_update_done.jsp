<%-- 学生登録追加完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

<c:param name="title">
  		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
			<p style="text-align:center" class="mb-3 fw-norma bg-success bg-opacity-10 py-1 px-5"><label>変更が完了しました</label></p>
			<br><br><br>
			<a href="student_list.jsp">学生一覧</a>
	</c:param>
</c:import>
