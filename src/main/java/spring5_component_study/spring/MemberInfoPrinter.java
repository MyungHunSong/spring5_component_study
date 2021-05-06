package spring5_component_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring5_component_study.exception.MemberNotFoundException;

@Component
public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		printer.print(member);
		System.out.println();
	}

}
