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
    public class Supplier implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_supplier", type = IdType.AUTO)
      private Integer idSupplier;

    private Integer idCommodity;

    private Integer purchasePrice;

    private String nameSupplier;

    private String contact;

    private String telephone;

    private String phone;

    private String address;

    private String postcode;

    private String creditCard;

    private String creditBank;


}
