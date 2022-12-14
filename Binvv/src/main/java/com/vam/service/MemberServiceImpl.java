package com.vam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.MemberMapper;
import com.vam.model.MemberVO;

@Service //  MemberServiceImp.java 클래스에 @Service 어노테이션이 반드시 추가해주어야 함.
public class MemberServiceImpl implements MemberService  {
	
	
	@Autowired
	MemberMapper membermapper;

	@Override
	public void memberJoin(MemberVO member) throws Exception {
		membermapper.memberJoin(member);
	}


	@Override
	public int idCheck(String memberId) throws Exception {
		// TODO Auto-generated method stub
		return membermapper.idCheck(memberId);
	}


    /* 로그인 */
    @Override
    public MemberVO memberLogin(MemberVO member) throws Exception {
        
        return membermapper.memberLogin(member);
    }
 


}
