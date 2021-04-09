package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.SysJobLog;
import com.lisy.mapper.SysJobLogMapper;
import com.lisy.service.ISysJobLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements ISysJobLogService {

}
