package com.example.controller;


import com.example.entity.Supplier;
import com.example.mapper.SupplierMapper;
import com.example.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@RequestMapping("//supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @GetMapping("/list")
    public List<Supplier> list(){
        return this.supplierService.list();
    }


    @Resource
    private SupplierMapper supplierMapper;//调用自定义的SQL语句必须@Resource
    @RequestMapping("/findLow/{price}")
    public List<Supplier> findLow(@PathVariable Integer price){
        return this.supplierMapper.findLow(price);
    }
}

