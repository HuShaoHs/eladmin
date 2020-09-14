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
package me.zhengjie.service.impl;

import me.zhengjie.domain.Qualification;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.QualificationRepository;
import me.zhengjie.service.QualificationService;
import me.zhengjie.service.dto.QualificationDto;
import me.zhengjie.service.dto.QualificationQueryCriteria;
import me.zhengjie.service.mapstruct.QualificationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author sding
* @date 2020-09-10
**/
@Service
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final QualificationMapper qualificationMapper;

    @Override
    public Map<String,Object> queryAll(QualificationQueryCriteria criteria, Pageable pageable){
        Page<Qualification> page = qualificationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(qualificationMapper::toDto));
    }

    @Override
    public List<QualificationDto> queryAll(QualificationQueryCriteria criteria){
        return qualificationMapper.toDto(qualificationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public QualificationDto findById(Long qualificationId) {
        Qualification qualification = qualificationRepository.findById(qualificationId).orElseGet(Qualification::new);
        ValidationUtil.isNull(qualification.getQualificationId(),"Qualification","qualificationId",qualificationId);
        return qualificationMapper.toDto(qualification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QualificationDto create(Qualification resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setQualificationId(snowflake.nextId()); 
        return qualificationMapper.toDto(qualificationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Qualification resources) {
        Qualification qualification = qualificationRepository.findById(resources.getQualificationId()).orElseGet(Qualification::new);
        ValidationUtil.isNull( qualification.getQualificationId(),"Qualification","id",resources.getQualificationId());
        qualification.copy(resources);
        qualificationRepository.save(qualification);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long qualificationId : ids) {
            qualificationRepository.deleteById(qualificationId);
        }
    }

    @Override
    public void download(List<QualificationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QualificationDto qualification : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("资质名称", qualification.getQualificationName());
            map.put("公司ID", qualification.getCompanyId());
            map.put("资质有效期", qualification.getValidityTime());
            map.put("资质类型", qualification.getQualificationType());
            map.put("资质介绍", qualification.getIntroduce());
            map.put("资质照片", qualification.getPicUrl());
            map.put("附件", qualification.getFiles());
            map.put("创建者", qualification.getCreateBy());
            map.put("更新者", qualification.getUpdateBy());
            map.put("创建日期", qualification.getCreateTime());
            map.put("更新时间", qualification.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}