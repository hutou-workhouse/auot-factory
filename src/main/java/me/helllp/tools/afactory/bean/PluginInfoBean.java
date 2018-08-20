package me.helllp.tools.afactory.bean;

import lombok.Data;

@Data
public class PluginInfoBean {
	private String name;
	
	private String pluginClass;
	
	private String beanClass;
	
	private String resourcePath;
	
	private boolean autoWrite;
	
	private String description;
}
