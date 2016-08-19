<%@ page import="org.ahu.edu.bigdatalab.scm.model.Admin" %>
<%@ page import="org.ahu.edu.bigdatalab.scm.helper.SparkHelper" %>
<%@ page import="org.ahu.edu.bigdatalab.scm.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="org.ahu.edu.bigdatalab.scm.util.SigarUtil" %><%--
  Created by IntelliJ IDEA.
  User: WangJiang
  Date: 2016/7/10
  Time: 21:19
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
        SparkHelper sparkHelper = new SparkHelper();

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
                    <li class="active">
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
                    <li>
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
                <h1>集群概览<small>显示集群当前的概览情况</small></h1>
                <ol class="breadcrumb">
                    <li><i class="fa fa-dashboard"></i> 集群管理</li>
                    <li class="active">集群概览</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

                <!-- Small boxes (Stat box) -->
                <div class="row">
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-aqua">
                            <div class="inner">
                                <h3><%=sparkHelper.getCountOfAllWorkers()%></h3>
                                <p>集群节点总数</p>
                            </div>
                            <div class="icon">
                                <i class="fa fa-home"></i>
                            </div>
                            <a href="cluster" class="small-box-footer">详细信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <!-- ./col -->
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-green">
                            <div class="inner">
                                <h3><%=sparkHelper.getCountOfEnabledWorkers()%></h3>
                                <p>集群可用节点总数</p>
                            </div>
                            <div class="icon">
                                <i class="fa fa-heartbeat"></i>
                            </div>
                            <a href="cluster" class="small-box-footer">详细信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <!-- ./col -->
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-yellow">
                            <div class="inner">
                                <h3><%=sparkHelper.getCountOfDisabledWorkers()%></h3>
                                <p>断开连接的节点总数</p>
                            </div>
                            <div class="icon">
                                <i class="fa fa-flash"></i>
                            </div>
                            <a href="cluster" class="small-box-footer">详细信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <!-- ./col -->
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-red">
                            <div class="inner">
                                <h3><%=sparkHelper.getCountOfRunningJobs()%></h3>
                                <p>正在运行的任务总数</p>
                            </div>
                            <div class="icon">
                                <i class="fa  fa-terminal"></i>
                            </div>
                            <a href="job" class="small-box-footer">详细信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <!-- ./col -->
                </div>

                <!--
                        作者：1184829149@qq.com
                        时间：2016-07-05
                        描述：显示集群的一些信息
                    -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Spark Master at spark://master:7077</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">

                                <div class="invoice-info">
                                    <div class="col-sm-4 invoice-col">
                                        <h4><strong>Spark <%=sparkHelper.getVersionOfSpark()%></strong></h4><br>
                                        <h4>URL: <%=sparkHelper.getSparkUrl()%></h4>
                                        <h4>REST URL: <%=sparkHelper.getSparkRestUrl()%></h4>
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-4 invoice-col">
                                        <h4><strong>Alive Workers: <%=sparkHelper.getCountOfEnabledWorkers()%></strong></h4><br>
                                        <h4>CPU核数: <%=sparkHelper.getCoresInfo()%></h4>
                                        <h4>内存: <%=sparkHelper.getMemoryInfo()%></h4>
                                        <h4>应用: <%=sparkHelper.getJobsInfo()%></h4>
                                        <h4>驱动: <%=sparkHelper.getDriversInfo()%></h4>
                                        <br />
                                    </div>
                                    <!-- /.col -->
                                    <div class="col-sm-4 invoice-col">
                                        <h4><strong>状态: <%=sparkHelper.getSparkStatus()%></strong></h4>
                                    </div>
                                    <!-- /.col -->
                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                </div>
                <!--显示集群的一些信息-->

                <!-- Main row -->
                <div class="row">
                    <!-- Left col -->
                    <section class="col-lg-7 connectedSortable">
                        <!-- Custom tabs (Charts with tabs)-->

                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">CPU信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="chart">

                                    <!--CPU信息主模块-->
                                    <div id="cpu_info" style="height: 300px;"></div>
                                    <!--CPU信息主模块-->

                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.nav-tabs-custom -->

                        <!-- Chat box -->
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">内存信息</h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i> </button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="chart">

                                    <!--内存信息模块-->
                                    <div id="mem_info" style="height: 300px;"></div>
                                    <!--内存信息模块-->

                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box (chat box) -->
                    </section>
                    <!-- /.Left col -->
                    <!-- right col (We are only adding the ID to make the widgets sortable)-->
                    <section class="col-lg-5 connectedSortable">

                        <!-- Map box -->
                        <div class="box box-solid bg-light-blue-gradient">
                            <div class="box-header">
                                <!-- tools box -->
                                <div class="pull-right box-tools">
                                    <button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse" data-toggle="tooltip" title="Collapse" style="margin-right: 5px;">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <!-- /. tools -->
                                <i class="fa fa-map-marker"></i>
                                <h3 class="box-title">
                                    磁盘信息
                                </h3>
                            </div>
                            <div class="box-body">
                                <div class="chart" id="sales-chart" style="height: 300px; position: relative;">
                                    <!--磁盘信息模块-->
                                    <div id="disk_chart" style="height: 300px;"></div>
                                    <!--磁盘信息模块-->
                                </div>
                            </div>
                            <!-- /.box-body-->
                            <div class="box-footer no-border">
                                <div class="row">
                                    <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                                        <div id="sparkline-1"></div>
                                        <div class="knob-label"> 总共：<%=SigarUtil.getDiskInfo().getOrDefault(SigarUtil.DISK_TOTAL, "0")%>GB</div>
                                    </div>
                                    <!-- ./col -->
                                    <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                                        <div id="sparkline-2"></div>
                                        <div class="knob-label">已用：<%=SigarUtil.getDiskInfo().getOrDefault(SigarUtil.DISK_USED, "0")%>GB</div>
                                    </div>
                                    <!-- ./col -->
                                    <div class="col-xs-4 text-center">
                                        <div id="sparkline-3"></div>
                                        <div class="knob-label">剩余：<%=SigarUtil.getDiskInfo().getOrDefault(SigarUtil.DISK_FREE, "0")%>GB</div>
                                    </div>
                                    <!-- ./col -->
                                </div>
                                <!-- /.row -->
                            </div>
                        </div>
                        <!-- /.box -->

                        <!-- solid sales graph -->
                        <div class="box box-solid bg-teal-gradient">
                            <div class="box-header">
                                <i class="fa fa-th"></i>
                                <h3 class="box-title">主节点信息</h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                            </div>
                            <div class="box-body border-radius-none">
                                <div class="chart" id="line-chart" style="height: 250px;">
                                    <!--Info模块-->
                                    <div class="am-u-md-6">
                                        <div class="am-form am-form-horizontal">
                                            <dl class="dl-horizontal" style="font-size: 22px;">
                                                <dt>操作系统：</dt><dd><%=SigarUtil.getOSInfo().get(SigarUtil.OS_NAME)%></dd>
                                                <dt>系统厂商：</dt><dd><%=SigarUtil.getOSInfo().get(SigarUtil.OS_VENDOR)%></dd>
                                                <dt>系统构架：</dt><dd><%=SigarUtil.getOSInfo().get(SigarUtil.OS_ARCH)%></dd>
                                                <dt>系统版本：</dt><dd><%=SigarUtil.getOSInfo().get(SigarUtil.OS_VERSION)%></dd>
                                                <dt>Java版本：</dt><dd><%=SigarUtil.getOSInfo().get(SigarUtil.OS_JAVA_VERSION)%></dd>
                                            </dl>
                                        </div>
                                    </div>
                                    <!--Info模块-->

                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </section>
                    <!-- right col -->
                </div>
                <!-- /.row (main row) -->
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

    <!-- FLOT CHARTS -->
    <script src="../plugins/flot/jquery.flot.min.js"></script>
    <!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
    <script src="../plugins/flot/jquery.flot.resize.min.js"></script>
    <!-- FLOT PIE PLUGIN - also used to draw donut charts -->
    <script src="../plugins/flot/jquery.flot.pie.min.js"></script>
    <!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
    <script src="../plugins/flot/jquery.flot.categories.min.js"></script>

    <script>
        function getCpuFromServer(dataCpu) {
            $.get("getCpuInfo", function(result){
                console.log(result.data);
                dataCpu.push(result.data);
            });
        }

        function getMemFromServer(dataMem) {
            $.get("getMemInfo", function(result){
                console.log(result.data);
                dataMem.push(result.data);
            });
        }
    </script>

    <script>
        $(function () {
            /*
             * CPU信息
             */
            var dataCpu = [], totalPointsCpu = 60;		//显示60个点的数据（即一分钟内的数据情况）
            function getCpuData() {
                if (dataCpu.length > 0)
                    dataCpu = dataCpu.slice(1);
                //获取数据
                /*while (dataCpu.length < totalPointsCpu) {
                    var prev = dataCpu.length > 0 ? dataCpu[dataCpu.length - 1] : 50,
                            y = prev + Math.random() * 10 - 5;
                    if (y < 0) {
                        y = 0;
                    } else if (y > 100) {
                        y = 100;
                    }
                    dataCpu.push(y);
                }*/
                getCpuFromServer(dataCpu);
                // Zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < dataCpu.length; ++i) {
                    res.push([i, dataCpu[i]]);
                }
                return res;
            }
            /**
             * 初始化cpu的浮动图表，全部赋值为0
             */
            function initCpuChart(){
                // Zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < totalPointsCpu; ++i) {
                    dataCpu.push(0);
                    res.push([i, 0]);
                }
                return res;
            }

            var interactive_plot_cpu = $.plot("#cpu_info", [initCpuChart()], {
                grid: {
                    hoverable:true,     //想要虚浮显示，必须设置true
                    borderColor: "#f3f3f3",
                    borderWidth: 1,
                    tickColor: "#f3f3f3"
                },
                series: {
                    shadowSize: 0, // Drawing is faster without shadows
                    color: "#3c8dbc"
                },
                lines: {
                    fill: true, //Converts the line chart to area chart
                    color: "#3c8dbc"
                },
                yaxis: {
                    min: 0,
                    max: 100,
                    show: true
                },
                xaxis: {
                    show: true
                }
            });

            var updateIntervalCpu = 1000; //1秒钟刷新一次数据
            var realtimeCpu = "on"; //If == to on then fetch data every x seconds. else stop fetching
            function updateCpu() {
                interactive_plot_cpu.setData([getCpuData()]);
                // Since the axes don't change, we don't need to call plot.setupGrid()
                interactive_plot_cpu.draw();
                if (realtimeCpu === "on")
                    setTimeout(updateCpu, updateIntervalCpu);
            }
            //INITIALIZE REALTIME DATA FETCHING
            if (realtimeCpu === "on") {
                updateCpu();
            }
            //REALTIME TOGGLE
            $("#realtime .btn").click(function () {
                if ($(this).data("toggle") === "on") {
                    realtimeCpu = "on";
                }
                else {
                    realtimeCpu = "off";
                }
                updateCpu();
            });

            //鼠标悬浮显示CPU该点的数据
            $('<div class="tooltip-inner" id="cpu_info_tooltip"></div>').css({
                position: "absolute",
                display: "none",
                opacity: 0.8	//透明度
            }).appendTo("body");
            $("#cpu_info").bind("plothover", function (event, pos, item) {
                if (item) {
                    var y = item.datapoint[1].toFixed(2);
                    $("#cpu_info_tooltip").html( "使用率:" + y +"%")
                            .css({top: item.pageY + 5, left: item.pageX + 5})
                            .fadeIn(200);
                } else {
                    $("#cpu_info_tooltip").hide();
                }
            });
            /*
             * CPU信息
             */

            /*
             * mem信息
             */
            var dataMem = [], totalPointsMem = 60;		//显示60个点的数据（即一分钟内的数据情况）
            function getMemData() {
                if (dataMem.length > 0)
                    dataMem = dataMem.slice(1);
                //获取数据
               /* while (dataMem.length < totalPointsMem) {
                    var prev = dataMem.length > 0 ? dataMem[dataMem.length - 1] : 50,
                            y = prev + Math.random() * 10 - 5;
                    if (y < 0) {
                        y = 0;
                    } else if (y > 100) {
                        y = 100;
                    }
                    dataMem.push(y);
                }*/
                getMemFromServer(dataMem);
                // Zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < dataMem.length; ++i) {
                    res.push([i, dataMem[i]]);
                }
                return res;
            }
            /**
             * 初始化Mem的浮动图表，全部赋值为0
             */
            function initMemChart(){
                // Zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < totalPointsMem; ++i) {
                    dataMem.push(0);
                    res.push([i, 0]);
                }
                return res;
            }
            var interactive_plot_Mem = $.plot("#mem_info", [initMemChart()], {
                grid: {
                    hoverable:true,
                    borderColor: "#f3f3f3",
                    borderWidth: 1,
                    tickColor: "#f3f3f3"
                },
                series: {
                    shadowSize: 0, // Drawing is faster without shadows
                    color: "#00A65A"
                },
                lines: {
                    fill: true, //Converts the line chart to area chart
                    color: "#00A65A"
                },
                yaxis: {
                    min: 0,
                    max: 100,
                    show: true
                },
                xaxis: {
                    show: true
                }
            });

            var updateIntervalMem = 1000; //1秒钟刷新一次数据
            var realtimeMem = "on"; //If == to on then fetch data every x seconds. else stop fetching
            function updateMem() {
                interactive_plot_Mem.setData([getMemData()]);
                // Since the axes don't change, we don't need to call plot.setupGrid()
                interactive_plot_Mem.draw();
                if (realtimeMem === "on")
                    setTimeout(updateMem, updateIntervalMem);
            }
            //INITIALIZE REALTIME DATA FETCHING
            if (realtimeMem === "on") {
                updateMem();
            }
            //REALTIME TOGGLE
            $("#realtime .btn").click(function () {
                if ($(this).data("toggle") === "on") {
                    realtimeMem = "on";
                } else {
                    realtimeMem = "off";
                }
                updateMem();
            });

            //鼠标悬浮内存显示该点的数据
            $('<div class="tooltip-inner" id="mem_info_tooltip"></div>').css({
                position: "absolute",
                display: "none",
                opacity: 0.8	//透明度
            }).appendTo("body");
            $("#mem_info").bind("plothover", function (event, pos, item) {
                if (item) {
                    var x = item.datapoint[0].toFixed(2),
                            y = item.datapoint[1].toFixed(2);

                    $("#mem_info_tooltip").html( "使用率:" + y +"%")
                            .css({top: item.pageY + 5, left: item.pageX + 5})
                            .fadeIn(200);
                } else {
                    $("#mem_info_tooltip").hide();
                }
            });
            /*
             * Mem信息
             */

            /*
             * 磁盘
             */
            var donutDataDisk = [
                {label: "已用", data: <%=SigarUtil.getDiskInfo().getOrDefault(SigarUtil.DISK_USED, "0")%>, color: "#D53A35"},
                {label: "剩余", data: <%=SigarUtil.getDiskInfo().getOrDefault(SigarUtil.DISK_FREE, "0")%>, color: "#2F4554"}
            ];
            $.plot("#disk_chart", donutDataDisk, {
                series: {
                    pie: {
                        show: true,
                        radius: 1,
                        innerRadius: 0.5,
                        label: {
                            show: true,
                            radius: 2 / 3,
                            formatter: labelFormatter,
                            threshold: 0.1
                        }
                    }
                },
                legend: {
                    show: false
                }
            });
            /*
             * END DONUT CHART
             */

            /*
             * Custom Label formatter
             */
            function labelFormatter(label, series) {
                return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
                        + label
                        + "<br>"
                        + Math.round(series.percent) + "%</div>";
            }

        });	//结束ready函数
    </script>

</body>
</html>