// 文件上传
jQuery(function () {
    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader;

    uploader = WebUploader.create({
        // 不压缩image
        resize: false,
        // swf文件路径
        swf: 'webuploader/Uploader.swf',
        // 文件接收服务端。
        server: uploadUrl,	//将绑定的ID一同传过去
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker'
    });

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        $list.append('<div id="' + file.id + '" class="item" style="height:50px;" >' +
            '<h4 class="info"  style="display:inline;">' + file.name + '<h4  class="state"  style="display:inline;"> 等待上传...  </h4></h4>' +
            //   '<p class="state"  style="display:inline;">等待上传...<a id="del" style="visibility:hidden;">删除</a></p>' +//隐藏删除按钮
            '</div>');
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('h4.state').text(' 上传中');
        $percent.css('width', percentage * 100 + '%');
    });

    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('h4.state').text(' 已上传 ');
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('h4.state').text(' 上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text(' 暂停上传');
        } else {
            $btn.text(' 开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});

