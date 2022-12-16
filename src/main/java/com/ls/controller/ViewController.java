package com.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lingsheng
 * @description
 * @date 2022/11/23 10:26
 **/
@Controller
public class ViewController {
    //通过 Environment 环境对象，获取 yml 中配置的值
    @RequestMapping({"/","/index"})
    public String index(){
        return "main";
    }

}
