<%-- 科目情報削除完了 --%>
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
<c:import url="/common/base.jsp">
    <c:param name="title">
        科目情報削除
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h2 mb-3" style="background-color: #f2f2f2; padding: 10px;">科目情報削除</h2>
            <div class="label" style="color: black; background-color: #aad4aa; text-align: center;">削除が完了しました。</div>
            <div class="d-flex justify-content-center mt-3">
                <div class="me-3">
                </div>
                <div>
                </div>
                </div>
                   <a href="subjectlist.jsp">科目一覧</a>
        </section>
    </c:param>
</c:import>
