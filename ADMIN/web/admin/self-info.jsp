<%@ page import="org.ahu.edu.bigdatalab.scm.model.Admin" %><%--
  Created by IntelliJ IDEA.
  User: WangJiang
  Date: 2016/7/11
  Time: 13:32
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
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
   folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">

<%
    Admin admin = (Admin)request.getSession().getAttribute("admin");
%>

<div class="row">
    <!-- /.col -->
    <div class="col-md-12">
        <!-- Widget: user widget style 1 -->
        <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-black" style="background: url('/img/self_info_bg.jpg') center center;">
                <h3 class="widget-user-username"><%=admin.get(Admin.name)%></h3>
            </div>
            <div class="widget-user-image">
                <img class="img-circle" src="/upload/photo/<%=admin.get(Admin.photo)%>" id="preview" style="cursor:pointer;">
            </div>
            <div class="box-footer">
                <div class="row">

                    <form action="admin/editSelf" enctype="multipart/form-data" method="post" id="myForm">

                        <div style="display: none;">
                            <input type="file" name="photo" id="photo" onchange="previewImage(this)" accept="image/*"/>
                        </div>

                        <div class="box-body">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" placeholder="用户名" value="<%=admin.get(Admin.name)%>" id="name" name="name">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" class="form-control" placeholder="邮箱" disabled="disabled" value="<%=admin.get(Admin.email)%>" id="email" name="email">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-unlock-alt"></i></span>
                                <input type="password" class="form-control" placeholder="密码（如果不修改请留空）" name="password" id="password">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                                <input type="tel" class="form-control" placeholder="电话号码" value="<%=admin.get(Admin.number)%>" name="number" id="number">
                            </div>
                            <br>

                        </div>
                        <!-- /.box-body -->
                    </form>
                </div>
                <!-- /.row -->
            </div>
        </div>
        <!-- /.widget-user -->
    </div>
    <!-- /.col -->

</div>

<!--预览图片的-->
<script type="text/javascript" src="../plugins/jQuery/jquery-2.2.3.min.js" ></script>
<script type="text/javascript" src="../js/image.preview.js" ></script>
</body>
</html>

