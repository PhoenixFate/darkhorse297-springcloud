package com.example;

import com.example.po.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

public class TestJson {

    @Test
    public void test01(){
        Student stu=new Student();
        stu.setName("JSON");
        stu.setAge("23");
        stu.setAddress("北京市西城区");

        //1、使用JSONObject
        JSONObject json = JSONObject.fromObject(stu);
        //2、使用JSONArray
        JSONArray array=JSONArray.fromObject(stu);

        String strJson=json.toString();
        String strArray=array.toString();

        System.out.println("strJson:"+strJson);
        System.out.println("strArray:"+strArray);




    }
}
