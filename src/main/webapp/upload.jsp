<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文件上传</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">

		<form class="form-inline" role="form" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath }/UploadFileServlet">
		  <div style="margin:50px auto;text-align:center;">
		  <div class="form-group">
		    <div class="input-group">
		      <label class="sr-only" for="inputfile">文件选择</label>
			  <input type="file" id="inputfile" name="file">
		    </div>
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		  	<div class="form-group">
			  
			</div>
			
		  </div>
		</form>
	
	
		<c:if test="${upload_msg != null }">
		<!-- 信息框 -->
	  	<div class="alert alert-${upload_type } alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" >
		  	<span>&times;</span>
		  </button>
		   <strong>${upload_msg }</strong>
		   <p><a href="${upload_path }" target="_blank">戳我查看刚才上传的文件</a></p>
		</div>
		</c:if>
	</div>
	
</body>
</html>