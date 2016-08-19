* 获取目录子文件
>* URL: ip/getFileList
>* PARA: userid, full_path
>* RES: json

* 新建文件夹
>* URL： IP/mkdir
>* PARA: userid, full_path, filename
>* RES: json{result: "ok/error"}

* 删除文件
>* URL: IP/deleteFile
>* PARA: userid, full_path
>* RES: json{result: "ok/error"}

* 重命名
>* URL: IP/renameFile
>* PARA: userid, path, pre_filename, new_name
>* RES: json{result: "ok/error"}

* 获取文件的top100行
>* URL: IP/getLines
>* PARA: userid, full_path
>* RES: String

* 上传文件
>* URL: IP/uploadFile
>* PARA: userid, full_path, FILE
>* RES: json{result: "ok/error"}

* 下载文件
>* URL: IP/downloadFile
>* PARA: userid, path, filename
>* nothing

* 提交作业
>* URL: IP/runJar
>* PARA: name, userid, main_class, memory, cores, jar_path, in, out

* 停止作业
>* URL: IP/killJar
>* PARA: appid