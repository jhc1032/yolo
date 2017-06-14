<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과목별 수강생</title>


<style type="text/css">
.form-control {
	width: 60px;
	height: 25px;
	padding-left: 10px;
}

.form-group {
	padding-left: 25px;
}

th {
	text-align: center;
}

td {
	text-align: center;
}
</style>
</head>

<body>
	<form>

		<div id="drop">
			<ul class="nav nav-pills">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#" aria-expanded="false"> 과목선택 <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:forEach var="subjects" items="${slist}" varStatus="i" begin="0">
						<li><a href="insertGradeForm.do?cscore=${subjects.cscore}&createcode=${subjects.createcode }&ctitle=${subjects.ctitle }">${subjects.ctitle}</a></li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<span>${ctitle}</span>

		<div>
			<table class="table table-striped table-hover">
				<thead>
					<tr class="warning">
						<th>아이디</th>
						<th>이름</th>
						<th>입력여부</th>
						<th>총점</th>
						<th></th>
						<!-- <th>퀴즈</th>
						<th>과제</th>
						<th>시험</th>
						<th>태도</th>
						<th>총점</th> -->

					</tr>
				</thead>
				<tbody>
					<c:forEach var="mlist" items="${mlist}">
						<tr>
							<td>${mlist.id }</td>
							<td>${mlist.name }</td>
							<c:choose>
								<c:when test="${mlist.score != null && mlist.score != 0 }">
								<td>완료</td> 
								<td>${mlist.score}</td>
								<td><a href="insertGrade.do?id=${mlist.id }&createcode=${createcode}&cscore=${cscore}"
										class="btn btn-primary btn-sm">수정</a></td>
								</c:when>
								<c:otherwise>
								<td>노완료</td>
								<td>${mlist.score }</td>
								<td><a href="insertGrade.do?id=${mlist.id }&createcode=${createcode}&cscore=${cscore}"
										class="btn btn-primary btn-sm">입력</a></td>
								</c:otherwise>
							</c:choose>

						</tr>

					</c:forEach>

				</tbody>

			</table>

		</div>
	</form>
</body>
</html>