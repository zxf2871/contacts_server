package com.sectong.controller;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		
		registry.addViewController("/admin/profile").setViewName("admin/profile");		
		
		registry.addViewController("/hello").setViewName("hello");
	}

//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//				Ssl ssl = new Ssl();
//				//Server.jks中包含服务器私钥和证书
//				ssl.setKeyStore("keystore.p12");
//				ssl.setKeyStorePassword("1qaz@WSX");
//				container.setSsl(ssl);
//				container.setPort(8442);
//			}
//		};
//	}
	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory factory =
				new TomcatEmbeddedServletContainerFactory() {
					@Override
					protected void postProcessContext(Context context) {
						//SecurityConstraint必须存在，可以通过其为不同的URL设置不同的重定向策略。
						SecurityConstraint securityConstraint = new SecurityConstraint();
						securityConstraint.setUserConstraint("CONFIDENTIAL");
						SecurityCollection collection = new SecurityCollection();
						collection.addPattern("/b8a3/*");
						securityConstraint.addCollection(collection);
						context.addConstraint(securityConstraint);
					}
				};
		factory.addAdditionalTomcatConnectors(createHttpConnector());
		return factory;
	}

	private Connector createHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setSecure(false);
		connector.setPort(8080);
		connector.setRedirectPort(8443);
		return connector;
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/b8a3/**")
				.addResourceLocations("classpath:/b8a3/");
	}
}
