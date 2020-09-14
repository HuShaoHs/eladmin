/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author hushao
* @date 2020-09-10
**/
@Entity
@Data
@Table(name="tb_release")
public class Release implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private String id;

    @Column(name = "user_id")
    @ApiModelProperty(value = "userId")
    private String userId;

    @Column(name = "remittance_company")
    @ApiModelProperty(value = "汇款公司")
    private String remittanceCompany;

    @Column(name = "remittance_type")
    @ApiModelProperty(value = "汇款方式")
    private String remittanceType;

    @Column(name = "status")
    @ApiModelProperty(value = "0为正常，1为驳回状态")
    private String status;

    @Column(name = "recommended_company")
    @ApiModelProperty(value = "推荐公司")
    private String recommendedCompany;

    @Column(name = "entry_name")
    @ApiModelProperty(value = "项目名称")
    private String entryName;

    @Column(name = "province")
    @ApiModelProperty(value = "省份")
    private String province;

    @Column(name = "industry")
    @ApiModelProperty(value = "行业")
    private String industry;

    @Column(name = "person_charge")
    @ApiModelProperty(value = "负责人")
    private String personCharge;

    @Column(name = "main_company")
    @ApiModelProperty(value = "主投公司 ")
    private String mainCompany;

    @Column(name = "collection_account_no")
    @ApiModelProperty(value = "收款账号 ")
    private String collectionAccountNo;

    @Column(name = "receiving_bank_no")
    @ApiModelProperty(value = "收款行号 ")
    private String receivingBankNo;

    @Column(name = "account_name")
    @ApiModelProperty(value = "收款户名")
    private String accountName;

    @Column(name = "amount_collected")
    @ApiModelProperty(value = "收款金额")
    private String amountCollected;

    @Column(name = "remittance_summary")
    @ApiModelProperty(value = "汇款摘要")
    private String remittanceSummary;

    @Column(name = "opening_date")
    @ApiModelProperty(value = "开标日期")
    private Timestamp openingDate;

    @Column(name = "tender_announcement")
    @ApiModelProperty(value = "招标公告")
    private String tenderAnnouncement;

    @Column(name = "deleted")
    @ApiModelProperty(value = "'是否删除(0未删除；1已删除)',")
    private Integer deleted;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @Column(name = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(Release source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}