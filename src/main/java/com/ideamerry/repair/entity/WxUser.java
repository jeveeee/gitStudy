package com.ideamerry.repair.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.ideamerry.repair.util.ExcelUtil;
import com.ideamerry.repair.util.Excel;

@Data
public class WxUser implements Serializable {
    /**
    * 主键
    */
    @Excel(name = "主键")
    private Integer id;
    /**
    * openid
    */
    @Excel(name = "openid")
    private String openid;
    /**
    * 昵称
    */
    @Excel(name = "昵称")
    private String nickName;
    /**
    * 语言
    */
    @Excel(name = "语言")
    private String language;
    /**
    * 用户id
    */
    @Excel(name = "用户id")
    private Integer uId;
    /**
    * 城市
    */
    @Excel(name = "城市")
    private String city;
    /**
    * 性别
    */
    @Excel(name = "性别")
    private Integer gender;
    /**
    * 省份
    */
    @Excel(name = "省份")
    private String province;
    /**
    * 头像
    */
    @Excel(name = "头像")
    private String avatarUrl;
    /**
    * 创建日期
    */
    @Excel(name = "创建日期")
    private Date createDate;
    /**
    * 邮箱
    */
    @Excel(name = "邮箱")
    private String email;
    /**
    * unionid
    */
    @Excel(name = "unionid")
    private String unionid;
    /**
    * 创建时间
    */
    @Excel(name = "创建时间")
    private Date createTime;
    /**
    * 是的可用
    */
    @Excel(name = "是的可用")
    private Integer isDelete;
    /**
    * 类型
    */
    @Excel(name = "类型")
    private Integer type;
    /**
    * 联系电话
    */
    @Excel(name = "联系电话")
    private String mobile;
    /**
    * 用户号码
    */
    @Excel(name = "用户号码")
    private String cardNumber;
    /**
    * 真实姓名
    */
    @Excel(name = "真实姓名")
    private String realName;
    /**
    * 是否绑定手机
    */
    @Excel(name = "是否绑定手机")
    private Integer isBindingMobile;
    /**
    * 服务号openId
    */
    @Excel(name = "服务号openId")
    private String otherOpenid;
    /**
    * 单位id
    */
    @Excel(name = "单位id")
    private String campus;
}
