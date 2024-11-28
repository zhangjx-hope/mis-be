package org.jeecg.modules.runningdata.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 运动记录
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Data
@TableName("tb_runningdata")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb_runningdata对象", description="运动记录")
public class TbRunningdata implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**本次运动标识*/
	@Excel(name = "本次运动标识", width = 15)
    @ApiModelProperty(value = "本次运动标识")
    private java.lang.String runid;
    /**记录人*/
    @Excel(name = "记录人", width = 15)
    @ApiModelProperty(value = "记录人")
    private java.lang.String userId;
	/**运动时长*/
	@Excel(name = "运动时长", width = 15)
    @ApiModelProperty(value = "运动时长")
    private java.lang.Double runtime;
	/**运动配速*/
	@Excel(name = "运动配速", width = 15)
    @ApiModelProperty(value = "运动配速")
    private java.lang.Double pace;
	/**经度坐标*/
	@Excel(name = "经度坐标", width = 15)
    @ApiModelProperty(value = "经度坐标")
    private java.lang.String trajectoryx;
	/**维度坐标*/
	@Excel(name = "维度坐标", width = 15)
    @ApiModelProperty(value = "维度坐标")
    private java.lang.String trajectoryy;
	/**运动日期*/
	@Excel(name = "运动日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "运动日期")
    private java.util.Date rundate;
}
