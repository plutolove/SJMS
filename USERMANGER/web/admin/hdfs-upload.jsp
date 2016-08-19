<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<form action="http://172.19.142.65:8080/uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="FILE">
    <input type="hidden" name="userid" value="${user_id}">
    <input type="hidden" name="full_path" value="${path}">
    <input type="submit">
</form>

</body>
</html>
