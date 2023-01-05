<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/authorManage.css">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<style type="text/css">
*{
    margin: 0;
    padding:0;
}
a{
    text-decoration: none;
}
ul{
    list-style: none;
}
/* 화면 전체 렙 */
.wrapper{
    width: 100%;
}
/* content 랩 */
.wrap{
    width : 1080px;
    margin: auto;
}
/* 홈페이지 기능 네비 */ 
.top_gnb_area{
    width: 100%;
    height: 50px;
    background-color: #f0f0f1;
    position:relative;
}
.top_gnb_area .list{
    position: absolute;
    top: 0px;
    right: 0;
    
}
.top_gnb_area .list li{
    list-style: none;    
    float : left;
    padding: 13px 15px 0 10px;
    font-weight: 900;
    cursor: pointer;
}
 
/* 관리제 페이지 상단 현페이지 정보 */
.admin_top_wrap{
    height:110px;
    line-height: 110px;
    background-color: #5080bd;
    margin-bottom:15px;
}
.admin_top_wrap>span{
    margin-left: 30px;
    display:inline-block;
    color: white;
    font-size: 50px;
    font-weight: bolder;
}
/* 관리자 wrap(네비+컨텐츠) */
.admin_wrap{
    
    
}
 
/* 관리자페이지 네비 영역 */
.admin_navi_wrap{
    width: 20%;
    height: 300px;
    float:left;
    height: 100%;
}
.admin_navi_wrap li{
    display: block;
    height: 80px;
    line-height: 80px;
    text-align: center;
}
.admin_navi_wrap li a{
    display: block;
    height: 100%;
    width: 95%;
    margin: 0 auto;
    cursor: pointer;
    font-size: 30px;
    font-weight: bolder;
}
.admin_navi_wrap li a:link {color: black;}
.admin_navi_wrap li a:visited {color: black;}
.admin_navi_wrap li a:active {color: black;}
.admin_navi_wrap li a:hover {color: black;}
 
.admin_list_04{
    background-color: #c8c8c8;
} 
 
 
/* 관리자페이지 컨텐츠 영역 */
.admin_content_wrap{
    width: 80%;
    float:left;
    height: 100%;
    height: 700px;
    
}
.admin_content_subject{            /* 관리자 컨텐츠 제목 영역 */
    font-size: 40px;
    font-weight: bolder;
    padding-left: 15px;
    background-color: #6AAFE6;
    height: 80px;
    line-height: 80px;
    color: white;    
}
 
/* footer navai 영역 */
.footer_nav{
    width:100%;
    height:50px;
}
.footer_nav_container{
    width: 100%;
    height: 100%;
    background-color:#8EC0E4;
}
.footer_nav_container>ul{
    font-weight : bold;
    float:left;
    list-style:none;
    position:relative;
    padding-top:10px;
    line-height: 27px;
    font-family: dotum;
    margin-left: 10px;
}
.footer_nav_container>ul>li{
    display:inline;
    width: 45px;
    height: 19px;
    padding: 10px 9px 0 10px;
}
.footer_nav_container>ul>span{
    margin: 0 4px;
}
/* footer 영역 */
.footer{
    width:100%;
    height:130px;
    background-color:#D4DFE6;
    padding-bottom : 50px;
}
.footer_container{
    width: 100%;
    height: 100%;
    margin: auto;
}
.footer_left>img {
    width: 150%;
    height: 130px;
    margin-left: -20px;
    margin-top: -12px;
}
.footer_left{
    float :left;
    width: 170px;
    margin-left: 20px;
    margin-top : 30px;
    
}
.footer_right{
    float :left;
    width: 680px;
    margin-left: 70px;
    margin-top : 30px;
}
 
 
 
 
/* float 속성 해제 */
.clearfix{
    clear: both;
}

</style>
<body>
 
    <div class="wrapper">
        <div class="wrap">
            <!-- gnv_area -->    
            <div class="top_gnb_area">
                <ul class="list">    
                    <li><a href="/main">메인 페이지</a></li>
                    <li><a href="/member/logout.do">로그아웃</a></li>
                    <li>고객센터</li>            
                </ul>
            </div>
            <!-- top_subject_area -->
            <div class="admin_top_wrap">
                <span>관리자 페이지</span>
                
            </div>
            <!-- contents-area -->
            <div class="admin_wrap">
                <!-- 네비영역 -->
                <div class="admin_navi_wrap">
                  <ul>
                      <li >
                          <a class="admin_list_01" href="/admin/goodsEnroll">상품 등록</a>
                      </li>
                      <li>
                          <a class="admin_list_02" href="/admin/goodsManage">상품 관리</a>
                      </li>
                      <lI>
                          <a class="admin_list_03" href="/admin/authorEnroll">작가 등록</a>                            
                      </lI>
                      <lI>
                          <a class="admin_list_04" href="/admin/authorManage">작가 관리</a>                            
                      </lI>
                      <lI>
                          <a class="admin_list_05">회원 관리</a>                            
                      </lI>                                                                                             
                  </ul>
                </div>
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>작가 관리</span></div>
                </div>
                <div class="clearfix"></div>
            </div>
        
        <!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
                <ul>
                    <li>회사소개</li>
                    <span class="line">|</span>
                    <li>이용약관</li>
                    <span class="line">|</span>
                    <li>고객센터</li>
                    <span class="line">|</span>
                    <li>광고문의</li>
                    <span class="line">|</span>
                    <li>채용정보</li>
                    <span class="line">|</span>
                </ul>
            </div>
        </div> <!-- class="footer_nav" -->
        
        <div class="footer">
            <div class="footer_container">
                
                <div class="footer_left">
                    <img src="../resources/img/Logo.png">
                </div>
                <div class="footer_right">
                    (주) VamBook    대표이사 : OOO
                    <br>
                    사업자등록번호 : ooo-oo-ooooo
                    <br>
                    대표전화 : oooo-oooo(발신자 부담전화)
                    <br>
                    <br>
                    COPYRIGHT(C) <strong>kimvampa.tistory.com</strong>    ALL RIGHTS RESERVED.
                </div>
                <div class="clearfix"></div>
            </div>
        </div> <!-- class="footer" -->        
        
    </div>    <!-- class="wrap" -->
</div>    <!-- class="wrapper" -->
<script type="text/javascript">
/* 페이지가 로드될 떼 반드시 실행이 되는 익명 함수를 추가했고,
   함수 구현부에 서버에서 전송되는 데이터를 체크 후, 존재할 시에 작가 등록 성공 메시지를 알리는 경고창을 띄우는 코드를 추가함. */
	$(document).ready(function() {
		
		let result = '<c:out value="${enroll_result}"/>';
		// $(enroll_result)는 사용자가 작성한 값을 그대로 전송되기 떄문에 XSS 공격과 같이 스크립트 코드를 주입시키는 웹사이트에 취약할 수 있음.
		// 이를 방지하기 위해서 유효성 검사를 통해 스크립트 코드를 작성하지 못 하도록 JSTL <c:out>을 사용해서 HTML 문자를 탈락시키는 기능을 사용함.
		
		checkResult(result);
		
		function checkResult(result){
			
			if(result === ''){
				return;
			}
			
			alert("작가'${enroll_result}'을 등록하였습니다.");
			
		}
		
	});
</script>
 
</body>
</html>