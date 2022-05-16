package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
    public class OrderDetails implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_order", type = IdType.AUTO)
      private Integer idOrder;

    private LocalDateTime ordertime;

    private Integer purchasePrice;

    private Integer quantity;

    private Integer idCommodity;

    private String nameCommodity;

    private Integer idSupplier;

    private String nameSupplier;

    private String address;

    private String postcode;

    private String purchaserId;

    private String purchaserName;

    private LocalDateTime purchasetime;

    private String orderstate;

    private String manageId;

    private String manageName;

    private LocalDateTime manageTime;

    private String manageOpinion;


}
