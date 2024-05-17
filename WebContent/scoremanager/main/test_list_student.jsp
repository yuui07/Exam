<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績参照</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
    <header>
        <c:import url="/common/header.jsp"/>
    </header>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <c:import url="/common/navigation.jsp"/>
            </nav>

            <!-- Main content -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">成績参照システム</h1>
                </div>
                <form method="post" action="searchGrades.jsp">
                    <div class="mb-3">
                        <label for="year" class="form-label">入学年度:</label>
                        <input type="text" class="form-control" id="year" name="year" placeholder="入学年度を入力" required>
                    </div>
                    <div class="mb-3">
                        <label for="class" class="form-label">クラス:</label>
                        <input type="text" class="form-control" id="class" name="class" placeholder="クラスを入力" required>
                    </div>
                    <div class="mb-3">
                        <label for="subject" class="form-label">科目:</label>
                        <input type="text" class="form-control" id="subject" name="subject" placeholder="科目を入力" required>
                    </div>
                    <button type="submit" class="btn btn-primary">検索</button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>

                <c:if test="${not empty students}">
                    <table class="table table-bordered mt-3">
                        <thead>
                            <tr>
                                <th>生徒番号</th>
                                <th>名前</th>
                                <th>成績</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.studentId}</td>
                                    <td>${student.name}</td>
                                    <td>${student.grade}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </main>
        </div>
    </div>

    <footer class="footer mt-auto py-3 bg-light">
        <c:import url="/common/footer.jsp"/>
    </footer>
</body>
</html>