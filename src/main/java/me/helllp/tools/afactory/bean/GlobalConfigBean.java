package me.helllp.tools.afactory.bean;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 自动化代码的配置信息
 * 
 * @author 林晓明
 *
 */
@Component
@Data
@ConfigurationProperties(prefix = "globalSetting")
public class GlobalConfigBean {
	private SystemDataBean systemData;
	
	private Map<String,String> systemDefine;
	
	private List<PluginInfoBean> plugins;
}
