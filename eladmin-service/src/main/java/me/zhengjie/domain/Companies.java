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
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author sding
* @date 2020-09-10
**/
@Entity
@Data
@Table(name="tb_companies")
public class Companies implements Serializable {

    @Id
    @Column(name = "company_id")
    @ApiModelProperty(value = "公司ID")
    private Long companyId;

    @Column(name = "company_name")
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @Column(name = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "contact_name")
    @ApiModelProperty(value = "联系人")
    private String contactName;

    @Column(name = "contact_tel")
    @ApiModelProperty(value = "联系电话")
    private String contactTel;

    @Column(name = "fax")
    @ApiModelProperty(value = "传真")
    private String fax;

    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column(name = "mobile_phone")
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;

    @Column(name = "enterprise_type")
    @ApiModelProperty(value = "企业性质")
    private String enterpriseType;

    @Column(name = "enterprise_level")
    @ApiModelProperty(value = "企业级别")
    private String enterpriseLevel;

    @Column(name = "establishe_time")
    @ApiModelProperty(value = "成立时间")
    private Timestamp establisheTime;

    @Column(name = "legal_person")
    @ApiModelProperty(value = "法人")
    private String legalPerson;

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

    public void copy(Companies source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}