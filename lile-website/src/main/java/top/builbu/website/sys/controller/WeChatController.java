package top.builbu.website.sys.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.entity.Member;
import top.builbu.business.wechat.entity.WxConfig;
import top.builbu.business.wechat.service.MemberService;
import top.builbu.business.wechat.service.WeChatService;
import top.builbu.business.wechat.service.WxConfigService;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.ResultDO;
import top.builbu.core.util.entity.Lile;
import top.builbu.core.wechat.entity.WXOpenIdResult;
import top.builbu.core.wechat.utils.Aouth2Utils;
import top.builbu.core.wechat.utils.HttpXmlUtils;
import top.builbu.core.wechat.utils.WXSignUtils;

@Log4j
@Controller
@RequestMapping("/weChat")
public class WeChatController {

	@Autowired
	private WeChatService weChatService;
	
	@Autowired
	private WxConfigService wxConfigService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 微信公众号认证
	 * @param response
	 * @param timestamp
	 * @param signature
	 * @param nonce
	 * @param echostr
	 */
	@ResponseBody
	@RequestMapping(value="/authC")
	public String authC(HttpServletRequest request,String timestamp,String signature,String nonce,String echostr,String pram_token){
        log.info(pram_token);
        try {
        	 if("GET".equals(request.getMethod())){
             	log.info("微信接入认证");
     			if(WXSignUtils.checkSignature("ec10c3b0dfd93ffb700abb6a9f41ebbb", timestamp, nonce, signature)){
     				return echostr;
     			}else{
     				return "";
     			}
             }else{
             	Map<String,Object> dataMap = HttpXmlUtils.requestResult(request);
             	return weChatService.EventManage(dataMap);
             }
		} catch (Exception e) {
			return "";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/token")
	public ResultDO<WxConfig> selectAccessToken(){
	    ResultDO<WxConfig> result = null;
		try {
			result = wxConfigService.getToken("lile");
		} catch (Exception e) {
			log.error(e.getStackTrace());
			result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		}
		return result;
	}
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd6d1e3ffbb1071e7&redirect_uri=http://lile.vbooline.com/lile-website/weChat/scan&response_type=code&scope=snsapi_userinfo&state=Y008#wechat_redirect
	//wx35eed173d89a147d  wxd6d1e3ffbb1071e7
	/**
	 * 扫描二维码
	 * @return
	 */
	@RequestMapping("/scan")
	public String scan(HttpSession session,HttpServletRequest request,String code,String state){
		//log.info("code:"+code+"--isTag:"+state);
		ResultDO<MemberDTO> result = null;
		ResultDO<WxConfig> config = null;
		WXOpenIdResult openIdResult = null;
		if(null != code && !"".equals(code)){
			try {
				config = wxConfigService.getToken("lile");
				if(config.isSuccess()){
					WxConfig wxConfig = config.getModule();
					openIdResult = Aouth2Utils.getOpenId(wxConfig.getCorpId(), wxConfig.getCorpSecret(), code);
					result = weChatService.scan(openIdResult.getOpenid(),state);
				}else{
					result = new ResultDO<>(BaseResultCode.COMMON_SALE_RETURN,Boolean.FALSE);
				}			
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			}	
		}else{
			result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
		}
		
		if(result.isSuccess()){
			session.setAttribute(Lile.USERKEY, result.getModule());
			request.setAttribute("module", result.getModule());
			return result.getModule().getForLoad();
		}else{
			return "/wechat/error";
		}
	}
	
	
	/**
	 * 点击取字
	 */
	@RequestMapping("/check")
	public String check(HttpSession session,HttpServletRequest request){
		ResultDO<?> result = null;
		MemberDTO memberDTO =  (MemberDTO) session.getAttribute(Lile.USERKEY);
		if(null != memberDTO && !"".equals(memberDTO.getOpenId())){
			try {
				Member member = new Member();
				member.setIsLi("Y");
				member.setIsLe("Y");
				member.setOpenId(memberDTO.getOpenId());
				result = memberService.updateByOpenId(member );
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			}
		}else{
			result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
		}
		
		if(result.isSuccess()){
			//log.info(JSONObject.toJSON(memberDTO));
			request.setAttribute("module", memberDTO);
			return "/wechat/map";
		}else{
			return "/wechat/error";
		}
	}
	
	
	/**
	 * 兑换码
	 * @param session
	 * @param changeCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exchange")
	public ResultDO<?> exchange(HttpSession session,String changeCode,String code){
		ResultDO<?> result = null;
		if(null != changeCode && !"".equals(changeCode) && "5188".equals(changeCode)){
			MemberDTO memberDTO =  (MemberDTO) session.getAttribute(Lile.USERKEY);
			if(null != memberDTO && !"".equals(memberDTO.getOpenId())){
				try {
					result = weChatService.exchange(memberDTO.getOpenId(), "0000",code);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
					result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
				}
			}else{
				result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
			}
		}else{
			result = new ResultDO<>(BaseResultCode.COMMON_NO_CHECK,Boolean.FALSE);
		}
		return result;
	}
	
	
	/**
	 * JsTicket 
	 * @param url
	 * @return
	 */
	private static String surl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd6d1e3ffbb1071e7&redirect_uri=http://lile.vbooline.com/lile-website/weChat/scan&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	@ResponseBody
	@RequestMapping("/getTicket")
	public JSONObject getTicket(String url,HttpSession session){
		JSONObject json=new JSONObject();
		ResultDO<Map<String, Object>> result=new ResultDO<>(BaseResultCode.COMMON_DB_ERRORS,Boolean.FALSE);
		try {
			result=wxConfigService.getTiekct(url,"lile");
			json.put("data", result);
			MemberDTO memberDTO =  (MemberDTO) session.getAttribute(Lile.USERKEY);
			if(null != memberDTO){
				json.put("shareUrl", surl.replaceAll("STATE", memberDTO.getCode()));
			}
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			return json;
		}
		return json;
	}
}
