<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>UserSJMS | 个人资料</title>
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

<div class="row">
    <!-- /.col -->
    <div class="col-md-12">
        <!-- Widget: user widget style 1 -->
        <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-black" style="background: url('/img/self_info_bg.jpg') center center;">

            </div>
            <div class="widget-user-image">
                <img class="img-circle" src="${user.thumbnail}" id="preview" style="cursor:pointer;">
            </div>
            <div class="box-footer">
                <div class="row">

                    <form action="/admin/user/editP" enctype="multipart/form-data" method="post" id="myForm">

                        <div style="display: none;">
                            <input type="file" name="file" id="photo" onchange="previewImage(this)" accept="image/*"/>
                        </div>

                        <div class="box-body">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-paper-plane"></i></span>
                                <input type="text" class="form-control" placeholder="用户名" disabled="disabled" value="${user.username}" id="username" name="username">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" class="form-control" placeholder="邮箱" value="${user.email}" id="email" name="email">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="tel" class="form-control" placeholder="昵称" value="${user.nickname}" name="nickname" id="nickname">
                            </div>
                            <br>

                            <%--<button type="button" onclick="javascript:submitForm();" class="btn btn-primary" id="save">保存修改</button>--%>
                        </div>
                        <!-- /.box-body -->
                    </form>
                </div>

                <a href="/admin/user/change_password"> 修改密码 </a>
                <!-- /.row -->
            </div>
        </div>
        <!-- /.widget-user -->
    </div>
    <!-- /.col -->

</div>

<!--预览图片的-->
<script type="text/javascript" src="/plugins/jQuery/jquery-2.2.3.min.js" ></script>
<script type="text/javascript" src="/js/image.preview.js" ></script>
<script src="/js/jquery.form.js"></script>
</body>
</html>
