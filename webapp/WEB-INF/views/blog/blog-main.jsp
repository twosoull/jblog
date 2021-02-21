<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">

					<c:choose>
						<c:when test="${'no' eq bMap.blogVo.logoFile }">
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>
							<!-- 사용자업로드 이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/upload/${bMap.blogVo.logoFile}">
						</c:otherwise>
					</c:choose>

					<div id="nick">${bMap.blogVo.username}(${bMap.blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">

						<c:forEach items="${bMap.categoryList}" var="vo">
							<li><a href="${pageContext.request.contextPath}/${bMap.blogVo.id}?cateNo=${vo.cateNo}&cateName=${vo.cateName}">${vo.cateName}</a></li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">
				<div id="postBox" class="clearfix">



							<div id="postTitle" class="text-left">
								<strong>등록된 글이 없습니다.</strong>
							</div>
					<!-- 
							<div id="postDate" class="text-left">
								<strong>2020/07/23</strong>
							</div>
							<div id="postNick">${bMap.blogVo.username}(${bMap.blogVo.id})님</div>
						 -->

				</div>
				<div id="post">글이없습니다</div>
				<!-- //postBox -->

				<!-- //post -->

				<!-- 글이 없는 경우 -->
				<!-- 
				<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
				</div>
			    
				<div id="post" >
				</div>
				-->

				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table id="postTable">
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<c:forEach items="${bMap.postList}" var="vo">
							<tr>
								<td id="titles" class="text-left" data-postno="${vo.postNo}"><a href="">${vo.postTitle}</a></td>
								<td class="text-right">${vo.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
	<input id="cate_id" type="hidden" name="id" value="${bMap.blogVo.id}">
	<input id="cate_no" type="hidden" name="cateno" value="${param.cateNo}">
</body>

<script type="text/javascript">
	//첫화면 포스트 글 뿌리기
	$("document").ready(
			function() {
				console.log("준비");

				var id = $("#cate_id").val();
				console.log(id);
				
				var cateNo = $("#cate_no").val();
				console.log(cateNo);
				
				$.ajax({

					url : "${pageContext.request.contextPath }/" + id
							+ "/admin/lastpost", //컨트롤러의 url과 파라미터
					type : "post", // 겟 포스트
					//contentType : "application/json",
					data : {cateNo : cateNo},

					dataType : "json",
					success : function(postVo) { //성공시
						if(postVo == null){
							
						}else{
						console.log(postVo);

						postWrite(postVo);
						}
						//2.post가 없을 경우 뿌리기와  3. 페이지 켜졌을때 뿌려주기도 해줘야함
					},
					error : function(XHR, status, error) { //실패
						console.error(status + " : " + error);
					}
				});
			});
	//포스트 글 뿌리기
	$("#postTable").on("click", "#titles", function() {
		event.preventDefault();

		var postNo = $(this).data("postno");
		console.log(postNo);
		var id = $("#cate_id").val();
		console.log(id);

		$.ajax({

			url : "${pageContext.request.contextPath }/" + id + "/admin/post", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			data : {
				postNo : postNo
			},

			dataType : "json",
			success : function(postVo) { //성공시
				console.log(postVo);

				//1.이제 테이블을 함수로 만들어서 거기에 값을 넣어주고 뿌려주기하면됌
				postWrite(postVo);

				//2.post가 없을 경우 뿌리기와  3. 페이지 켜졌을때 뿌려주기도 해줘야함
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});

	});

	function postWrite(postVo) {
		console.log("하이하이")

		str = "";
		str += '<div id="postTitle" class="text-left">';
		str += '	<strong>' + postVo.postTitle + '</strong>';
		str += '</div>';
		str += '<div id="postDate" class="text-left">';
		str += '	<strong>' + postVo.regDate + '</strong>';
		str += '</div>';
		str += '<div id="postNick">' + postVo.userName + '(' + postVo.id
				+ ')님</div>';

		post = postVo.postContent;

		$("#postBox").html(str);
		$("#post").text(post);
	}
</script>

</html>