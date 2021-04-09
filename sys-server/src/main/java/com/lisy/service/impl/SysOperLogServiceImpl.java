package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.SysOperLog;
import com.lisy.mapper.SysOperLogMapper;
import com.lisy.service.ISysOperLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

}
