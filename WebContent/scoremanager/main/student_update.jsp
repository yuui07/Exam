<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
  		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form action="StudentUpdateExecute.action" method="post">
				<div class="row mx-3 my-0 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-lg-12">
					<label class="form-label" for="ent-year">入学年度</label>
					<br>


					<input type="text" id="entyear" value="${entyear}" size=84><br><br>


					<label class="form-label" for="no">学生番号</label><br>
					<input type="text" id="no" value="${no}"size=84><br><br>


</div>

					<label class="form-label" for="name">氏名</label><br>
					<input class="form-control-plaintext ps-3" type="text" name="name" id = "name" readonly value="${name}"><br><br>

		  			<div class="col-11">
<label class="form-label" for="class_num">クラス</label>
<select class="form-select" id="class_num" name="class_num">
<option value="0">--------</option>
<option value="101">101</option>
<option value="102">102</option>
<option value="201">201</option>
<option value="202">202</option>
<c:forEach var="num" items="${class_num_set}">
<%--現在のnumと選択されていたf4が一致していた場合selectedを追記 --%>
<option value="${num}"<c:if test="${num==f4}">selected</c:if>>${num}</option>
</c:forEach>
</select>
</div>
<br>

					在学中<input type="checkbox" name="si_attend"><br><br>
					<button type="submit">変更</button><br><br>
					<a href="StudentList.action">戻る</a>
					</div>

    		</form>
		</section>
	</c:param>
</c:import>
