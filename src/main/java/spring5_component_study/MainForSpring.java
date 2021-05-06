package spring5_component_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring5_component_study.config.AppCtx;
import spring5_component_study.exception.DuplicateMemberException;
import spring5_component_study.exception.MemberNotFoundException;
import spring5_component_study.exception.WrongIdPasswordException;
import spring5_component_study.spring.ChangePasswordService;
import spring5_component_study.spring.MemberInfoPrinter;
import spring5_component_study.spring.MemberListPrinter;
import spring5_component_study.spring.MemberRegisterService;
import spring5_component_study.spring.RegisterRequest;
import spring5_component_study.spring.VersionPrinter;

public class MainForSpring {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");		
				break;
			}
			
			if(command.startsWith("new")) {
				processNewCommand(command.split(" ")); // 뛰우기 해줘야한다. 뛰운 자리를 기준으로 배열로 만드러 주기때문에/
				System.out.println("command >> "+command);
				continue;
				
			}else if(command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if(command.equals("list")) {
				processListCommand();
				continue;
			}else if(command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			}else if(command.startsWith("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}
	// 버전 췤
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

	// 지정한 이메일로 사람 찾기.
	private static void processInfoCommand(String[] split) {
		if(split.length!=2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean(MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(split[1]);
	}

	// 전채 리스트 출력
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean( MemberListPrinter.class);
		listPrinter.printAll();
		
	}
	
	private static void processNewCommand(String[] split) {
		if(split.length !=5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		
		req.setEmail(split[1]);
		req.setName(split[2]);
		req.setPassword(split[3]);
		req.setConfirmPassword(split[4]);
		
		 if(!req.isPasswordEqualToconfirmPassword()) {
			 System.out.println("암호와 확인이 일치하지 않습니다./n");
			 return;
		 }
		 
		 try {
			 regSvc.regist(req);
			 System.out.println("등록했습니다./n");
		 }catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
		}
	}

	private static void processChangeCommand(String[] split) {
		ChangePasswordService changePwdSvc = ctx.getBean( ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(split[1], split[2], split[3]);
			System.out.println("암호를 변경했습니다./n");
		}catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다./n");
		}catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다. 꺼지세여");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명렁어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}
