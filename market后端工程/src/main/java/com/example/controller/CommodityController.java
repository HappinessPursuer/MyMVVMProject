package com.example.controller;


import com.example.entity.Commodity;
import com.example.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("//commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/list")
    public List<Commodity> list(){
        return this.commodityService.list();
    }
    //获取完整的商品表

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return this.commodityService.removeById(id);
    }
    //通过Delete方法 删除ID是前端传入的ID的商品 并返回一个boolean值

    @GetMapping("/find/{id}")
    public Commodity find(@PathVariable("id") Integer id){
        return this.commodityService.getById(id);
    }
    //通过Get方法 查找ID是前端传入的ID的商品 并返回一个商品对象

    @RequestMapping("/update")
    public boolean update(@RequestBody Commodity frontCommodity){
        return this.commodityService.updateById(frontCommodity);
    }
    //RequestMapping可以接受get、post和put 修改ID是前端传入商品的ID 并返回一个Boolean类型的值

    @RequestMapping("/append")
    public boolean append(@RequestBody Commodity frontCommodity){
        return this.commodityService.save(frontCommodity);
    }
    //添加商品，返回一个Boolean类型
}

