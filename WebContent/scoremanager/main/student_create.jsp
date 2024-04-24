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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

			<form action="StudentCreate.action" method="post">
				<div class="row mx-3 my-0 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-lg-12">
					<label class="form-label" for="ent-year-select">入学年度</label>
					<select class="form-select" id="ent-year-select" name="ent_year">
						<option value="0">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
							<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select><br>

					<label class="form-label" for="no">学生番号</label><br>
					<input class="form-control px-4 fs-5" type="text" id="no" name="no" maxlength=10 value="${no}"size=84 placeholder="学生番号を入力してください" required><br><br>

					<label class="form-label" for="name">氏名</label><br>
					<input class="form-control px-4 fs-5" type="text" id="name" name="name" maxlength=30 value="${name}" size=84 placeholder="氏名を入力してください" required><br><br>

					<label class="form-label" for="class_num">クラス</label>
					<select class="form-select" id="class_num" name="class_num">
					    <option value="0">--------</option>
					    <c:forEach var="classNum" items="${class_num_set}">
					        <option value="${classNum}">${classNum}</option>
					    </c:forEach>
					</select><br>
					<button type="submit">登録して終了</button><br><br>
					<a href="student_list.jsp">戻る</a>
					</div>
				</div>
    		</form>
		</section>
	</c:param>
</c:import>
