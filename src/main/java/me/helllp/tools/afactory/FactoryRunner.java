package me.helllp.tools.afactory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class FactoryRunner {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FactoryRunner.class, args);
    }
    
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//		yaml.setResources(new FileSystemResource("config.yml"));// File引入
		yaml.setResources(new ClassPathResource("auto-config/config.yml"));//class引入
		configurer.setProperties(yaml.getObject());
		
		return configurer;
	}
}
