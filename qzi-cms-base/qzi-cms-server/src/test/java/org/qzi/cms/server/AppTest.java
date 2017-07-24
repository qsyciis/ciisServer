package org.qzi.cms.server;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.DateUtils;
import com.qzi.cms.common.util.HttpUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.common.vo.ClientVo;

public class AppTest {
	
	@Test
	public void test(){
//		String salt = UUID.randomUUID().toString().replaceAll("-","");
//		String loginPw = CryptUtils.hmacSHA1Encrypt("1q2w3e", salt);
//		System.out.println(salt);
//		System.out.println(loginPw+"="+loginPw.length());
		System.out.println(String.format("%02d单元",2));
	}
	
	@Test
	public void testJson(){
		AdminVo avo = new AdminVo();
		avo.setCommunityId("====");
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
	
	@Test
	public void testClient() throws Exception{
		String strAccountId = "4e72049b63f9a19fe79b1585a28caf87";
		String strAutoTaken = "7490ae2b21235f58d850fbab00a9d1aa";
		String strAppId = "997517e1229143c5972ce12c62d4bc91";
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String url = "https://api.ucpaas.com";
		url = url.concat("/2015-06-30")
		.concat("/Accounts/")
		.concat(strAccountId)
		.concat("/Clients")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(strAccountId, strAutoTaken, timestamp));
		
		ClientVo clientVo = new ClientVo();
		clientVo.setAppId(strAppId);
		clientVo.setUserId("2000");
		String param = "{\"client\":"+ToolUtils.toJson(clientVo)+"}";
		String auth= CryptUtils.base64Encoder(strAccountId+":"+timestamp);
		String res = HttpUtils.sendPostJson(url,param,auth);
		System.out.println(res);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
