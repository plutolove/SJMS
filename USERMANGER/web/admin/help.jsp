<%@ page import="java.util.List" %>
<%@ page import="org.ahu.edu.bigdatalab.sum.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>UserSJMS | 系统帮助</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
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

    <!--描述：html视频播放相关-->
   <%-- <link rel="stylesheet" type="text/css" href="/videoplayer/css/reset.css"/>--%>
    <link rel="stylesheet" type="text/css" href="/videoplayer/css/willesPlay.css"/>
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
                <li>
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
                <li class="active">
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
                FAQ
                <small>系统帮助</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-dashboard"></i> 系统消息</li>
                <li class="active">FAQ</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">系统简介</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <p>UserSJMS(User Spark Job Manager System) 是一个基于Spark的Job管理软件，你可以通过它提交自己的Spark任务到我们的Spark集群，同时系统提供了HDFS(Hadoop Distributed File System)管理系统，你也可以通过它来管理你的HDFS上的文件。</p>
                    <p>出于各种因素考虑，本页面使用了HTML5，因此对低版本IE不兼容，为了保障你的用户体验，请使用支持HTML5的浏览器，如Chrome,FireFox,360安全浏览器(请不要使用兼容模式)等</p>
                </div><!-- /.box-body -->
            </div>

            <h3>只提供Linux开发环境所需要的软件</h3>
            <div class="row">
                <div class="col-md-3">
                    <div class="box box-default ">
                        <div class="box-header with-border">
                            <h3 class="box-title">JDK下载</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            </div>
                            <!-- /.box-tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body" style="display: block;">
                            <%-- <a href="/static/software/scala-2.10.4.msi" class="btn btn-primary"><i class="fa fa-windows"></i> 下 载 </a>--%>
                            <a href="/static/software/jdk-7u79-linux-x64.gz" class="btn btn-success"><i class="fa fa-linux"></i> 下 载 </a>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="box box-default ">
                        <div class="box-header with-border">
                            <h3 class="box-title">Scala-SDK</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            </div>
                            <!-- /.box-tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body" style="display: block;">
                           <%-- <a href="/static/software/scala-2.10.4.msi" class="btn btn-primary"><i class="fa fa-windows"></i> 下 载 </a>--%>
                            <a href="/static/software/scala-2.10.4.tgz" class="btn btn-success"><i class="fa fa-linux"></i> 下 载 </a>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="box box-default ">
                        <div class="box-header with-border">
                            <h3 class="box-title">IntelliJ IDEA</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            </div>
                            <!-- /.box-tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body" style="display: block;">
                           <%-- <a href="/static/software/ideaIC-2016.2.1.exe" class="btn btn-primary"><i class="fa fa-windows"></i> 下 载 </a>--%>
                            <a href="/static/software/ideaIC-2016.2.1.tar.gz" class="btn btn-success"><i class="fa fa-linux"></i> 下 载 </a>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="box box-default ">
                        <div class="box-header with-border">
                            <h3 class="box-title">Spark</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            </div>
                            <!-- /.box-tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body" style="display: block;">
                            <a href="/static/software/spark-1.4.0-bin-hadoop1.tgz" class="btn btn-success"><i class="fa fa-linux"></i> 下 载 </a>
                        </div>
                    </div>
                </div>

            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">一些常用的在线文档</h3>
                    <div class="box-tools pull-right">
                        <!-- Buttons, labels, and many other things can be placed here! -->
                    </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body">
                    <a href="https://spark.apache.org/docs/1.4.0/" target="_blank" style="font-size: 20px;"> Spark Document </a><br />
                    <a href="https://github.com/apache/spark" target="_blank" style="font-size: 20px;"> Spark源码</a><br />
                    <a href="http://www.scala-lang.org/api/current/" target="_blank" style="font-size: 20px;"> Scala Document</a><br />
                    <a href="/static/book/Programming.Scala.pdf" target="_blank" style="font-size: 20px;"> Programming Scala PDF下载</a><br />
                    <a href="/static/book/programing.in.scala.pdf" target="_blank" style="font-size: 20px;"> Programing In Scala PDF下载</a><br />
                    <a href="#" target="_blank" style="font-size: 20px;"> Spark源码</a><br />

                </div>
                <div class="box-footer">
                </div><!-- box-footer -->
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">重要学术会议主页</h3>
                    <div class="box-tools pull-right">
                        <!-- Buttons, labels, and many other things can be placed here! -->
                    </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body">
                    <a href="http://www.ccfccscw.cn/" target="_blank" style="font-size: 20px;"> 第11届全国计算机支持的协同工作学术会议 </a><br>
                    <a href="http://www.ccf-ncsc.org.cn/" target="_blank" style="font-size: 20px;"> 第七届中国计算机学会服务计算学术会议</a><br>
                    <a href="http://www2016.ca/" target="_blank" style="font-size: 20px;"> WWW2016</a><br>
                    <a href="http://icws.org/2016/" target="_blank" style="font-size: 20px;"> ICWS2016</a><br>
                    <a href="http://www.www2017.com.au/" target="_blank" style="font-size: 20px;"> WWW2017</a><br>
                    <a href="http://cscw.acm.org/2016/index.php" target="_blank" style="font-size: 20px;"> CSCW2016  A类会议</a><br>
                    <a href="http://cscw.acm.org/2017/" target="_blank" style="font-size: 20px;"> CSCW2017</a><br>
                    <a href="http://www.aaai.org/Conferences/AAAI/aaai16.php" target="_blank" style="font-size: 20px;"> AAAI2016</a><br>
                    <a href="http://www.aaai.org/Conferences/AAAI/aaai17.php" target="_blank" style="font-size: 20px;"> AAAI2017</a><br>
                    <a href="http://www.sigmod2016.org/" target="_blank" style="font-size: 20px;"> Sigmod2016</a><br>
                    <a href="http://sigmod2017.org/" target="_blank" style="font-size: 20px;"> sigmod2017</a><br>
                    <a href="http://cs.adelaide.edu.au/~icsoc2016/index.html" target="_blank" style="font-size: 20px;">  ICSOC2016</a><br>
                    <a href="http://www.ccf.org.cn/sites/ccf/paiming.jsp" target="_blank" style="font-size: 20px;"> CCF推荐期刊和会议</a><br>
                    <a href="http://2016.icse.cs.txstate.edu/" target="_blank" style="font-size: 20px;">  ICSE2016（软件工程A类）</a><br>

                </div>
                <div class="box-footer">
                </div><!-- box-footer -->
            </div>

            <div style="margin: 0 40px;">
                <div class="row">
                    <div class="col-md-12">
                        <div id="willesPlay">
                            <div class="playHeader">
                                <div class="videoName">系统使用帮助视频</div>
                            </div>
                            <div class="playContent">
                                <div class="turnoff">
                                    <ul>
                                        <li><a href="javascript:;" title="关灯" class="btnLight on glyphicon glyphicon-sunglasses"></a></li>
                                    </ul>
                                </div>
                                <video width="100%" height="100%" id="playVideo">
                                    <source src="/static/video/demo.mp4" type="video/mp4"/>
                                    当前浏览器不支持 video直接播放，点击这里下载视频： <a href="/">下载视频</a>
                                </video>
                                <div class="playTip glyphicon glyphicon-play"></div>
                            </div>
                            <div class="playControll">
                                <div class="playPause playIcon"></div>
                                <div class="timebar">
                                    <span class="currentTime">0:00:00</span>
                                    <div class="progress">
                                        <div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>
                                    </div>
                                    <span class="duration">0:00:00</span>
                                </div>
                                <div class="otherControl">
                                    <span class="volume glyphicon glyphicon-volume-down"></span>
                                    <span class="fullScreen glyphicon glyphicon-fullscreen"></span>
                                    <div class="volumeBar">
                                        <div class="volumewrap">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 8px;height: 40%;"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
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
<script src="/layer/layer.js"></script>
<script src="/js/dialog.js"></script>

<!--在线视频播放插件-->
<script src="/videoplayer/js/willesPlay.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
