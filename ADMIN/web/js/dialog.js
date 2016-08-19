/**
 * 显示修改个人资料信息
 */
function selfInfo() {
    layer.open({
        type: 2,
        title: '个人资料',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['820px', '520px'],
        content: 'self-info.jsp',
        scrollbar: false,
        btn: ['保存', '关闭'],
        yes: function (index) {
            var body = layer.getChildFrame('body', index);
            var form = $(body.find("#myForm"));
            form.submit();
            layer.close(index);
            location.reload(true);	//刷新页面
        },
        close: function (index) {
            layer.close(index);
        }
    });
}

/**
 * 节点重启提示
 */
function clusterReboot(clusterId) {
    layer.confirm('是否重启该节点？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('开始重启', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 节点关机提示
 */
function clusterShutdown(clusterId) {
    layer.confirm('是否关闭该节点？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('开始关机', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 节点重启提示
 */
function clusterDisconnect(clusterId) {
    layer.confirm('是否断开该节点与集群的连接？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('开始断开连接', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 显示节点信息
 * @param clusterAddress
 */
function clusterInfo(clusterAddress) {
    layer.open({
        type: 2,
        title: '节点信息',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['1500px', '660px'],
        content: 'http://' + clusterAddress + ':8080/HardwareWeb',
        scrollbar: false
    });
}

/**
 * 连接节点SSH
 * @param clusterAddress
 */
function clusterSSH(clusterAddress) {
    var index = layer.open({
        type: 2,
        title: 'SSH',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['1500px', '660px'],
        content: 'http://' + clusterAddress + ':8080/HardwareWeb',
        scrollbar: false
    });
    layer.full(index);
}

/**
 * 禁止登陆提示
 */
function userLoginDisable(userId) {
    layer.confirm('是否禁止该用户登陆系统？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('禁止登陆', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 允许登陆提示
 * @param userId
 */
function userLoginEnable(userId) {
    layer.confirm('是否允许该用户登陆系统？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('允许登陆', {icon: 1});
    }, function () {
        layer.close();
    });
}


/**
 * 禁止提交任务提示
 */
function userRunJobDisable(userId) {
    layer.confirm('是否禁止该用户提交任务？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("user/setRunJobStatus", {userId: userId, status:0}, function (result) {
            if (result.result == 'OK') {
                layer.msg('禁止提交任务', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('出现异常', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 允许提交任务提示
 * @param userId
 */
function userRunJobEnable(userId) {
    layer.confirm('是否允许该用户提交任务？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("user/setRunJobStatus", {userId: userId, status:1}, function (result) {
            if (result.result == 'OK') {
                layer.msg('允许提交任务', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('出现异常', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 停止所有任务
 */
function userStopJobs(userId) {
    layer.confirm('是否停止该用户的所有任务？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        layer.msg('停止所有任务', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 显示用户个人资料信息
 */
function userInfo(userId) {
    layer.open({
        type: 2,
        title: '用户资料',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['660px', '560px'],
        content: 'user-info.jsp?id=' + userId,
        scrollbar: false
    });
}

/**
 * 结束任务
 * @param jodId
 */
function jobStop(jodId) {
    //Spark的URL写在html中，js变量里面
    layer.confirm('是否结束该任务？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        funPostBack(jodId); //调用该函数生成表单提交
        layer.msg('结束任务', {icon: 1});
    }, function () {
        layer.close();
    });
}

/**
 * 新建文件夹
 */
function hdfsAdd(path) {
    layer.prompt({
        title: '输入新建文件夹名称',
        formType: 0 //prompt风格，支持0-2
    }, function (name) {
        $.post("hdfs/add", {path: path, name: name}, function (result) {
            if (result.result == 'OK') {
                layer.msg('新建文件夹', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('创建失败', {icon: 2});
            }
        });
    });
}

/**
 * 删除HDFS文件及文件夹
 */
function hdfsDelete(file, dom) {
    layer.confirm('是否删除该文件？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("hdfs/delete", {file: file}, function (result) {
            if (result.result == 'OK') {
                layer.msg('删除完成', {icon: 1});
                dom.parents("tr").hide("slow", function () {
                    dom.parents("tr").remove();
                });
            } else {
                layer.msg('删除失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 重命名HDFS文件及文件夹
 */
function hdfsRename(path, oldName) {
    layer.prompt({
        title: '输入新的名称',
        formType: 0 //prompt风格，支持0-2
    }, function (newName) {
        $.post("hdfs/rename", {path: path, oldName: oldName, newName: newName}, function (result) {
            if (result.result == 'OK') {
                layer.msg('重命名文件', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('重命名失败', {icon: 2});
            }
        });
    });
}

/**
 * 上传文件
 */
function hdfsUpload(serverUrl, path) {
    layer.open({
        type: 2,
        title: '上传数据(最大支持1.9GB)',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['680px', '520px'],
        content: serverUrl + 'upload/hdfs-upload.jsp?full_path=' + path,
        scrollbar: false,
        end:function () {
            location.reload(true);	//刷新页面
        }
    });
}

/**
 * 查看文件前100行
 */
function hdfsFileView(file) {
    layer.open({
        type: 2,
        title: '文件预览',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['1200px', '720px'],
        content: 'hdfs/fileView?file=' + file,
        scrollbar: false
    });
}


/**
 * 允许用户登陆系统
 */
function sysUserLoginEnable() {
    layer.confirm('是否允许用户登陆系统？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("sys/changeSysState", {action: 0}, function (result) {
            if (result.result == 'OK') {
                layer.msg('允许用户登陆系统', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('操作失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 禁止用户登陆系统
 */
function sysUserLoginDisable() {
    layer.confirm('是否禁止用户登陆系统？确认后所有用户将无法登陆系统', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("sys/changeSysState", {action: 1}, function (result) {
            if (result.result == 'OK') {
                layer.msg('禁止用户登陆系统', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('操作失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 允许用户提交任务
 */
function sysUserJobEnable() {
    layer.confirm('是否允许用户提交任务？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("sys/changeSysState", {action: 2}, function (result) {
            if (result.result == 'OK') {
                layer.msg('允许用户提交任务', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('操作失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 禁止用户提交任务
 */
function sysUserJobDisable() {
    layer.confirm('是否禁止用户提交任务？确认后所有用户将无法提交允许任务，正在运行的任务不受影响', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("sys/changeSysState", {action: 3}, function (result) {
            if (result.result == 'OK') {
                layer.msg('禁止用户提交任务', {icon: 1});
                location.reload(true);	//刷新页面
            } else {
                layer.msg('操作失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 删除通告通知的提示
 */
function sysNoteDelete(noteId, dom) {
    layer.confirm('是否删除该通告通知？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("sys/deleteNote", {id: noteId}, function (result) {
            if (result.result == 'OK') {
                layer.msg('删除完成', {icon: 1});
                dom.parents('li').slideUp("slow", function () {
                    //删除DOM的li父元素
                    dom.parents('li').remove();
                });
            } else {
                layer.msg('操作失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

/**
 * 新建管理员
 */
function adminAdd() {
    layer.open({
        type: 2,
        title: '新建管理员',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['820px', '520px'],
        content: 'admin-add.html',
        scrollbar: false
    });
}

/**
 * 删除该管理员
 * @param  adminId
 */
function adminDelete(adminId, dom) {
    layer.confirm('是否删除该管理员？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.post("admin/delete", {id: adminId}, function (result) {
            if (result.result == 'OK') {
                //删除DOM的li父元素
                dom.parents('tr').remove();
                layer.msg('删除成功', {icon: 1});
            } else {
                layer.msg('删除失败', {icon: 2});
            }
        });
    }, function () {
        layer.close();
    });
}

