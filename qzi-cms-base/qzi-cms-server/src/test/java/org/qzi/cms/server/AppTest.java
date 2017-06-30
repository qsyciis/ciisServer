package org.qzi.cms.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.vo.AdminVo;

public class AppTest {
	
	@Test
	public void test(){
		String salt = UUID.randomUUID().toString().replaceAll("-","");
		String loginPw = CryptUtils.hmacSHA1Encrypt("1q2w3e", salt);
		System.out.println(salt);
		System.out.println(loginPw+"="+loginPw.length());
//		System.out.println(String.format("%06d",11));
	}
	
	@Test
	public void testJson(){
		AdminVo avo = new AdminVo();
		avo.setResidentId("sadfasdfa");
		List<String> lis = new ArrayList<>();
		lis.add("aaa");
		lis.add("bbb");
		lis.add("ccc");
		lis.add("eee");
		lis.add("ddd");
		lis.add("fff");
		avo.setUserIds(lis);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			System.out.println(mapper.writeValueAsString(avo));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
