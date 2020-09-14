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
import me.zhengjie.domain.Qualification;
import me.zhengjie.service.QualificationService;
import me.zhengjie.service.dto.QualificationQueryCriteria;
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
@Api(tags = "企业资质管理")
@RequestMapping("/api/qualification")
public class QualificationController {

    private final QualificationService qualificationService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('qualification:list')")
    public void download(HttpServletResponse response, QualificationQueryCriteria criteria) throws IOException {
        qualificationService.download(qualificationService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询企业资质")
    @ApiOperation("查询企业资质")
    @PreAuthorize("@el.check('qualification:list')")
    public ResponseEntity<Object> query(QualificationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(qualificationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增企业资质")
    @ApiOperation("新增企业资质")
    @PreAuthorize("@el.check('qualification:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Qualification resources){
        return new ResponseEntity<>(qualificationService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改企业资质")
    @ApiOperation("修改企业资质")
    @PreAuthorize("@el.check('qualification:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Qualification resources){
        qualificationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除企业资质")
    @ApiOperation("删除企业资质")
    @PreAuthorize("@el.check('qualification:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        qualificationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}