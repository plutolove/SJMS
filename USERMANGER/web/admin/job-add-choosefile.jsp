<%@ page import="java.util.List" %>
<%@ page import="org.ahu.edu.bigdatalab.sum.model.Note" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>UserSJMS | 选择文件</title>
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
         folder instead of downloading afll of them to reduce the load. -->
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
        String fileOnly = request.getParameter("fileOnly");    //是否只能选择文件
    %>
    <!-- Main content -->
    <section class="content">

        <div class="row">
            <div class="col-xs-12">
                <div class="">
                    <div class="box-header">
                        <h3 class="box-title">
                            <ol class="breadcrumb">
                                <li><a href="/admin/job/chooseFile?path=/">home</a></li>
                                <c:forEach var="pathModel" items="${pathList}">
                                    <li><a href="/admin/job/chooseFile?path=/${pathModel.folderPath}">${pathModel.folderName}</a></li>
                                </c:forEach>
                            </ol>
                        </h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table id="hdfs_list" class="table table-hover">
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>大小</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="file" items="${fileList}">
                                <tr>
                                    <c:if test="${file.filetype == '1'}">
                                        <td width="30%">
                                            <a href="#"><i class="fa fa-file"></i> ${file.filename}</a>
                                        </td>
                                        <td width="25%">${file.filesize}</td>
                                        <td width="25%">${file.filetime}</td>
                                        <td width="10%">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default" onclick="jobFileSelected('${path}${file.filename}', <%=fileOnly%>)"><i class="fa fa-check-square-o"></i> 选中 </button>
                                            </div>
                                        </td>
                                    </c:if>
                                    <c:if test="${file.filetype == '0'}">
                                        <td width="30%">
                                            <a href="/admin/job/chooseFile?path=${path}${file.filename}&fileOnly=<%=fileOnly%>"><i class="fa fa-folder-open"></i> ${file.filename}</a>
                                        </td>
                                        <td width="15%">${file.filesize}</td>
                                        <td width="15%">${file.filetime}</td>
                                        <td width="30%">
                                            <div class="btn-group">
                                                <% if ("0".equals(fileOnly)) { %>
                                                <button type="button" class="btn btn-default" onclick="jobFileSelected('${path}${file.filename}', 0)" ><i class="fa fa-check-square-o"></i> 选中 </button>
                                                <% } %>
                                            </div>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>名称</th>
                                <th>大小</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->

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
<!-- page script -->
<script>

    function hdfsMkdir() {
        layer.prompt({
            title: '输入文件夹名称',
            formType: 0 //prompt风格，支持0-2
        }, function (filename) {
            $.post("/admin/file/mkdir", {path: '${path}', filename: filename}, function (result) {
                if (result.result == '0') {
                    layer.msg(result.message, {icon: 1});
                    location.reload(true);
                } else if (result.result == '1'){
                    layer.msg(result.message, {icon: 2});
                }
            });
        });
    }

</script>

<!--对话框-->
<script type="text/javascript" src="/layer/layer.js" ></script>
<script type="text/javascript" src="/js/dialog.js" ></script>
</body>
</html>
