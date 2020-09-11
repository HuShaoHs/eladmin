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
package me.zhengjie.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.domain.Companies;
import me.zhengjie.service.CompaniesService;
import me.zhengjie.service.dto.CompaniesQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author sding
* @date 2020-09-10
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "companies管理")
@RequestMapping("/api/tbCompanies")
public class CompaniesController {

    private final CompaniesService companiesService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tbCompanies:list')")
    public void download(HttpServletResponse response, CompaniesQueryCriteria criteria) throws IOException {
        companiesService.download(companiesService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询companies")
    @ApiOperation("查询companies")
    @PreAuthorize("@el.check('tbCompanies:list')")
    public ResponseEntity<Object> query(CompaniesQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(companiesService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增companies")
    @ApiOperation("新增companies")
    @PreAuthorize("@el.check('tbCompanies:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Companies resources){
        return new ResponseEntity<>(companiesService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改companies")
    @ApiOperation("修改companies")
    @PreAuthorize("@el.check('tbCompanies:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Companies resources){
        companiesService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除companies")
    @ApiOperation("删除companies")
    @PreAuthorize("@el.check('tbCompanies:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        companiesService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}