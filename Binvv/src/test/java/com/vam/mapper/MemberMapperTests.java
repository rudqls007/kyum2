package com.vam.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	
	@Autowired
	private MemberMapper memberMapper;			// MemberMapper.java 인터페이스 의존성 주입
	
	// 회원가입 쿼리 테스트 메서ㅡ
	
	
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("spring_test");
		member.setMemberPw("spring_test");
		member.setMemberName("spring_test");
		member.setMemberMail("spring_test");
		member.setMemberAddr1("spring_test");
		member.setMemberAddr2("spring_test");
		member.setMemberAddr3("spring_test");
		
		memberMapper.memberJoin(member);            // 쿼리 메서드 실행
											
		
	}
}
