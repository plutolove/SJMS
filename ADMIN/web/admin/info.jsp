<%@ page import="org.ahu.edu.bigdatalab.scm.model.Admin" %>
<%@ page import="org.ahu.edu.bigdatalab.scm.model.Note" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: WangJiang
  Date: 2016/7/11
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminSJMS | 系统信息</title>
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
    List<Note> notesList = Note.DAO.getTop5Notes();
%>

<!-- Site wrapper -->
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">SJMS</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Admin</b>SJMS</span>
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
                                        <a href="sys-note-view.jsp?id=<%=note.get(Note.id)%>">
                                            <i class="<%=iconClass%>"></i> <%=note.get(Note.title)%>
                                        </a>
                                    </li>
                                    <% } %>
                                </ul>
                            </li>
                            <li class="footer"><a href="sys-note.jsp">查看所有</a></li>
                        </ul>
                    </li>

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/upload/photo/<%=admin.get(Admin.photo)%>" class="user-image" alt="User Image">
                            <span class="hidden-xs"><%=admin.get(Admin.name)%></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/upload/photo/<%=admin.get(Admin.photo)%>" class="img-circle" alt="User Image"/>
                                <p><font size="5"><%=admin.get(Admin.name)%></font></p>
                            </li>
                            <!-- Menu Body -->

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="javascript:selfInfo()" class="btn btn-default btn-flat">资料</a>
                                </div>
                                <div class="pull-right">
                                    <a href="../logout" class="btn btn-default btn-flat">注销</a>
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

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/upload/photo/<%=admin.get(Admin.photo)%>" class="img-circle" alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <h4><%=admin.get(Admin.name)%></h4>
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">集群管理</li>
                <li>
                    <a href="index">
                        <i class="fa fa-dashboard"></i> <span>集群概览</span>
                    </a>
                </li>
                <li>
                    <a href="cluster">
                        <i class="fa fa-list-ul"></i> <span>节点列表</span>
                    </a>
                </li>
                <li class="header">用户</li>
                <li>
                    <a href="user">
                        <i class="fa fa-user"></i> <span>用户管理</span>
                    </a>
                </li>
                <li>
                    <a href="job">
                        <i class="fa fa-table"></i> <span>任务管理</span>
                    </a>
                </li>
                <li>
                    <a href="hdfs">
                        <i class="fa fa-folder"></i> <span>HDFS管理</span>
                    </a>
                </li>
                <li class="header">系统管理</li>
                <li>
                    <a href="sys">
                        <i class="fa fa-cog"></i> <span>系统设置</span>
                    </a>
                </li>
                <li>
                    <a href="admin">
                        <i class="fa fa-user-secret"></i> <span>管理员设置</span>
                    </a>
                </li>
                <li class="active">
                    <a href="info">
                        <i class="fa fa-info-circle"></i> <span>系统信息</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                系统信息
                <small>显示系统相关信息</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-dashboard"></i> 系统管理</li>
                <li class="active">系统信息</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="content body">

                <section id="introduction">
                    <h2 class="page-header"><a href="#introduction">介绍</a></h2>
                    <p class="lead">
                        <b>AdminSJMS(Admin Spark Job Manager System)</b> 是一个基于Spark的Job管理软件，你可以通过它管理Spark平台的任务和所有节点。<br />
                        本软件基于MIT协议开源
                    </p>
                </section><!-- /#introduction -->

                <section id="implementations">
                    <h2 class="page-header"><a href="#implementations">构建</a></h2>
                    <p class="lead">本软件基于了一些开源的软件，在这里感谢开源社区的力量。下面是我们使用的一部分开源软件:</p>

                    <ul>
                        <li><a href="https://github.com/jquery/jquery">Jquery</a> by <a href="https://github.com/jquery" target="_blank">jQuery Foundation</a></li>
                        <li><a href="https://github.com/flot/flot" target="_blank">Flot</a> by <a href="https://github.com/flot" target="_blank">Flot</a></li>
                        <li><a href="https://github.com/almasaeed2010/AdminLTE" target="_blank">admin-lte</a> by <a href="https://github.com/almasaeed2010" target="_blank">Abdullah Almsaeed</a></li>
                        <li><a href="https://github.com/hyperic/sigar" target="_blank">sigar</a> by <a href="https://github.com/hyperic" target="_blank">Hyperic</a></li>
                    </ul>

                    <p><b class="text-red">注意:</b> 如果你希望使用上面的开源软件，请仔细阅读他们的开源许可。</p>
                </section>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="box box-primary">
                            <div class="box-header with-border">
                                <h3 class="box-title">静态页面</h3>
                                <span class="label label-primary pull-right"><i class="fa fa-html5"></i></span>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <p>基于AdminLTE模板开发的静态页面以及相关的资源文件</p>
                                <a href="#" class="btn btn-primary"><i class="fa fa-download"></i> 下 载 </a>
                            </div><!-- /.box-body -->
                        </div><!-- /.box -->
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <div class="box box-danger">
                            <div class="box-header with-border">
                                <h3 class="box-title">完整的项目</h3>
                                <span class="label label-danger pull-right"><i class="fa fa-database"></i></span>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <p>完整的项目，包括源代码以及数据库文件。<b>基于IDEA开发，数据库是MySQL</b></p>
                                <a href="#" class="btn btn-danger"><i class="fa fa-download"></i> 下 载 </a>
                            </div><!-- /.box-body -->
                        </div><!-- /.box -->
                    </div><!-- /.col -->
                </div>

                <!-- ============================================================= -->

                <section id="license">
                    <h2 class="page-header"><a href="#license">许可</a></h2>
                    <h3>AdminLTE</h3>
                    <p class="lead">
                        AdminSJMS是一个基于<a href="http://opensource.org/licenses/MIT">MIT license</a>的开源软件，在下面你可以看到本软件的完整的开源协议
					    <pre>
            The MIT License (MIT)

            Copyright (c) 2016 bigdata.ahu.edu.cn

            Permission is hereby granted, free of charge, to any person obtaining a copy
            of this software and associated documentation files (the "Software"), to deal
            in the Software without restriction, including without limitation the rights
            to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
            copies of the Software, and to permit persons to whom the Software is
            furnished to do so, subject to the following conditions:

            The above copyright notice and this permission notice shall be included in all
            copies or substantial portions of the Software.

            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
            IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
            FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
            AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
            LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
            OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
            SOFTWARE.
							</pre>
                    </p>
                </section>

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
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>

<!--对话框-->
<script type="text/javascript" src="../layer/layer.js" ></script>
<script type="text/javascript" src="../js/dialog.js" ></script>
</body>
</html>

