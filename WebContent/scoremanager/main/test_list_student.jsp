<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績参照システム</c:param>
    <c:param name="scripts">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    </c:param>
    <c:param name="content">
        <div class="container-fluid">
            <div class="row">
                <!-- Main content -->
                    <div class="pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">成績参照システム</h1>
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute">
                        <div class="mb-3">
                            <label for="student_number" class="form-label">学生番号:</label>
                            <input type="text" class="form-control" id="student_number" name="student_number" placeholder="学生番号を入力" required>
                        </div>
                        <button type="submit" class="btn btn-primary">検索</button>
                    </form>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger mt-3" role="alert">
                            ${errorMessage}
                        </div>
                    </c:if>

                    <c:if test="${not empty tests}">
                        <table class="table table-bordered mt-3">
                            <thead>
                                <tr>
                                    <th>生徒番号</th>
                                    <th>クラス</th>
                                    <th>テスト番号</th>
                                    <th>点数</th>
                                    <th>科目名</th>
                                    <th>学校名</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <td>${test.student.no}</td>
                                        <td>${test.classNum}</td>
                                        <td>${test.no}</td>
                                        <td>${test.point}</td>
                                        <td>${test.subject.name}</td>
                                        <td>${test.school.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </main>
            </div>
        </div>
    </c:param>
</c:import>
