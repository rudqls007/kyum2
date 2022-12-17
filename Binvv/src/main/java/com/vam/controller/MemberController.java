package com.vam.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vam.model.MemberVO;
import com.vam.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/member")	// MemberController 사용을 명확히 구분해주기 위해 회원과 관련된 호출은 url 경로에 member를 타도록 설계함.
@Log4j
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	// @Autowired를 통해서 MemberService.java가 MemberController.java에 자동 주입되도록 코드를 추가함
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		log.info("회원가입 페이지 진입");
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		log.info("join 진입");
		
		// 회원가입 서비스 실행
		memberservice.memberJoin(member);
		
		log.info("join Service 성공");
		
		return "redirect:/main";
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		log.info("로그인 페이지 진입");
	}
	

	
	
	
}
