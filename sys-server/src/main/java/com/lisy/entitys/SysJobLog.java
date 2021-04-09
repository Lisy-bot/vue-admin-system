package com.lisy.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 定时任务调度日志表
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysJobLog对象", description="定时任务调度日志表")
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务日志ID")
    @TableId(value = "job_log_id", type = IdType.AUTO)
    private Long jobLogId;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务组名")
    private String jobGroup;

    @ApiModelProperty(value = "调用目标字符串")
    private String invokeTarget;

    @ApiModelProperty(value = "日志信息")
    private String jobMessage;

    @ApiModelProperty(value = "执行状态（0正常 1失败）")
    private String status;

    @ApiModelProperty(value = "异常信息")
    private String exceptionInfo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
