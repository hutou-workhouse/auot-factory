package ${local.userDefines.basePackagePath}.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 所有输入参数的基类
 *
 * @author autoFactory 
 */
@Data
public class BaseParams {
<#list local.params as item>
    @ApiModelProperty(value = "${item.description}", dataType = "${item.type}", required = true)
    private String userId;

</#list>
}
