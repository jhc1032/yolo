<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#dplist {
	text-align: center;
}
#thead tr th {
	text-align: center;
}
.frame {
	width: 270px;
	display: inline-block;
}
</style>


<script type="text/javascript">
// modal open
function openModal(ccode){
	$('#classcode').val(ccode);
	$('#deletemodal').modal('show');
}

</script>

</head>
<body>
	<!--  					-->
	<!--  					-->
	<!--  					-->
	<!-- 과목 등록 & 조회 	-->
	<!--  					-->
	<!--  					-->
	<!--  					-->
	
	<!-- Tab 선택 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="#home " data-toggle="tab">교육과정 조회</a></li>
		<li><a href="#cregister" data-toggle="tab">교육과정 등록</a></li>
	</ul>
	
	<!-- tab안에 들어가는 내용 -->
	<div id="myTabContent" class="tab-content">	
		<!--  					-->
		<!--  					-->
		<!--  					-->
		<!-- 과목 조회 tab		-->
		<!--  					-->
		<!--  					-->
		<!--  					-->
		<div class="tab-pane fade active in" id="home">
			<form method="get" action="classCheck.do">
				<div style="overflow: scroll; height: 550px;">
					<table class="table table-striped table-hover ">
						<thead id="thead">
							<tr style="color: #b94a48;">
								<th>#</th>
								<th>코드</th>
								<th>교육과정명</th>
								<th>교육기간(h)</th>
								<th>학점(weeks)</th>
								<th>수정</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${classinfo}">
								<tr id="dplist" class="active">
									<td><a href="#">#</a></td>
									<td><a href="#">${list.ccode}</a></a></td>
									<td><a href="#">${list.ctitle}</a></td>
									<td><a href="#">${list.chour}</a></td>
									<td><a href="#">${list.cscore}</a></td>
									<td><a href="classUpdateForm.do?ccode=${list.ccode}" class="btn btn-primary">수정</a></td>
									<%-- <td><a href="classDelete.do?ccode=${list.ccode}">Delete</a></td> --%>	
									<td><a href="#" onclick="openModal(${list.ccode})" class="btn btn-primary">삭제</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="form-group">
					<div class="col-lg-10">
						<button type="submit" class="btn btn-primary">Check</button>
					</div>
				</div>
			</form>
		</div>
		
		<!--  					-->
		<!--  					-->
		<!--  					-->
		<!-- 과목 등록 tab		-->
		<!--  					-->
		<!--  					-->
		<!--  					-->
		<div class="tab-pane fade" id="cregister">
			<form method="get" action="register.do" style="border: 10px; border-color: orange;">
				<div class="col-lg-10" style="overflow: scroll; height: 550px;">			
					<div class="form-group has-error frame">
						<label class="control-label" for="inputError">교육과정 분류</label> 
						<select class="form-control" name='ccode' id='ccode'>
							<option>전체</option>
							<option value="0">전산(00000)</option>
							<option value="10000">사무(10000)</option>
							<option value="20000">통신(20000)</option>
							<option value="30000">기타(30000)</option>
						</select>
					</div>
					<div class="form-group has-error frame">
						<label class="control-label" for="inputError">교육과정</label> 
						<input type="text" class="form-control" name='ctitle' id='ctitle'>
					</div>	
					<div class="form-group has-error frame" style="width: 100px;">
						<label class="control-label" for="inputError">교육기간(h)</label> 
							<select
								class="form-control" name='chour' id='chour'>
								<option>8</option>
								<option>16</option>
								<option>24</option>
								<option>32</option>
								<option>40</option>
								<option>48</option>
								<option>56</option>
								<option>64</option>
								<option>72</option>
								<option>80</option>
								<option>120</option>
								<option>160</option>		
							</select>
					</div>
					<div class="form-group has-error frame" style="width: 100px;">
						<label class="control-label" for="inputError">과정배점</label> 
						<select
							class="form-control" name='cscore' id='cscore'>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<!-- 모달 창  -->
	<!-- 모달 창  -->
	<!-- 모달 창  -->
	<!-- 모달 창  -->
	<!-- 모달 창  -->
	<!-- 모달 창  -->
	<div class="modal" id="deletemodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h2 class="modal-title">정말 삭제하시겠습니까?</h2>
				</div>
				<form method="get" action="classDelete.do">
					<input type="hidden" id="classcode" name="ccode" />
					<div class="modal-body">
						<p>등록된 교육과정을 삭제합니다.</p>	
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary">삭제</button>
					</div>
				</form>	
			</div>
		</div>
	</div>
</body>
</html>