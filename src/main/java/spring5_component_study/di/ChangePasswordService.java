package spring5_component_study.di;

import spring5_component_study.exception.MemberNotFoundException;

public class ChangePasswordService {
	private MemberDao memberDao;

	//
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
 
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);		// 원래 true 면 업데이트 해야한다.
	}	
}
