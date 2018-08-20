package me.helllp.tools.afactory.web;

import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.helllp.tools.afactory.bean.GlobalConfigBean;
import me.helllp.tools.afactory.bean.PluginInfoBean;
import me.helllp.tools.afactory.core.BasePluginBean;
import me.helllp.tools.afactory.core.TemplateHealper;

@RestController
public class CreateSourceController  {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private GlobalConfigBean globalBean;
	
	/**
	 * 获取平台数据
	 * 
	 * @return
	 */
	@GetMapping("/")
	public GlobalConfigBean global() {
		return globalBean;
	}
	
	/**
	 * 获取插件的信息
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 * @throws ClassNotFoundException
	 */
	@GetMapping("/p")
	public BasePluginBean plugin(String name) throws BeansException, ClassNotFoundException {
		Optional<PluginInfoBean> plugBean =  globalBean.getPlugins().stream().filter(s -> s.getName().equals(name)).findFirst();
		
		return (BasePluginBean)context.getBean(Class.forName(plugBean.get().getBeanClass()));
	}
	
	@GetMapping("/g")
	public String createSrc(String plugInName) throws Exception {
		Optional<PluginInfoBean> plugBean =  globalBean.getPlugins().stream().filter(s -> s.getName().equals(plugInName)).findFirst();
		
		BasePluginBean bean = (BasePluginBean)context.getBean(Class.forName(plugBean.get().getBeanClass()));
		
		TemplateHealper.createPluginSrc(globalBean, bean);
		
		return "OK";
	}
}
