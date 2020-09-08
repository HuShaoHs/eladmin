package com.company.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.aop.annotation.LogAnnotation;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.common.job.utils.ScheduleJob;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.SysContentEntity;
import com.company.project.entity.SysJobEntity;
import com.company.project.entity.SysRelease;
import com.company.project.service.SysReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/sysRelease")
@Api(tags = "发布-发布需求")
@RestController
public class SysReleaseController {

    @Resource
    private SysReleaseService sysReleaseService;

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
//    @RequiresPermissions("sysRelease:list")
    public DataResult findListByPage(@RequestBody SysRelease sysRelease) {
        Page page = new Page(sysRelease.getPage(), sysRelease.getLimit());
        LambdaQueryWrapper<SysRelease> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysRelease.getUserId())) {
            queryWrapper.eq(SysRelease::getUserId, sysRelease.getUserId());
        }
        IPage<SysContentEntity> iPage = sysReleaseService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "新增")
    @LogAnnotation(title = "新增")
    @PostMapping("/add")
//    @RequiresPermissions("sysRelease:add")
    public DataResult add(@RequestBody SysRelease sysRelease) {
          if (ObjectUtils.isEmpty(sysRelease)){
              return DataResult.fail("入参不能为空");
          }
        sysReleaseService.save(sysRelease);
        return DataResult.success();
    }


    @ApiOperation(value = "更新")
    @LogAnnotation(title = "更新")
    @PostMapping("/update")
//    @RequiresPermissions("sysRelease:update")
    public DataResult update(@RequestBody SysRelease sysRelease) {
        if (ObjectUtils.isEmpty(sysRelease)){
            return DataResult.fail("入参不能为空");
        }
        sysReleaseService.updateById(sysRelease);
        return DataResult.success();
    }
}
