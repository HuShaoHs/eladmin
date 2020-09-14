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
* @author sding
* @date 2020-09-10
**/
@Entity
@Data
@Table(name="tb_qualification")
public class Qualification implements Serializable {

    @Id
    @Column(name = "qualification_id")
    @ApiModelProperty(value = "资质Id")
    private Long qualificationId;

    @Column(name = "qualification_name")
    @ApiModelProperty(value = "资质名称")
    private String qualificationName;

    @Column(name = "company_id")
    @ApiModelProperty(value = "公司ID")
    private Long companyId;

    @Column(name = "validity_time")
    @ApiModelProperty(value = "资质有效期")
    private Timestamp validityTime;

    @Column(name = "qualification_type")
    @ApiModelProperty(value = "资质类型")
    private String qualificationType;

    @Column(name = "introduce")
    @ApiModelProperty(value = "资质介绍")
    private String introduce;

    @Column(name = "pic_url")
    @ApiModelProperty(value = "资质照片")
    private String picUrl;

    @Column(name = "files")
    @ApiModelProperty(value = "附件")
    private String files;

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

    public void copy(Qualification source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}