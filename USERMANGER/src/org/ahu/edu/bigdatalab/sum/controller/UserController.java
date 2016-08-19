package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import net.coobird.thumbnailator.Thumbnails;
import org.ahu.edu.bigdatalab.sum.model.Result;
import org.ahu.edu.bigdatalab.sum.model.User;
import org.ahu.edu.bigdatalab.sum.tools.MD5Util;
import org.ahu.edu.bigdatalab.sum.util.TestUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class UserController extends Controller {

    public void index() {
        User user = getSessionAttr("user");
        setAttr("user", user.toString());
        renderJsp("index.jsp");
    }

    /**
     * 用户资料编辑
     */
    public void edit() {
        User user = getSessionAttr("user");
        setAttr("user", user);
        renderJsp("self-info.jsp");
    }

    public void editP() {
        //头像上传处理
        UploadFile uploadFile = getFile("file", "avatar");
        if (uploadFile != null) {
            //修改文件名
            String uploadFileName = uploadFile.getFileName();
            String suffix = uploadFileName.substring(uploadFileName.lastIndexOf('.'));

            //检测后缀
            String[] types = {".jpg", ".jpeg", ".png", ".bmp", ".gif"};
            Boolean allow = false;
            for (int i = 0; i < types.length; i++) {
                if (suffix.toLowerCase().equals(types[i])) {
                    allow = true;
                    break;
                }
            }
            if (allow) {
                //改名
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                String fileName = sdf.format(System.currentTimeMillis());
                String fullName = fileName + suffix;//完整文件名
                String path = "/upload/avatar/";//不带名路径
                String fullpath = path + fullName;//带名路径
                String filePath = PathKit.getWebRootPath() + fullpath;//文件本地路径
                boolean b = uploadFile.getFile().renameTo(new File(filePath));

                //生成缩略图
                try {
                    Thumbnails.of(new File(filePath))
                            .forceSize(100, 100)
                            .outputFormat("jpg")
                            .toFile(PathKit.getWebRootPath() + path + "thumbnail" + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将图片路径存到数据库
                User user = getSessionAttr("user");
                user.set("avatar", fullpath)
                        .set("thumbnail", path + "thumbnail" + fileName + ".jpg")
                        .update();
            } else {
                //类型不允许
                TestUtil.log("文件类型不允许：" + suffix);
                renderJson(new Result("1", "文件类型不允许"));
            }
        }

        /*
        其他处理
         */
        User user = getSessionAttr("user");
        String nickname = getPara("nickname");
        String email = getPara("email");
        if (nickname.equals("") || email.equals("")) {
            renderJson(new Result("1", "内容不能为空"));
            return;
        }
        user.set("nickname", nickname)
                .set("email", email)
                .update();
        renderJson(new Result("0", "修改成功"));
    }

    /**
     * 修改密码
     */
    public void change_password() {
        render("change_password.jsp");
    }

    public void change_passwordP() {
        String old_password = getPara("old_password");
        String new_password = getPara("new_password");
        User user = getSessionAttr("user");
        if (user.getStr("password").equals(MD5Util.getMD5(old_password))) {
            user.set("password", MD5Util.getMD5(new_password)).update();
            //修改成功
            renderText("修改成功");
        } else {
            renderJson("密码错误");
        }
    }

}
