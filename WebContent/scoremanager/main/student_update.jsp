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

			<form action="StudentCreate.action" method="post">
				<div class="row mx-3 my-0 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-lg-12">
					<label class="form-label" for="ent-year">入学年度</label>
					<br>
					<input type="text" id="ent-year" value="${ent_year}" size=84 readonly><br><br>

					<label class="form-label" for="no">学生番号</label><br>
					<input type="text" id="no" value="${no}"size=84 readonly><br><br>

					<label class="form-label" for="name">氏名</label><br>
					<input type="text" id="name" name="name" maxlength=30 value="${name}" size=84 placeholder="氏名を入力してください" required><br><br>

					<label class="form-label" for="class_num">クラス</label>
					<select class="form-select" id="class_num" name="class_num">
						<option value="0">--------</option>
						<c:forEach var="class_num" items="${class_num}">
							<option value="${class_num}">
						</c:forEach>
					</select><br>

					在学中<input type="checkbox" name="si_attend"><br><br>
					<button type="submit">変更</button><br><br>
					<a href="student_management.jsp">戻る</a>
					</div>
				</div>
    		</form>
		</section>
	</c:param>
</c:import>
