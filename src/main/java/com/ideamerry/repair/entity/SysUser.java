package com.ideamerry.repair.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.ideamerry.repair.util.ExcelUtil;
import com.ideamerry.repair.util.Excel;

@Data
public class SysUser implements Serializable {
    /**
    * id
    */
    @Excel(name = "id")
    private Integer id;
    /**
    * 用户名
    */
    @Excel(name = "用户名")
    private String userName;
    /**
    * 用户类型
    */
    @Excel(name = "用户类型")
    private Integer userType;
    /**
     * 用户类型
     */
    @Excel(name = "联系电话")
    private String mobile;
    /**
    * 密码
    */
    @Excel(name = "密码")
    private String userPassword;
    /**
    * 是否删除
    */
    @Excel(name = "是否删除")
    private Integer isDelete;
    /**
    * 单位
    */
    @Excel(name = "单位")
    private Integer campus;
}
