package com.example.controller;


import com.alibaba.fastjson.JSON;
import com.example.entity.Officeuser;
import com.example.service.OfficeuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-06-25
 */
@RestController
@RequestMapping("//officeuser")
public class OfficeuserController {

    @Autowired
    private OfficeuserService officeuserService;

    @RequestMapping("/login")
    public String login(@RequestBody Officeuser frontuser){
        //前端传入Officeuser的json数据，Spring接收并将他转化成一个Officeuser类的Bean对象frontuser
        //然后使用@RestController自带的getByID方法，查找数据库，得到相同ID的Bean对象enduser
        //将他们两个的password值进行比较，如果值相同就返回成功登录数据
        String springLoginCheck="loginIsFalse";

        Officeuser enduser=this.officeuserService.getById(frontuser.getIdUser());

        System.out.println("前端："+frontuser);
        System.out.println("后端"+enduser);

        HashMap<String,Object> res=new HashMap<>();//创建一个哈希表存储要返回的数据
        if(enduser.getPassword().equals(frontuser.getPassword())){
            springLoginCheck="loginIsOk";
            res.put("user",enduser);
        }
        res.put("springLoginCheck",springLoginCheck);//把当前键和值加入到哈希表中，也可以再添加其他数据，如图片、历史记录、token等

        String resToJson= JSON.toJSONString(res);//把当前哈希表转化成JSON数据返回给前端
        return resToJson;
    }
}

