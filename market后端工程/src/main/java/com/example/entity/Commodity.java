package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2021-06-25
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Commodity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_commodity", type = IdType.AUTO)
      private Integer idCommodity;

    private String nameCommodity;

    private String specifications;

    private Integer purchasePrice;

    private Integer salePrice;

    private Integer vipPrice;

    private Integer quantity;


}
