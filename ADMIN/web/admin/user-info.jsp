<%@ page import="org.ahu.edu.bigdatalab.scm.model.User" %>
<%@ page import="org.ahu.edu.bigdatalab.scm.util.TestUtil" %><%--
  Created by IntelliJ IDEA.
  User: WangJiang
  Date: 2016/7/11
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminSJMS | 集群概览</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
   folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">

<%
    User user = User.DAO.findById(request.getParameter("id"));
    if (user == null) {
        response.sendRedirect("/404.html");
        TestUtil.log("NULL");
        return;     //阻止下面的页面继续渲染
    }
%>

<div class="row">
    <!-- /.col -->
    <div class="col-md-12">
        <!-- Widget: user widget style 1 -->
        <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-aqua-active">
                <h3 class="widget-user-username"><%=user.get(User.name)%></h3>
                <h5 class="widget-user-desc">学生</h5>
            </div>
            <div class="widget-user-image">
                <img class="img-circle" src="/upload/admin_photo.jpg" alt="User Avatar">
            </div>
            <div class="box-footer">
                <div class="row">

                    <div class="box-body">
                        <div class="input-group">
                            <span class="input-group-addon">邮箱</span>
                            <input type="email" class="form-control" readonly="readonly" value="<%=user.get(User.email)%>">
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">电话</span>
                            <input type="tel" class="form-control" readonly="readonly" value="未使用该字段">
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">注册</span>
                            <input type="text" class="form-control" readonly="readonly" value="未使用该字段">
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">登录</span>
                            <input type="text" class="form-control" readonly="readonly" value="<%=user.get(User.lastLoginTime)%>">
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">状态</span>
                            <input type="text" class="form-control" readonly="readonly" value="离线(禁止登陆)未使用该字段">
                        </div>
                        <br>

                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.row -->
            </div>
        </div>
        <!-- /.widget-user -->
    </div>
    <!-- /.col -->

</div>

</body>
</html>

