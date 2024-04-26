<%--成績管理登録完了画面JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

<c:param name="title">
  		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<p style="text-align:center" class="mb-3 fw-norma bg-success bg-opacity-10 py-1 px-5"><label>登録が完了しました</label></p>
			<br><br><br>
			<a href="test_regist.jsp">戻る</a>&emsp;&emsp;&emsp;
			<a href="test_list.jsp">成績参照</a>
	</c:param>
</c:import>
