package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.AuthorVO;
import com.vam.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private AuthorService authorService;
	// AuthorService 인터페이스 의존성 주입 코드 추가.
	
	
	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		logger.info("관리자 페이지 이동");
	}
	
	/* 상품 등록 페이지 접속 */
	@RequestMapping(value = "goodsManage", method = RequestMethod.GET)
	public void goodsManageGET() throws Exception{
		logger.info("상품 등록 페이지 접속");
	}
	
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }
    
    /* 작가 등록 페이지 접속 */
    @RequestMapping(value = "authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }
    
    /* 작가 관리 페이지 접속 */
    @RequestMapping(value = "authorManage", method = RequestMethod.GET)
    public void authorManageGET() throws Exception{
        logger.info("작가 관리 페이지 접속");
    }    
    
    /* 작가 등록 */
    @RequestMapping(value="authorEnroll.do", method = RequestMethod.POST)
    // AuthorVO 객체는 View가 전송하는 작가 관련 데이터를 받기 위해서임.
    // RedirectAttributes 객체는 해당 메서드가 종료된 뒤 리다이렉트 방식으로 다른 페이지로 전송할 때,
    // 성공 메세지를 전송하기 위해서 추가하였음.
    public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
    	
    	logger.info("authorEnroll : " + author );
    	// 메서드에 들어온 기록과 뷰로부터 전달받은 데이털르 확인하기 위한 로그 코드를 추가함.
    	
    	authorService.authorEnroll(author);		// 작가 등록 쿼리 수행
    	// authorService.java의 authorEnroll 메서드를 호출하여,
    	// 뷰로부터 전달받은 등록할 작가 정보가 담긴 AuthorVO 변수를 매개변수로 작성함.
    	
    	rttr.addFlashAttribute("enrool_result", author.getAuthorName());
    	// 작가 등록 성공 후 이동할 페이지가 작가 관리 페이지로, 존재할 시 작가 등록에 성공하였다는 경고창을 뜨도록 할 것임.
    	// 근데 서버로부터 전송된 데이터가 계속 남아있다면 해당 경고창은 계속 뜰 것이기 떄문에 일회성인 addFlashAttribute 메서드를 사용하였음.
    	
    	return "redirect:/admin/authorManage";
    	// redirect 방식으로 작가 목록 페이지로 이동하도록 리턴값을 추가해줌.
    	
    }
 

}
