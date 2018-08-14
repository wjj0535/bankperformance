package org.wangjj.bankperformance;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.wangjj.bankperformance.Constant.GlobalData;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@ComponentScan("org.wangjj.bankperformance")
@MapperScan("org.wangjj.bankperformance.Mapper")
public class BankPerformanceApplication extends WebMvcConfigurerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(BankPerformanceApplication.class);
	
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		
		context = SpringApplication.run(BankPerformanceApplication.class, args);
		
//		if(!InitGlobalData())
//		{
//			logger.error("配置文件缺少相关配置，启动失败");
//			shutdown();
//		}
//		
	}
	
	public static void shutdown()
	{
		context.close();
	}
	//不知道从哪获取配置，只知道从这可以
	public static boolean InitGlobalData()
	{
		if(context == null)
			return false;
		return GlobalData.initGlobalData(context);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		// 1.需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		// 3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);
		// 4、将convert添加到converters当中.
		converters.add(fastConverter);
	}
}
