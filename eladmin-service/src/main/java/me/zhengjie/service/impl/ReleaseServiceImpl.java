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

import me.zhengjie.domain.Release;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.ReleaseRepository;
import me.zhengjie.service.ReleaseService;
import me.zhengjie.service.dto.ReleaseDto;
import me.zhengjie.service.dto.ReleaseQueryCriteria;
import me.zhengjie.service.mapstruct.ReleaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
* @author hushao
* @date 2020-09-10
**/
@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;

    @Override
    public Map<String,Object> queryAll(ReleaseQueryCriteria criteria, Pageable pageable){
        Page<Release> page = releaseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(releaseMapper::toDto));
    }

    @Override
    public List<ReleaseDto> queryAll(ReleaseQueryCriteria criteria){
        return releaseMapper.toDto(releaseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ReleaseDto findById(String id) {
        Release release = releaseRepository.findById(id).orElseGet(Release::new);
        ValidationUtil.isNull(release.getId(),"Release","id",id);
        return releaseMapper.toDto(release);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReleaseDto create(Release resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return releaseMapper.toDto(releaseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Release resources) {
        Release release = releaseRepository.findById(resources.getId()).orElseGet(Release::new);
        ValidationUtil.isNull( release.getId(),"Release","id",resources.getId());
        release.copy(resources);
        releaseRepository.save(release);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            releaseRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ReleaseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ReleaseDto release : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" userId",  release.getUserId());
            map.put("汇款公司", release.getRemittanceCompany());
            map.put("汇款方式", release.getRemittanceType());
            map.put("0为正常，1为驳回状态", release.getStatus());
            map.put("推荐公司", release.getRecommendedCompany());
            map.put("项目名称", release.getEntryName());
            map.put("省份", release.getProvince());
            map.put("行业", release.getIndustry());
            map.put("负责人", release.getPersonCharge());
            map.put("主投公司 ", release.getMainCompany());
            map.put("收款账号 ", release.getCollectionAccountNo());
            map.put("收款行号 ", release.getReceivingBankNo());
            map.put("收款户名", release.getAccountName());
            map.put("收款金额", release.getAmountCollected());
            map.put("汇款摘要", release.getRemittanceSummary());
            map.put("开标日期", release.getOpeningDate());
            map.put("招标公告", release.getTenderAnnouncement());
            map.put("'是否删除(0未删除；1已删除)',", release.getDeleted());
            map.put("创建者", release.getCreateBy());
            map.put("更新者", release.getUpdateBy());
            map.put("创建日期", release.getCreateTime());
            map.put("更新时间", release.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}