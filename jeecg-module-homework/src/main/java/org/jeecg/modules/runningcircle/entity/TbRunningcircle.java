package org.jeecg.modules.runningcircle.entity;

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
 * @Description: 运动圈子
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Data
@TableName("tb_runningcircle")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb_runningcircle对象", description="运动圈子")
public class TbRunningcircle implements Serializable {
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
	/**圈子名称*/
	@Excel(name = "圈子名称", width = 15)
    @ApiModelProperty(value = "圈子名称")
    private java.lang.String circlename;
	/**圈子描述*/
	@Excel(name = "圈子描述", width = 15)
    @ApiModelProperty(value = "圈子描述")
    private java.lang.String description;
	/**成员数量*/
	@Excel(name = "成员数量", width = 15)
    @ApiModelProperty(value = "成员数量")
    private java.lang.Integer memberslimit;
	/**是否为公开圈子*/
	@Excel(name = "是否为公开圈子", width = 15)
    @ApiModelProperty(value = "是否为公开圈子")
    private java.lang.String ispublic;
}
