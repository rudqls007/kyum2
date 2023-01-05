package com.vam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.AuthorMapper;
import com.vam.model.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	AuthorMapper authorMapper; // AuthorMapper 주입
	

	/* 작가 등록 메서드 호출 */
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		
		authorMapper.authorEnroll(author);
		
	}

}
