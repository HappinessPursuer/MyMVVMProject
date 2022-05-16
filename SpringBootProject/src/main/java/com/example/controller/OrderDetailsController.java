package com.example.controller;


import com.example.entity.OrderDetails;
import com.example.mapper.OrderDetailsMapper;
import com.example.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-06-25
 */
@RestController
@RequestMapping("//orderDetails")
public class OrderDetailsController {

    @Resource
    private OrderDetailsService orderDetailsService;
    @GetMapping("/list")
    public List<OrderDetails> list(){
        return this.orderDetailsService.list();
    }
    @RequestMapping("/append")
    public boolean append(@RequestBody OrderDetails orderDetails){
        LocalDateTime now=LocalDateTime.now();//获取服务器当前时间
        orderDetails.setPurchasetime(now);
        return this.orderDetailsService.save(orderDetails);
    }
    @RequestMapping("/update")
    public boolean update(@RequestBody OrderDetails orderDetails){
        return this.orderDetailsService.updateById(orderDetails);
    }
    @RequestMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return this.orderDetailsService.removeById(id);
    }

    @Resource
    private OrderDetailsMapper orderDetailsMapper;
    @RequestMapping("/findState/{state}")
    public List<OrderDetails> findState(@PathVariable String state){
        return orderDetailsMapper.findState(state);
    }
}

