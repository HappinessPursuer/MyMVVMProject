package com.example.mapper;

import com.example.entity.OrderDetails;
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
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {
    public List<OrderDetails> findState(@Param("state") String state);//找所有 状态是传入状态的 所有细节订单
}
