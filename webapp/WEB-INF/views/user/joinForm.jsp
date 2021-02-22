<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="/jqueryex/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr id= "id-chenck">
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
			<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	</div>

</body>
<script type="text/javascript">
	$("#id-chenck").on("click","#btnIdCheck",function(){
		var id = $("#txtId").val();
		console.log(id);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/user/checkid",  //컨트롤러의 url과 파라미터
			type : "post",	// 겟 포스트
			//contentType : "application/json",
			data : { id:id },

			dataType : "json",
			success : function(result){  //성공시
				if(result == 'can'){
					$("#tdMsg").text("사용할 수 있는 아이디입니다.")
				}else if(result=='cant'){
					$("#tdMsg").text("다른 아이디로 가입해 주세요.")
				}else if(result =='fail'){
					$("#tdMsg").text("다른 아이디로 가입해 주세요.")
				}

			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});
		
		
		
	});
	
	
	$("#joinForm").on("submit",function(){
		console.log("하이하이");
		
		var id = $("#txtId").val();
		console.log(id);
		var password = $("#txtPassword").val();
		console.log(password);
		var userName = $("#txtUserName").val();
		console.log(userName);
		var tdMsg = $("#tdMsg").text();
		console.log(tdMsg);
		
		var check = $("#chkAgree").is(":checked");
		console.log(check);
		
		if(id == ''){
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(tdMsg == '' ){
			alert("아이디 중복체크를 해주세요.");
			return false;
		}
		if(tdMsg == '다른 아이디로 가입해 주세요.'){
			alert("사용할 수 없는 아이디입니다");
			
			return false;
		}
		
		if( password == ''){
			alert("패스워드를 입력해주세요.")
			return false;
		}
		if(userName == ''){
			alert("이름을 입력해주세요.");
			return false;
		}
		
		if(!check){
			alert("약관에 동의해 주세요");
			return false;
		}
		
		return true;
	});
	

</script>

</html>