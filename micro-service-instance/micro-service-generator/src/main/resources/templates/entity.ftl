package ${packageName}.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
* @Author: MachineGeek
* @Description: ${moduleName}类
* @Email: 794763733@qq.com
* @Date: ${date}
*/
@Data
@ApiModel(description = "${moduleName}")
public class ${className}{
<#list data as value>
    <#if value.columnKey == "PRI">
    @TableId(value = "`${value.columnName}`",type= IdType.ASSIGN_ID)
    <#else>
    @TableField(value = "`${value.columnName}`")
    </#if>
    @ApiModelProperty(value = "${value.columnComment}")
    private <#switch value.dataType><#case "bigint">Long<#break><#case "int">Integer<#break><#case "datetime">LocalDateTime<#break><#default>String</#switch> ${toHump(value.columnName)};
</#list>
}