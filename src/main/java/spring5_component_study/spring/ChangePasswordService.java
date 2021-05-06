package spring5_component_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring5_component_study.exception.MemberNotFoundException;

@Component
public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;

	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
 
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);		// 원래 true 면 업데이트 해야한다.
	}	
}
