package spring5_component_study.di;

import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;
// 등록 서비스
public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) { // 생성자로 memberDao를 받은거다.
		this.memberDao = memberDao;
	}
	// 이거는 개발자 마음이긴 한데 Member로 해도 됀다.
	// 근데 요방법은 멤버에 있는 내용이 정식으로 등록이 됀것이면 맴버로 받겟다는 말임.
	// 그 외에 떨거지들은 리퀘스트로 보겟다~
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateFormatFlagsException("dup email" + req.getEmail());
			// 예외를 따로 두어주는 이유는 어떤 어떤 에러인줄 알기 훨씬 수월해지기 위해서 써준다.
		}
		
	// update update 일떄나 update insert 나 insert insert 할때는 반드시 트렌젝션을 사용 해주는게 좋다.
	// 근데 여기선 select insert 이기에 안써줘도 괜찬타.(1나밖에 인서트가 안대자나)
		
		Member newMember = new Member(req.getEmail(), req.getName(), req.getPassword(), LocalDateTime.now()); 
		memberDao.insert(newMember);
		return newMember.getId();
	}


	
}
