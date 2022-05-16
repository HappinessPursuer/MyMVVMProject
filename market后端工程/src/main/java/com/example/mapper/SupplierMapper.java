package com.example.mapper;

import com.example.entity.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-06-25
 */
public interface SupplierMapper extends BaseMapper<Supplier> {
    public List<Supplier> findLow(@Param("price") Integer price);//找价格低于传入价格的所有供应商


}
