<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta  charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
	%>
	<table>
		<tr><td> 아이디 : </td> <td> ${member.id}</td></tr>
		<tr><td> password : </td> <td> ${member.password}</td></tr>
		<tr><td> 이  름 : </td> <td> ${member.name}</td></tr>
		<tr><td> mgroup : </td> <td> ${member.mgroup}</td></tr>
		<tr><td> enteryear : </td> <td>${member.enteryear}</td></tr>
		<tr><td> empno : </td> <td> ${member.empno}</td></tr>
		<tr><td> email : </td> <td>${member.email} </td></tr>
		<tr><td> <a href ="memberUpdateForm.do" >회원 정보 수정</a> </td></tr>
		
				
	</table>
</body>
</html>