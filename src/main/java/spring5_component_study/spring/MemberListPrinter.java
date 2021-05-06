package spring5_component_study.spring;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(e->printer.print(e));
	}
}
