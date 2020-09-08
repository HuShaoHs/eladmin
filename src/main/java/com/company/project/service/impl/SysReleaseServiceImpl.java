package com.company.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.SysRelease;
import com.company.project.mapper.SysReleaseMapper;
import com.company.project.service.SysReleaseService;
import org.springframework.stereotype.Service;

@Service("SysReleaseService")
public class SysReleaseServiceImpl extends ServiceImpl<SysReleaseMapper, SysRelease> implements SysReleaseService {
}
