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
package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author hushao
* @date 2020-09-10
**/
@Data
public class ReleaseDto implements Serializable {

    private String id;

    private String userId;

    /** 汇款公司 */
    private String remittanceCompany;

    /** 汇款方式 */
    private String remittanceType;

    /** 0为正常，1为驳回状态 */
    private String status;

    /** 推荐公司 */
    private String recommendedCompany;

    /** 项目名称 */
    private String entryName;

    /** 省份 */
    private String province;

    /** 行业 */
    private String industry;

    /** 负责人 */
    private String personCharge;

    /** 主投公司  */
    private String mainCompany;

    /** 收款账号  */
    private String collectionAccountNo;

    /** 收款行号  */
    private String receivingBankNo;

    /** 收款户名 */
    private String accountName;

    /** 收款金额 */
    private String amountCollected;

    /** 汇款摘要 */
    private String remittanceSummary;

    /** 开标日期 */
    private Timestamp openingDate;

    /** 招标公告 */
    private String tenderAnnouncement;

    /** '是否删除(0未删除；1已删除)', */
    private Integer deleted;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}