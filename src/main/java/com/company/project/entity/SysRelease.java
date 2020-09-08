package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.company.project.vo.req.PageReqVO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_release")
public class SysRelease extends PageReqVO implements Serializable {
    @TableId
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 汇款公司
     */
    private String remittanceCompany;

    /**
     * 汇款方式
     */
    private String remittanceType;

    /**
     *省份
     */
    private String province;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 驳回状态
     */
    private String status;

    /**
     * 项目名称
     */
    private String entryName;

    /**
     * 推荐公司
     */
    private String recommendedCompany;

    /**
     * 行业
     */
    private String industry;

    /**
     * 负责人
     */
    private String personCharge;

    /**
     * 主投公司
     */
    private String mainCompany;

    /**
     *收款账号
     */
    private String collectionAccountNo;

    /**
     * 收款行号
     */
    private String receivingBankNo;

    /**
     * 收款户名
     */
    private String accountName;

    /**
     *收款金额
     */
    private String amountCollected;

    /**
     * 汇款摘要
     */
    private String remittanceSummary;

    /**
     *开标日期
     */
    private Date openingDate;

    /**
     * 招标公告
     */
    private String tenderAnnouncement;
}
