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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
* @website https://el-admin.vip
* @description /
* @author sding
* @date 2020-09-10
**/
@Data
public class CompaniesDto implements Serializable {

    /** 公司ID */
    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long companyId;

    /** 公司名称 */
    private String companyName;

    /** 地址 */
    private String address;

    /** 联系人 */
    private String contactName;

    /** 联系电话 */
    private String contactTel;

    /** 传真 */
    private String fax;

    /** 邮箱 */
    private String email;

    /** 手机号码 */
    private String mobilePhone;

    /** 企业性质 */
    private String enterpriseType;

    /** 企业级别 */
    private String enterpriseLevel;

    /** 成立时间 */
    private Timestamp establisheTime;

    /** 法人 */
    private String legalPerson;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}