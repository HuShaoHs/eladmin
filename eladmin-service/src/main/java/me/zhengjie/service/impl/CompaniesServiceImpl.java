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

import me.zhengjie.domain.Companies;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.CompaniesRepository;
import me.zhengjie.service.CompaniesService;
import me.zhengjie.service.dto.CompaniesDto;
import me.zhengjie.service.dto.CompaniesQueryCriteria;
import me.zhengjie.service.mapstruct.CompaniesMapper;
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
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final CompaniesMapper companiesMapper;

    @Override
    public Map<String,Object> queryAll(CompaniesQueryCriteria criteria, Pageable pageable){
        Page<Companies> page = companiesRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(companiesMapper::toDto));
    }

    @Override
    public List<CompaniesDto> queryAll(CompaniesQueryCriteria criteria){
        return companiesMapper.toDto(companiesRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CompaniesDto findById(Long companyId) {
        Companies companies = companiesRepository.findById(companyId).orElseGet(Companies::new);
        ValidationUtil.isNull(companies.getCompanyId(),"TbCompanies","companyId",companyId);
        return companiesMapper.toDto(companies);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CompaniesDto create(Companies resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setCompanyId(snowflake.nextId()); 
        return companiesMapper.toDto(companiesRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Companies resources) {
        Companies companies = companiesRepository.findById(resources.getCompanyId()).orElseGet(Companies::new);
        ValidationUtil.isNull( companies.getCompanyId(),"TbCompanies","id",resources.getCompanyId());
        companies.copy(resources);
        companiesRepository.save(companies);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long companyId : ids) {
            companiesRepository.deleteById(companyId);
        }
    }

    @Override
    public void download(List<CompaniesDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CompaniesDto tbCompanies : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("公司名称", tbCompanies.getCompanyName());
            map.put("地址", tbCompanies.getAddress());
            map.put("联系人", tbCompanies.getContactName());
            map.put("联系电话", tbCompanies.getContactTel());
            map.put("传真", tbCompanies.getFax());
            map.put("邮箱", tbCompanies.getEmail());
            map.put("手机号码", tbCompanies.getMobilePhone());
            map.put("企业性质", tbCompanies.getEnterpriseType());
            map.put("企业级别", tbCompanies.getEnterpriseLevel());
            map.put("成立时间", tbCompanies.getEstablisheTime());
            map.put("法人", tbCompanies.getLegalPerson());
            map.put("创建者", tbCompanies.getCreateBy());
            map.put("更新者", tbCompanies.getUpdateBy());
            map.put("创建日期", tbCompanies.getCreateTime());
            map.put("更新时间", tbCompanies.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}