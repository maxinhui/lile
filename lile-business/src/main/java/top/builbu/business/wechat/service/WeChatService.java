package top.builbu.business.wechat.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.common.dto.ResultDO;

public interface WeChatService {

	/**
	 * 微信推送事件处理
	 * @param dataMap
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 */
	String EventManage(Map<String, Object> dataMap)
			throws Exception;

	
	/**
	 * 扫描二维码
	 * @return
	 */
	ResultDO<MemberDTO> scan(String openId, String riteCode);


	/**
	 * 兑换码
	 * @param openId
	 * @param riteCode
	 * @return
	 */
	ResultDO<?> exchange(String openId, String changeCode, String riteCode);

}
