package com.example.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sb on 16-7-14.
 */
public class CheckIdentity {
    public static boolean checkStudent(String jw_username, String jw_password){
        Document docGet = null;
        Document docPost = null;
        try {
            //获取必须参数
            docGet = Jsoup.connect("http://xk3.ahu.cn/").get();
            Element viewstate = docGet.getElementById("__VIEWSTATE");
            Element eventvalidation = docGet.getElementById("__EVENTVALIDATION");
            Map<String, String> postData = new HashMap<String, String>();

            //填充postdata
            postData.put("__VIEWSTATE", viewstate.attr("value"));
            postData.put("__EVENTVALIDATION", eventvalidation.attr("value"));
            postData.put("txtUserName", jw_username);
            postData.put("TextBox2", jw_password);
            postData.put("RadioButtonList1", "%D1%A7%EF%BF%BD%EF%BF%BD");
            postData.put("Button1", "");

            //模拟登陆
            docPost = Jsoup.connect("http://xk3.ahu.cn/default2.aspx")
                    .followRedirects(false)
                    .data(postData)
                    .post();

            //判断登陆是否成功
            Element a = docPost.select("a[href]").first();

            Pattern pattern = Pattern.compile("\"%2fxs_main.aspx%3fxh%3d" + jw_username + "\"");
            Matcher matcher = pattern.matcher(a.toString());
            if(matcher.find()){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkTeacher(String jw_username, String jw_password){
        Document docGet = null;
        Document docPost = null;
        try {
            //获取必须参数
            docGet = Jsoup.connect("http://xk3.ahu.cn/").get();
            Element viewstate = docGet.getElementById("__VIEWSTATE");
            Element eventvalidation = docGet.getElementById("__EVENTVALIDATION");
            Map<String, String> postData = new HashMap<String, String>();

            //填充postdata
            postData.put("__VIEWSTATE", viewstate.attr("value"));
            postData.put("__EVENTVALIDATION", eventvalidation.attr("value"));
            postData.put("txtUserName", jw_username);
            postData.put("TextBox2", jw_password);
            postData.put("RadioButtonList1", "教师");
            postData.put("Button1", "");


            //模拟登陆
            docPost = Jsoup.connect("http://xk3.ahu.cn/default2.aspx")
//                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .followRedirects(false)
                    .data(postData)
                    .post();

            //判断登陆是否成功
            Element a = docPost.select("a[href]").first();

            Pattern pattern = Pattern.compile("\"%2fjs_main.aspx%3fxh%3d" + jw_username + "\"");
            Matcher matcher = pattern.matcher(a.toString());
            if(matcher.find()){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args){
//        System.out.print(checkTeacher("06041", "ahu2675343"));
//    }
}
