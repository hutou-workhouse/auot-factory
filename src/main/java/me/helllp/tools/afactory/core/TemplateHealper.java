package me.helllp.tools.afactory.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import me.helllp.tools.afactory.bean.GlobalConfigBean;
import me.helllp.tools.afactory.tools.FileUtil;
import me.helllp.tools.afactory.tools.FreeMarkerUtil;

/**
 * 模板生成代码的帮助类
 * 
 * @author 林晓明
 *
 */
public class TemplateHealper {
	private static Configuration cfg = null;

	static {
		try {
			cfg = FreeMarkerUtil.getFreeMarkerCfg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPackagePath(String basePackage) {
		return basePackage.replaceAll("\\.", "/");
	}
	
	/**
	 * 构造模板引擎用的数据
	 * 
	 * @param globalBean	全局数据
	 * @param localBean		单个插件使用
	 * @return
	 */
	public static Map<String, Object> makeTemplateData(GlobalConfigBean globalBean, BasePluginBean localBean){
		Map<String, Object> docMap = new HashMap<String,Object>();
		
		docMap.put("system", globalBean);
		docMap.put("local", localBean);

		return docMap;
	}

	/**
	 * 根据文件名模板进行解析
	 * 
	 * @param globalBean
	 * @param localBean
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static void convertPluginBean(GlobalConfigBean globalBean, BasePluginBean localBean) throws TemplateException, IOException {
		Map<String, Object> docMap = makeTemplateData(globalBean, localBean);
		
		//	文件名模板加载器
		StringTemplateLoader stringLoader = new StringTemplateLoader();

		for(TemplateFileInfoBean info : localBean.getFileInfos()) {
			//	加载模板，给模板命名
			stringLoader.putTemplate(info.getFileName(), info.getFileName());
			stringLoader.putTemplate(info.getPathName(), info.getPathName());
			stringLoader.putTemplate(info.getTemplate(), info.getTemplate());
			
			info.setPathName(parser(stringLoader,info.getPathName(),docMap));
			info.setFileName(parser(stringLoader,info.getFileName(),docMap));
			info.setTemplate(parser(stringLoader,info.getTemplate(),docMap));
		}
	}
	
	private static String parser(StringTemplateLoader tempLoader, String name, Map<String, Object> docMap) throws TemplateException, IOException {
		cfg.setTemplateLoader(tempLoader);
		
		Template template = cfg.getTemplate(name);
		
        Writer out = new StringWriter();
        template.process(docMap, out);
        
        return out.toString();
	}
	
	/**
	 * 生成实际的代码
	 * 
	 * @param globalBean
	 * @param localBean
	 * @throws Exception 
	 */
	public static void createPluginSrc(GlobalConfigBean globalBean, BasePluginBean localBean) throws Exception {
		Map<String, Object> docMap = makeTemplateData(globalBean, localBean);
		
		cfg.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtil.class, "/"));
		
		for(TemplateFileInfoBean info : localBean.getFileInfos()) {
			Template template  = cfg.getTemplate(info.getTemplate());
			FileUtil.makeDir(info.getPathName());
			FreeMarkerUtil.output(docMap, info.getPathName() + info.getFileName(), template);
		}
	}
}
