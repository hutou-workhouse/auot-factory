package me.helllp.tools.afactory.core;

import java.io.IOException;

import freemarker.template.TemplateException;
import me.helllp.tools.afactory.bean.GlobalConfigBean;

/**
 * 插件的数据后续处理机制！
 * 
 * @author 林晓明
 *
 */
public interface FactoryPlugin<T extends BasePluginBean> {
	default  void convert(GlobalConfigBean globalBean, T t) throws TemplateException, IOException{
		TemplateHealper.convertPluginBean(globalBean, t);
	}
}
