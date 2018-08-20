package me.helllp.tools.afactory.core;

import lombok.Data;

/**
 * 模板和文件的信息
 * 
 * @author 林晓明
 *
 */
@Data
public class TemplateFileInfoBean {
	/**
	 * 目标文件的名称
	 */
	private String fileName;
	
	/**
	 * 目标文件的路径
	 */
	private String pathName;
	
	/**
	 * 模板
	 */
	private String template;
}
