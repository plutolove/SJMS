<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String fullPath = request.getParameter("full_path");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

    <link rel="stylesheet" href="webuploader/webuploader.css">
    <link rel="stylesheet" href="css/webuploader.css">
</head>
<body>

<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>

<script>
    //上传的URL及参数
    var uploadUrl = "../uploadFile?full_path=<%=(fullPath + "/").replace("//", "/")%>";
</script>

<!-- jQuery 2.2.3 -->
<script src="jQuery/jquery-2.2.3.min.js"></script>
<script src="webuploader/webuploader.min.js"></script>
<script src="js/uploader.js"></script>
</body>
</html>
