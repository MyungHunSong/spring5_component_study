package spring5_component_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring5_component_study.spring.MemberPrinter;
import spring5_component_study.spring.VersionPrinter;


@Configuration
@ComponentScan(basePackages = {"spring5_component_study.spring"})
public class AppCtx {
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	
	
	
	
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
