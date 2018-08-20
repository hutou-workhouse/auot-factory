package me.helllp.tools.afactory;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

import freemarker.template.TemplateException;
import me.helllp.tools.afactory.bean.GlobalConfigBean;
import me.helllp.tools.afactory.bean.PluginInfoBean;
import me.helllp.tools.afactory.core.BasePluginBean;
import me.helllp.tools.afactory.core.FactoryPlugin;

public class FactoryRunListener implements SpringApplicationRunListener {
    public FactoryRunListener(SpringApplication sa, String[] args) {
    }

    @Override
    public void starting() {
        System.out.println("自定义starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("自定义environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("自定义contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

        System.out.println("自定义contextLoaded");
    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {
    	GlobalConfigBean globalBean = context.getBean(GlobalConfigBean.class);

    	List<PluginInfoBean> pluginList = globalBean.getPlugins();
    	
    	for(PluginInfoBean bean : pluginList) {
    		try {
				Class<?> plugInClass = Class.forName(bean.getPluginClass());
				Class<?> beanClass = Class.forName(bean.getBeanClass());
				
				@SuppressWarnings("unchecked")
				FactoryPlugin<BasePluginBean> pluginObj = (FactoryPlugin<BasePluginBean>)plugInClass.newInstance();
//				BasePluginBean beanObj = (BasePluginBean)beanClass.newInstance();
				//	需要构建bean的数据，通过配置文件
				org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
				BasePluginBean beanObj = (BasePluginBean)yaml.loadAs(new ClassPathResource(bean.getResourcePath()).getInputStream(), beanClass);
				
				//	进行自定义的数据转换！
				pluginObj.convert(globalBean, beanObj);
				//	进行容器内组件的注册
				context.getBeanFactory().registerSingleton(beanObj.getClass().getName(), beanObj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        System.out.println("自定义finished");
    }
}