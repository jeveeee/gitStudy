package com.ideamerry.repair.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.ideamerry.repair.util.ExcelUtil;
import com.ideamerry.repair.util.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RepairInfo implements Serializable {
    /**
    * 主键
    */
    @Excel(name = "主键")
    private Integer id;
    /**
    * 报修类型
    */
    @Excel(name = "报修类型")
    private Integer type;
    /**
    * 报修单状态
    */
    @Excel(name = "报修单状态")
    private Integer status;
    /**
    * 报修时间
    */
    @Excel(name = "报修时间")
    private Date repairDate;
    /**
    * 报修地址
    */
    @Excel(name = "报修地址")
    private String address;
    /**
    * 故障描述
    */
    @Excel(name = "故障描述")
    private String description;
    /**
    * 故障图片
    */
    @Excel(name = "故障图片")
    private String imageUrl;
    /**
    * 留言
    */
    @Excel(name = "留言")
    private String message;
    /**
    * 意见和建议
    */
    @Excel(name = "意见和建议")
    private String evaluate;
    /**
    * 故障原因
    */
    @Excel(name = "故障原因")
    private String reason;
    /**
    * 故障耗材
    */
    @Excel(name = "故障耗材")
    private String consumables;
    /**
    * 申请人姓名
    */
    @Excel(name = "申请人姓名")
    private String applicantName;
    /**
    * 备注
    */
    @Excel(name = "备注")
    private String remarks;
    /**
    * 申请人联系电话
    */
    @Excel(name = "申请人联系电话")
    private String mobile;
    /**
    * 星级别评级
    */
    @Excel(name = "星级别评级")
    private Integer star;
    /**
    * 语音url
    */
    @Excel(name = "语音url")
    private String radioUrl;
    /**
    * 评价时间
    */
    @Excel(name = "评价时间")
    private Date evaluateDate;
    /**
    * openid
    */
    @Excel(name = "openid")
    private String openid;
    /**
    * 学校
    */
    @Excel(name = "学校")
    private Integer campus;
    /**
    * 维修完成时间
    */
    @Excel(name = "维修完成时间")
    private String finishTime;
    /**
    * 任务分配时间
    */
    @Excel(name = "任务分配时间")
    private String allocatedTime;
    /**
    * 故障处理说明
    */
    @Excel(name = "故障处理说明")
    private String detail;
    /**
    * 报修取消原因
    */
    @Excel(name = "报修取消原因")
    private String repairCancelReason;
    /**
    * 报修取消原因备注
    */
    @Excel(name = "报修取消原因备注")
    private String repairCancelReasonRemark;
    /**
    * 报修取消时间
    */
    @Excel(name = "报修取消时间")
    private Date repairCancelDate;
    /**
    * 位置名称
    */
    @Excel(name = "位置名称")
    private Integer location;
    /**
    * 维修级别
    */
    @Excel(name = "维修级别")
    private Integer level;
}
