<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.ahu.edu.bigdatalab.sum.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>UserSJMS | 任务管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/plugins/datatables/dataTables.bootstrap.css">
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
<div class="wrapper">
    <%
        List<Note> notesList = Note.DAO.getTop5Notes();
    %>
    <header class="main-header">
        <!-- Logo -->
        <a href="" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">SJMS</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>User</b>SJMS</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">共<%=Note.DAO.getCountOfNotes()%>条通告通知</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <%
                                        for (Note note : notesList) {
                                            String iconClass = "fa fa-bell text-aqua";
                                            String content = note.get(Note.content);
                                            if (Note.LOGIN_ENABLE.equals(content)) {
                                                iconClass = "fa fa-user text-green";
                                            } else if (Note.LOGIN_DISABLE.equals(content)) {
                                                iconClass = "fa fa-user-times text-red";
                                            } else if (Note.JOB_ENABLE.equals(content)) {
                                                iconClass = "fa fa-unlock text-green";
                                            } else if (Note.JOB_DISABLE.equals(content)) {
                                                iconClass = "fa fa-lock text-red";
                                            }
                                    %>
                                    <li>
                                        <a href="javascript:showSysNote(<%=note.get(Note.id)%>)">
                                            <i class="<%=iconClass%>"></i> <%=note.get(Note.title)%>
                                        </a>
                                    </li>
                                    <% } %>
                                </ul>
                            </li>
                            <li class="footer"><a href="sysnote">查看所有</a></li>
                        </ul>
                    </li>

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${sessionScope.user.thumbnail}" class="user-image" alt="User Image">
                            <span class="hidden-xs">${sessionScope.user.nickname}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${sessionScope.user.thumbnail}" class="img-circle" alt="User Image"/>
                                <p><font size="5">${sessionScope.user.nickname}</font></p>
                            </li>
                            <!-- Menu Body -->

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="javascript:selfInfo()" class="btn btn-default btn-flat">资料</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/logout" class="btn btn-default btn-flat">注销</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${sessionScope.user.thumbnail}" class="img-circle" alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <h4>${sessionScope.user.nickname}</h4>
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">任务管理</li>
                <%--<li>
                    <a href="/admin">
                        <i class="fa fa-paper-plane"></i> <span>欢迎首页</span>
                    </a>
                </li>--%>
                <li class="active">
                    <a href="/admin/job">
                        <i class="fa fa-table"></i> <span>任务管理</span>
                    </a>
                </li>
                <li class="header">HDFS管理</li>
                <li>
                    <a href="/admin/file">
                        <i class="fa fa-folder"></i> <span>HDFS管理</span>
                    </a>
                </li>
                <li class="header">系统信息</li>
                <li>
                    <a href="/admin/sysnote">
                        <i class="fa fa-info-circle"></i> <span>通告通知</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/help">
                        <i class="fa fa-book"></i> <span>FAQ</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                新增管理
                <small>新增加一个任务</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-dashboard"></i> 任务管理</li>
                <li class="active">任务管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">任务信息</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form role="form" action="/admin/job/job_addP" method="post" id="form" onsubmit="return checkForm()">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="name">任务名</label>
                            <input type="text" name="name" class="form-control" id="name" placeholder="输入任务名，如:测试程序" required/>
                        </div>
                        <div class="form-group">
                            <label for="main_class">主类名</label>
                            <input type="text" name="main_class" class="form-control" id="main_class" placeholder="输入主类名，如:test" required/>
                        </div>
                        <div class="form-group">
                            <label for="memory">内存大小(GB)</label>
                            <input type="number" name="memory" class="form-control" id="memory" placeholder="输入内存大小，如2(表示使用2G内存)，最大值为10" required/>
                        </div>
                        <div class="form-group">
                            <label for="cores">CPU核数</label>
                            <input type="number" name="cores" class="form-control" id="cores" placeholder="输入CPU核数，如6(表示使用6个CPU核)，最大值为10" required/>
                        </div>
                        <div class="form-group">
                            <label for="jar_path">jar包路径</label>
                            <input type="text" class="form-control" id="jar_path" name="jar_path" placeholder="输入jar包的路径" readonly onclick="jobFileChoose(1)" required/>
                        </div>
                        <div class="form-group">
                            <label for="in">数据路径</label>
                            <input type="text" class="form-control" id="in" name="in" placeholder="输入数据的路径(可以选择文件夹作为数据源)" readonly onclick="jobFileChoose(0)" required/>
                        </div>
                        <div class="form-group">
                            <label for="out">结果路径</label>
                            <input type="text" class="form-control" id="out" name="out" placeholder="输入结果的路径" required/>
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-flash"></i> 运行 </button>
                    </div>
                </form>
            </div>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>版本：</b> 0.0.1
        </div>
        <strong>Copyright &copy; 2016 <a href="http://bigdata.ahu.edu.cn/" target="_blank">安徽大学云计算与大数据实验室</a>.</strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-question"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">简单的帮助</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:void(0)">
                            <i class="menu-icon fa fa-book bg-blue"></i>
                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">关于页面设置</h4>
                                <p>如果页面设置出现异常，可以尝试刷新页面或者删除浏览器的cookie数据</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->
            </div>
            <!-- /.tab-pane -->

        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/dist/js/demo.js"></script>

<!--对话框-->
<script type="text/javascript" src="/layer/layer.js" ></script>
<script type="text/javascript" src="/js/dialog.js" ></script>

<script>
    //简单的验证
    function checkForm() {
        var memory = $("#memory").val();
        var cores = $("#cores").val();
        if (memory <= 10 && memory > 0 && cores <= 10 && cores > 0) {
            return true;
        } else {
            layer.msg('内存或CPU参数请填不合理');
            return false;
        }
    }

</script>

</body>
</html>

