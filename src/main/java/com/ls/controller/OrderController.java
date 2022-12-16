package com.ls.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ls.entity.Order;
import com.ls.exception.DataResult;
import com.ls.service.IOrderService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService orderService;
    @Autowired
    private Environment env;
    @PostMapping("/getProperties")
    public String getProperties(){
        return env.getProperty("server.port");
    }
    @RequestMapping("/takeOrder")
    public String takeOrder(){
        return "下一笔订单！";
    }
    @PostMapping("/getOrder")
    public DataResult getOrder(@RequestBody @Validated Order order, @RequestParam(value = "id") String id){
        System.out.println(id);
        QueryWrapper<Order> query = Wrappers.query();
        query.eq("order_no",order.getOrderNo());
        return new DataResult(orderService.list(query));
    }
    @PostMapping("/saveOrder")
    public DataResult saveOrder(@RequestBody @Validated Order order){
        return new DataResult(orderService.saveOrder(order));
    }
}
