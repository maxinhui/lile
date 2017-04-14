package top.builbu.core.util;


import top.builbu.core.util.entity.Suntory;
import top.builbu.core.util.entity.SuntoryTicketResult;
import top.builbu.core.util.entity.SuntoryUserResult;
import top.builbu.core.wechat.utils.HttpXmlUtils;

import com.alibaba.fastjson.JSONObject;

public class SuntoryUtil {

	public static SuntoryTicketResult ticket(){
		final String url="http://mpweixin.suntoryfoods-china.com/api/get_weixin_js_api_ticket.json?access_token=ACCESS_TOKEN";
		String s=HttpXmlUtils.httpRequest(url.replace("ACCESS_TOKEN", Suntory.ACCESS_TOKEN), "GET", null);
		SuntoryTicketResult result=JSONObject.parseObject(s, SuntoryTicketResult.class);
		return result;
	}
	
	public static SuntoryUserResult getUserInfo(String openid){
		final String url = "http://mpweixin.suntoryfoods-china.com/api/get_weixin_user.json?weixin_openid=OPEN_ID&access_token=ACCESS_TOKEN";
	    String s = HttpXmlUtils.httpRequest(url.replace("OPEN_ID", openid).replace("ACCESS_TOKEN", Suntory.ACCESS_TOKEN), "GET", null);
	    SuntoryUserResult result = JSONObject.parseObject(s,SuntoryUserResult.class);
	    return result;
	}

}
