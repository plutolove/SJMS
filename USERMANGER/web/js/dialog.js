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
		content: '/admin/user/edit',
		scrollbar: false,
		btn: ['保存', '关闭'],
		yes: function (index) {
			var body = layer.getChildFrame('body', index);
			var form = $(body.find("#myForm"));
			form.submit();
			//layer.close(index);
            setTimeout("location.reload(true)",500);//刷新页面
		},
		close: function (index) {
			layer.close(index);
		}
	});
}


/**
 * 结束任务
 * @param jodId
 */
function jobStop(jodId){
	layer.confirm('是否结束该任务？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
		$.post("/admin/job/stop", {jobId: jodId}, function (result) {
			if (result.result == '0') {
				layer.msg('结束任务', {icon: 1});
				location.reload(true);	//刷新页面
			} else if (result.result == '1'){
				layer.msg(result.message, {icon: 2});
			}
		});
	}, function(){
	  layer.close();
	});	
}

/**
 * 删除HDFS文件及文件夹
 * @param fileId
 */
function hdfsDelete(path, filename){
	layer.confirm('是否删除该文件？', {
		btn: ['确认', '取消'] //按钮
	}, function () {
		$.post("/admin/file/delete", {path: path, filename: filename}, function (result) {
			if (result.result == '0') {
				layer.msg(result.message, {icon: 1});
				location.reload(true);	//刷新页面
			} else if (result.result == '1'){
				layer.msg(result.message, {icon: 2});
			}
		});
	}, function () {
		layer.close();
	});
}

/**
 * 重命名HDFS文件及文件夹
 * @param fileId
 */
function hdfsRename(path, filename){
	layer.prompt({
		title: '请输入新名称',
		formType: 0 //prompt风格，支持0-2
	}, function (newname) {
		$.post("/admin/file/rename", {path: path, prename: filename, newname: newname}, function (result) {
			if (result.result == '0') {
				layer.msg(result.message, {icon: 1});
				location.reload(true);
			} else if (result.result == '1'){
				layer.msg(result.message, {icon: 2});
			}
		});
	});
}

/**
 * 移动HDFS文件及文件夹
 * @param fileId
 */
function hdfsMove(fileId){
	layer.confirm('是否移动该文件？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  layer.msg('移动文件', {icon: 1});
	}, function(){
	  layer.close();
	});		
}

/**
 * 复制HDFS文件及文件夹
 * @param fileId
 */
function hdfsCopy(fileId){
	layer.confirm('是否复制该文件？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  layer.msg('复制文件', {icon: 1});
	}, function(){
	  layer.close();
	});		
}

/**
 * 禁止用户登陆系统
 */
function sysUserLoginDisable(){
	layer.confirm('是否禁止用户登陆系统？确认后所有用户将无法登陆系统', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  layer.msg('禁止用户登陆系统', {icon: 1});
	}, function(){
	  layer.close();
	});		
}

/**
 * 上传文件
 * @param path
 */
function hdfsUpload(path) {
	layer.open({
		type: 2,
		title: '上传数据',
		shadeClose: true,
		shade: 0.5,
		maxmin: true, //开启最大化最小化按钮
		area: ['680px', '520px'],
		content: '/admin/file/upload?path=' + path,
		scrollbar: false
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
		content: '/admin/file/fileView?file=' + file,
		scrollbar: false
	});
}

/**
 * 删除工作记录
 */
function jobDelete(id){
	layer.confirm('是否删除该工作记录？', {
		btn: ['确认', '取消'] //按钮
	}, function () {
		$.post("/admin/job/delete", {id: id}, function (result) {
			if (result.result == '0') {
				layer.msg(result.message, {icon: 1});
				location.reload(true);	//刷新页面
			} else if (result.result == '1'){
				layer.msg(result.message, {icon: 2});
			}
		});
	}, function () {
		layer.close();
	});
}

/**
 * 查看工作日志
 */
function jobLog(id) {
	layer.open({
		type: 2,
		title: '日志查看',
		shadeClose: true,
		shade: 0.5,
		maxmin: true, //开启最大化最小化按钮
		area: ['1200px', '720px'],
		content: '/admin/job/log?id=' + id,
		scrollbar: false
	});
}

/**
 * 选择文件
 * @param fileOnly 是否只能选择文件 0表示false ，1表示true
 */
function jobFileChoose(fileOnly) {
    layer.open({
        type: 2,
        title: '文件选择',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, //开启最大化最小化按钮
        area: ['1200px', '720px'],
        content: '/admin/job/chooseFile?path=/&fileOnly=' + fileOnly,
        scrollbar: false
    });
}

/**
 * 选中文件后的操作
 */
function jobFileSelected(filePath, fileOnly) {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    if (fileOnly == 0) {
        //不是只能选择文件，说明是选择数据
        parent.$('#in').val(filePath);
    } else {
        //选择jar程序
        parent.$('#jar_path').val(filePath);
    }
    parent.layer.close(index);
}

/**
 * 查看系统通知
 */
function showSysNote(id) {
	layer.open({
		type: 2,
		title: '通告通知',
		shadeClose: true,
		shade: 0.5,
		maxmin: true, //开启最大化最小化按钮
		area: ['1200px', '720px'],
		content: '/admin/sysnote/view?id=' + id,
		scrollbar: false
	});
}