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

                    <form action="/admin/user/change_passwordP" method="post" id="myForm" onsubmit="return checkPassword()">

                        <div class="box-body">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="password" class="form-control" placeholder="输入旧密码" id="old_password" name="old_password">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="password" class="form-control" placeholder="输入新密码" id="new_password" name="new_password">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="password" class="form-control" placeholder="确认密码" id="new_password_check" name="new_password_check">
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
<script type="text/javascript" src="/plugins/jQuery/jquery-2.2.3.min.js" ></script>
<script type="text/javascript" src="/layer/layer.js"></script>
<script src="/js/jquery.form.js"></script>

<script>
    function checkPassword() {
        var newPassword = $("#new_password").val();
        var newPasswordCheck = $("#new_password_check").val();
        var oldPassword =  $("#old_password_check").val();
        if (newPassword != newPasswordCheck) {
            layer.msg("两次输入的密码不同");
           return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>
