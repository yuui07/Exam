<%--成績参照検索画面JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            <form method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-2">
                        <div>科目情報</div>
                    </div>
                    <div class="col-2">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subject_set}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${fn:escapeXml(subject.name)}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </div>
                <div class="mt-2 text-warning">${errors.get("f1")}</div>
            </form>

            <c:choose>
                <c:when test="${dep == true}">
                    <div>科目: ${fn:escapeXml(subject_name)}: ${test_no}回</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                        <c:forEach var="test" items="${tests}"> <!-- ここでTestオブジェクトのリストをループ -->
                            <tr>
                                <td>${test.student.entYear}</td>
                                <td>${test.student.classNum}</td>
                                <td>${test.student.no}</td>
                                <td>${test.student.name}</td>
                                <td class="text-center">
                                    <input type="text" name="point_${test.student.no}" size="5"
                                           value="${test.point}" placeholder="0-100" onblur="validateScore(this)">
                                    <span class="error" id="error_${test.student.no}" style="color: red; display: none;">点数は0から100の間で入力してください。</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="exe-button" onclick="location.href='test_regist_done.jsp'">登録して終了</button>
                    </div>
                </c:when>
            </c:choose>
        </section>
    </c:param>
</c:import>
