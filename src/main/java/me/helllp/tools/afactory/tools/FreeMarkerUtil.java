package me.helllp.tools.afactory.tools;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * FreeMarker相关操作工具类
 * 
 * @author 林晓明
 * 
 */
public class FreeMarkerUtil {

	/**
	 * 初始化FreeMarker
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Configuration getFreeMarkerCfg() throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setDefaultEncoding("UTF-8");
		
		// 配置模板的位置
		cfg.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtil.class, "/"));
		
		return cfg;
	}
	
	/**
	 * 输出根据模板产生的代码
	 */
	public static void output(Map<String, Object> docMap, String fileName, Template template) throws Exception {
		FileWriter fileWriter = new FileWriter(fileName);
		template.process(docMap, fileWriter);
		fileWriter.flush();
		fileWriter.close();			
	}	
	
	/**
	 * 输出为字符串
	 * 
	 * @param docMap
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public static String outputBuffer(Map<String, Object> docMap, Template template) throws Exception {
		StringWriter sw = new StringWriter();
		template.process(docMap, sw);
		String buffer = sw.toString();
		sw.close();
		
		return buffer;
	}	
	
	/**
	 * 使用例子，不要真正调用
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void myUsage() throws Exception{
		Configuration cfg = getFreeMarkerCfg();
		
		Map<String, Object> docMap = new HashMap<String, Object>();
		docMap.put("key", "value");
		Template template = cfg.getTemplate("test/test1.ftl");
		
		output(docMap,"c:\\fileName.java",template);		
	}
}
