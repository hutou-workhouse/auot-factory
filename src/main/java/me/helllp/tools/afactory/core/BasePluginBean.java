package me.helllp.tools.afactory.core;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class BasePluginBean {
	/**
	 * 自动生成的文件信息
	 */
	private List<TemplateFileInfoBean> fileInfos;
	
	/**
	 * 用户自定义的插件内信息
	 */
	private Map<String,String> userDefines;

}
