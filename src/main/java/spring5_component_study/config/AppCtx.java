package spring5_component_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring5_component_study.di.ChangePasswordService;
import spring5_component_study.di.MemberDao;
import spring5_component_study.di.MemberInfoPrinter;
import spring5_component_study.di.MemberListPrinter;
import spring5_component_study.di.MemberPrinter;
import spring5_component_study.di.MemberRegisterService;
import spring5_component_study.di.VersionPrinter;


@Configuration
public class AppCtx {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	// 회원 리스트로 출력하기
	@Bean
	public MemberListPrinter listPrinter(MemberDao memberDao, MemberPrinter printer) {
		return new MemberListPrinter(memberDao, printer);
	}
	
	// 이메일로 회원 출력
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
