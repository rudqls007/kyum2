package com.vam.controller;

import java.io.File;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.MemberVO;
import com.vam.service.MemberService;

@Controller
@RequestMapping(value = "/member") // MemberController 사용을 명확히 구분해주기 위해 회원과 관련된 호출은 url 경로에 member를 타도록 설계함.
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MemberService memberservice;
	// @Autowired를 통해서 MemberService.java가 MemberController.java에 자동 주입되도록 코드를 추가함
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	// BCryptPasswordEncoder를 Autowired 어노테이션을 이용해서 의존성 주입을 해줌.

@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용" + "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
        String from = "dlrudqls55@naver.com";
        String to = "받는이 아이디@도메인주소";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.
            FileSystemResource file = new FileSystemResource(new File("D:\\test.txt")); 
            mailHelper.addAttachment("업로드파일.형식", file);
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }


	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void loginGET() {
		logger.info("회원가입 페이지 진입");
	}

	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		String rawPw = "";			// 인코딩 전 비밀번호
		String encodePw = "";		// 인코딩 후 비밀번호
		
		rawPw = member.getMemberPw();			// 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw);		// 비밀번호 인코딩
		member.setMemberPw(encodePw);			// 인코딩된 비밀번호 member객체에 다시 저장
		
		/* 회원가입 쿼리 실행 */
		memberservice.memberJoin(member);
	
		return "redirect:/main";
	}
		

	// 로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		logger.info("로그인 페이지 진입");
	}

	// 아이디 중복 검사
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {

		logger.info("memberIdChk() 진입");

		int result = memberservice.idCheck(memberId);

		logger.info("결과값 = " + result);

		if (result != 0) {

			return "fail"; // 중복 아이디가 존재

		} else {

			return "success"; // 중복 아이디 x

		}

	} // memberIdChkPOST() 종료

	
	
	  /* 이메일 인증 */
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    /* 변경후 */
    public String mailCheckGET(String email) throws Exception{
        
        /* 뷰(View)로부터 넘어온 데이터 확인 */
        logger.info("이메일 데이터 전송 확인");
        
        
        /* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111; // 111111 ~ 999999 범위의 숫자를 얻기 위해서 nextInt(888888) + 111111를 사용함. 
        logger.info("인증번호 : " + checkNum);
        
        /* 이메일 보내기 */
        String setFrom = "rudqls007@gmail.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content = 
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        			
        
		
		  try {
		  
		  MimeMessage message = mailSender.createMimeMessage(); MimeMessageHelper
		  helper = new MimeMessageHelper(message, true, "utf-8");
		  helper.setFrom(setFrom); helper.setTo(toMail); helper.setSubject(title);
		  helper.setText(content,true); mailSender.send(message);
		  
		  }catch(Exception e) { e.printStackTrace(); }
		 
        
      	String num = Integer.toString(checkNum);
      	
      	return num;
    }    	
	// MemberController.java 에 로그인 기능을 수행하는 메서드를 작성함. 
	// 파라미터로는 MemberVO, HttpServletRequest, RedirectAttributes를 사용함.
	// MemberVo는 데이터를 전달받기 위해, HttpServletRequest는 로그인 성공 시 session에 회원 정보를 저장하기 위해, 
	// RedirectAttributes는 로그인 실패 시 리다이렉트 된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해 사용함.

    
    
    /* 로그인 */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
    	HttpSession session = request.getSession();
    	// HttpSession 클래스 타입의 참조변수 session을 초기화함.
    	String rawPw = "";
    	String encodePw = "";
    	// String타입의 rawPw, encodePw 변수를 선언 및 초기화함.
    	// rawPw, encodePw는 제출받은 비밀번호와 인코딩 된 비밀번호를 잠시 저장하기 위한 용도로 사용할 것임.
    	MemberVO lvo = memberservice.memberLogin(member);						// 제출한 아이디와 일치하는 아이디가 있는지 확인.
    	// memberLogin() 메서드를 호출하여 쿼리가 실행되도록 함.
    	// 실행된 결과로 반환받는 MemberVO 인스턴스의 주소를 MemberVo타입의 lvo 변수에 저장함.
    	
    	if(lvo != null) {						// 일치하는 아이디 존재시
    		
    		rawPw = member.getMemberPw();		// 사용자가 제출한 비밀번호
    		encodePw = lvo.getMemberPw();		// 데이터베이스에 저장한 인코딩된 비밀번호
    		
    		if(true == pwEncoder.matches(rawPw, encodePw)) {	// 비밀번호 일치여부 판단
    		// matches() 메서드가 true를 반환하는지 하지 않는지 조건문으로 작성.	
    			
    			lvo.setMemberPw("");							// 인코딩된 비밀번호 정보 지움.
    			session.setAttribute("member", lvo);			// session에 사용자의 정보 저장
    			return "redirect:/main";						// 메인페이지 이동
    			
    		}else {
    			
        		rttr.addFlashAttribute("result", 0);
        		return "redirect:/member/login";				// 로그인 페이지로 이동
        		
    		}
    		
    	}else {									// 일치하는 아이디가 존재하지 않을 시 (로그인 실패)
    		
    		rttr.addFlashAttribute("result", 0);
    		return "redirect:/member/login";
    		
    	}
    }
    
    
//    /* 로그인 */
//    @RequestMapping(value="login", method=RequestMethod.POST)
//    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
//
//        System.out.println("login 메서드 진입");
//        System.out.println("전달된 데이터 : " + member);
//    	HttpSession session = request.getSession();
//    	//  두 가지의 변수를 선언 및 초기화를 진행할 것입니다. 먼저 session을 사용하기 위해 session 변수를 선언하고 request.getSession()으로 초기화
//    	MemberVO lvo = memberservice.memberLogin(member);
//    	//  memberLogin 메서드를 요청하게 되면 MemberMapper.java를 거쳐서 로그인 쿼리가 실행이 되게 되고 그 결과 값이 담긴 MemberVO 객체를 반환받아서 변수 lvo에 저장
//    	
//    	if (lvo == null) {
//    		int result = 0;
//    		rttr.addFlashAttribute("result", result);
//    		return "redirect:/member/login";
//    	}
//    	
//    	session.setAttribute("member", lvo);
//    	
//        return "redirect:/main";
//    }
    
    /* 메인페이지 로그아웃 */
    @RequestMapping(value = "logout.do", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
    	// a 태그의 요청 방식은 GET방식이므로 GET 방식으로 만들었음.
    	// a 태그 속성에서 작성한 url 경로인 => logout.do를 넣어줌.
    	// session에 대한 작업을 해야하기 떄문에 HttpServletRequest 타입의 매개변수를 작성함.
    	
    	logger.info("logoutMainGET 메서드 진입");
    	
    	HttpSession session = request.getSession();
    	// 세선을 제거하는 작업을 해야 하기 대문에 HttpSession타입의 session 변수 및 초기화를 합니다.
    	
    	session.invalidate();
    	// session을 제거할 수 있는 메서드가 2개("invalidate()", "removeAttribute()") 있다. 
    	// "invalidate()" 메서드의 경우 세션 전체를 무효화하는 메서드 
    	// "removeAttribute()" 의 경우 특정 이름으로 네이밍 한 session 객체를 타기팅하여 삭제하는 메서드
    	// 로그아웃을 할 경우 사용될 세션이 없기 때문에 invalidate() 메서드를 사용함.
    	
    	return "redirect:/main";
    	
    }
 

}
