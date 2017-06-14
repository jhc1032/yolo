<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
<script type="text/javascript">
$(function(){
	$("#addFile").click(addFileForm);
});
var count=0;   //file form index
function addFileForm(){
	var html="<div id='item_"+count+"'>"
	html+="<input type='file' name='fileup' />";
	html+="<input type='button' value='삭제' onclick='removeForm("+count+")'/></div>";
	count++;
	//html=$("#content").html()+html;
	//$("#content").html(html);
	$("#fileUpForm").append(html);
}
function removeForm(count){
	var item = document.getElementById('item_'+count);
	if(item !=null) item.parentNode.removeChild(item);
}
</script>
</head>
<body>
<form method="post" action="insertQnaBoard.do" class="form-horizontal">
		<fieldset>
			<legend style="text-align: center">Write Q&A Board</legend>
			<div class="form-group">
				<label for="inputTitle" class="col-lg-2 control-label">Title</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label for="inputId" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-10" style="padding-top: 8px">
					<input type="hidden" class="form-control" id="id" name="id"
						value="<%=session.getAttribute("id")%>" /> <span
						style="padding-top: 5px;"><%=session.getAttribute("id")%></span>
				</div>
			</div>
			<div class="form-group">
				<label for="textArea" class="col-lg-2 control-label">Contents</label>
				<div class="col-lg-10">
					<textarea name="contents" id="contents" class="form-control"
						rows="3" id="textArea"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<button type="reset" class="btn btn-default">Cancel</button>
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>