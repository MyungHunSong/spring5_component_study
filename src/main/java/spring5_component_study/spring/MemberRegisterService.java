package spring5_component_study.spring;

import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;

import org.springframework.stereotype.Component;
// 등록 서비스
@Component
public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) { 
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateFormatFlagsException("dup email" + req.getEmail());
			
		}
		
		Member newMember = new Member(req.getEmail(), req.getName(), req.getPassword(), LocalDateTime.now()); 
		memberDao.insert(newMember);
		return newMember.getId();
	}


	
}
