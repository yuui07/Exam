<%--成績管理一覧JSP --%>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     <%--成績管理一覧画面--%>
<%@ page language= "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:import url = "/common/base.jsp">

	<c:param name = "title">
		得点管理システム

		</c:param>

	<c:param name = "scripts"></c:param>

	<c:param name = "content">
	<section class = "me-4">
	<h2 class = "h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
	<div class = "my-2 text-end px-4">
	</div>
	<form method = "get">
	<div class = "row border mx-3 mb-3 py-2 align-items-center rounded" id = "filter">
		<div class = "col-2">
			<label class="form-label"for="student-f1-select">入学年度</label>
			<select class="form-select " id="student-f1-select" name="f1">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ent_year_set}">
					<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
		</div>

		<div class = "col-2">
			<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select " id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
             			<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
						<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
		</div>

		<div class = "col-4">
			<label class="form-label" for="student-f2-select">科目</label>
				<select class="form-select " id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${subject_cd_set}">
             			<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
						<option value="${subject_cd}" <c:if test="${subject_cd==f3}">selected</c:if>>${subject_cd}</option>
					</c:forEach>
				</select>
		</div>

		<div class = "col-2">
			<label class="form-label" for="student-f2-select">回数</label>
				<select class="form-select " id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
             			<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
						<option value="${num}" <c:if test="${num==f4}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
		</div>
		<div class = "col-2 text-center">
		<button class = "btn btn-secondary" id = "filter-button">検索</button>
		</div>

	</div>


	<div class = "mt-2 text-warning">${errors.get("f1")}</div>

	</form>
	<c:choose>
		<c:when test = "${subject}">
		<div>科目:${subject}</div>
		<table class = "table table-hover">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生暗号</th>
					<th>氏名</th>
					<th>点数</th>

				</tr>
				<c:forEach var = "student" items = "${students}">
					<tr>
						<td>${student.entYear}</td>
						<td>${student.classNum}</td>
						<td>${student.no}</td>
						<td>${student.name}</td>

						<td class="text-center">
							<input type="text" name="point_${studentno}" size="5"
								value="${student.score}" placeholder="0-100" onblur="validateScore(this)">
							<span class="error" id="error_${student.no}" style="color: red; display: none;">点数は0から100の間で入力してください。</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<div class="col-2 text-center">
    <button class="btn btn-secondary" id="filter-button" onclick="test_regist_done.jsp">登録して終了</button>
    </div>

	</section>
	</c:param>
</c:import>

