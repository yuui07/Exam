<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>

</body>
</html>
<c:import url="/common/base.jsp">
    <c:param name="title">
        科目情報登録
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h2 mb-3" style="background-color: #f2f2f2; padding: 10px;">科目情報登録</h2>
            <form action="SubjectRegister.action" method="post">
                <div class="mb-3">
                    <label for="cd" class="form-label">科目コード</label>
                    <input type="text" class="form-control" id="cd" name="cd" value="${cd}" maxlength="3" required placeholder="科目コードを入力してください" style="color: #666;">
                    <div class="error-message" id="cd-error"></div>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">科目名</label>
                    <input type="text" class="form-control" id="name" name="name" value="${name}" maxlength="20" required placeholder="科目名を入力してください" style="color: #666;">
                    <div class="error-message" id="name-error"></div>
                </div>
                <button type="submit" class="btn btn-primary rounded-pill" style="background-color: #007bff; color: white;">登録</button>
            </form>
            <div class="mt-3">
                <a href="SubjectManagement.action">戻る</a>
            </div>
        </section>
        <div class="error-message" id="general-error"></div>
    </c:param>
</c:import>