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
import me.zhengjie.domain.Release;
import me.zhengjie.service.ReleaseService;
import me.zhengjie.service.dto.ReleaseQueryCriteria;
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
* @author hushao
* @date 2020-09-10
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "release管理")
@RequestMapping("/api/release")
public class ReleaseController {

    private final ReleaseService releaseService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('release:list')")
    public void download(HttpServletResponse response, ReleaseQueryCriteria criteria) throws IOException {
        releaseService.download(releaseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询release")
    @ApiOperation("查询release")
    @PreAuthorize("@el.check('release:list')")
    public ResponseEntity<Object> query(ReleaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(releaseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增release")
    @ApiOperation("新增release")
    @PreAuthorize("@el.check('release:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Release resources){
        return new ResponseEntity<>(releaseService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改release")
    @ApiOperation("修改release")
    @PreAuthorize("@el.check('release:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Release resources){
        releaseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除release")
    @ApiOperation("删除release")
    @PreAuthorize("@el.check('release:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        releaseService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}