<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

            <!-- フォームのアクションとメソッドを指定 -->
            <form action="StudentUpdateExecute.action" method="post">
                <div class="row mx-3 my-0 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-lg-12">

                        <!-- 入学年度の入力フィールド -->
                        <label class="form-label" for="entyear">入学年度</label><br>
                        <!-- 入力フィールドに name 属性を追加 -->
                        <input type="text" id="entyear" name="entyear" value="${entyear}" size="84"><br><br>

                        <!-- 学生番号の入力フィールド -->
                        <label class="form-label" for="no">学生番号</label><br>
                        <!-- 入力フィールドに name 属性を追加 -->
                        <input type="text" id="no" name="no" value="${no}" size="84"><br><br>

                        <!-- 氏名の入力フィールド -->
                        <label class="form-label" for="name">氏名</label><br>
                        <!-- 入力フィールドに name 属性を追加し、maxlength 属性で10文字に制限 -->
                        <input type="text" id="name" name="name" maxlength="10" value="${name}" size="84" placeholder="氏名を入力してください" required><br><br>

                        <!-- クラスの選択フィールド -->
                        <label class="form-label" for="class_num">クラス</label><br>
                        <!-- 選択フィールドに name 属性を追加 -->
                        <select class="form-select" id="class_num" name="class_num">
                            <option value="0">--------</option>
                            <option value="101">101</option>
                            <option value="102">102</option>
                            <option value="201">201</option>
                            <option value="202">202</option>
                            <!-- クラス番号のリストを動的に生成 -->
                            <c:forEach var="num" items="${class_num_set}">
                                <!-- 選択されているクラス番号を保持 -->
                                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                        <br><br>


</div>

					<label class="form-label" for="name">氏名</label><br>
					<input class="form-control-plaintext ps-3" type="text" name="name" id = "name" readonly value="${name}"><br><br>

                        <!-- 在学中のチェックボックス -->
                        在学中<input type="checkbox" name="is_attend" value="true" <c:if test="${is_attend}">checked</c:if>><br><br>

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

                        <!-- 送信ボタン -->
                        <button type="submit">変更</button><br><br>
                        <!-- 戻るリンク -->
                        <a href="StudentList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>


</c:import>
