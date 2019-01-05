package com.accp.chatroom.config;

import java.io.IOException;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.accp.chatroom.job.factory.JobAutowireFactoryBean;
import com.github.pagehelper.PageHelper;

@Configuration
@MapperScan(basePackages = {"com.accp.chatroom.dao"})
@EnableTransactionManagement
public class BeansConfig {

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
	/**
	 * 
	 * @title: quartzInitializerListener
	 * @description: 启用Quartz默认监听器
	 * @return
	 */
	@Bean
	public QuartzInitializerListener quartzInitializerListener() {
		return new QuartzInitializerListener();
	}

	/**
	 * 
	 * @title: quartzProperties
	 * @description: 配置Quartz属性文件
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		// 在quartz.properties中的属性被读取并注入后再初始化对象
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/**
	 * 
	 * @title: jobAutowireFactoryBean
	 * @description: 自定义QuartzJob中能植入springbean的工厂类
	 * @return
	 */
	@Bean
	public JobAutowireFactoryBean jobAutowireFactoryBean() {
		return new JobAutowireFactoryBean();
	}

	/**
	 * 
	 * @title: schedulerFactoryBean
	 * @description: Quartz任务调度工厂
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		sfb.setQuartzProperties(quartzProperties());// 读取配置文件
		sfb.setJobFactory(jobAutowireFactoryBean());// 支持Autowire
		return sfb;
	}

	/**
	 * 
	 * @title: scheduler
	 * @description: Quartz任务调度器【非常重要】,最终在Action中可以使用
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Scheduler scheduler() throws IOException {
		return schedulerFactoryBean().getScheduler();
	}
}
