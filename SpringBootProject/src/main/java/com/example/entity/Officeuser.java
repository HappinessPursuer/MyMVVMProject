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
    public class Officeuser implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_user", type = IdType.AUTO)
      private Integer idUser;

    private String username;

    private String password;

    private String position;


}
