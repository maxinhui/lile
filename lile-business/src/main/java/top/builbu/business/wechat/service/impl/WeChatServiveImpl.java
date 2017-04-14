package top.builbu.business.wechat.service.impl;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import top.builbu.business.wechat.dto.MemberAcDTO;
import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.dto.MemberDrawDTO;
import top.builbu.business.wechat.dto.RiteDTO;
import top.builbu.business.wechat.entity.Member;
import top.builbu.business.wechat.entity.MemberDraw;
import top.builbu.business.wechat.service.MemberAcService;
import top.builbu.business.wechat.service.MemberDrawService;
import top.builbu.business.wechat.service.MemberService;
import top.builbu.business.wechat.service.RiteService;
import top.builbu.business.wechat.service.WeChatService;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.ResultDO;
import top.builbu.core.wechat.entity.WXEventResult;

@Slf4j
@Service
public class WeChatServiveImpl implements WeChatService {
           
	@Autowired
	private RiteService riteService;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberAcService memberAcService;
	@Autowired
	private MemberDrawService memberDrawService;
	
	/**
	 * 微信推送事件处理
	 * @throws Exception 
	 */
	@Override
	public String EventManage(Map<String,Object> dataMap) throws Exception {
		WXEventResult eventResult = null;
		if(null != dataMap){
			eventResult = JSONObject.parseObject(JSONObject.toJSONString(dataMap), WXEventResult.class);
			
			log.info(JSONObject.toJSONString(eventResult)+"---");
			
			
		}
		return "";
	}
	
	
	/**
	 * 扫描二维码
	 * @return
	 */
	@Override
    public ResultDO<MemberDTO> scan(String openId,String riteCode){
		ResultDO<MemberDTO> result =null;
		ResultDO<?> resultDo = null;	
		
			ResultDO<MemberDTO> resultMember = memberService.selectByOpenId(openId);
			MemberDTO memberDTO = resultMember.getModule(); 
			ResultDO<RiteDTO> resultRite = riteService.selectByCode(openId, riteCode);
			if(resultMember.isSuccess() && null != memberDTO){				
				if(resultRite.isSuccess()){
					if(null != resultRite.getModule().getOpenId()){//重复扫同区域二维码
						resultDo = new ResultDO<>(BaseResultCode.COMMON_SCANCODE_FAIL,Boolean.TRUE);
					}else{
						MemberAcDTO dto = new MemberAcDTO();
						Long riteId = resultRite.getModule().getRiteId();
						dto.setOpenId(openId);
						dto.setRiteId(riteId);
						dto.setSancTime(new Date());
						resultDo = memberAcService.save(dto);
						if(resultDo.isSuccess()){
							resultDo = riteService.addCount(riteId);//流量自加
						}
					}
				}else{
					resultDo = resultRite;
				}				
			}else{ 
				memberDTO = new MemberDTO();
				memberDTO.setOpenId(openId);
				resultDo = memberService.save(memberDTO);
				if(resultDo.isSuccess()){
					MemberAcDTO dto = new MemberAcDTO();
					Long riteId = resultRite.getModule().getRiteId();
					dto.setOpenId(openId);
					dto.setRiteId(riteId);
					dto.setSancTime(new Date());
					resultDo = memberAcService.save(dto);
					if(resultDo.isSuccess()){
						resultDo = riteService.addCount(riteId);//流量自加
					}
				}
			}
			
			if(resultDo.isSuccess()){
				if("Y".equals(memberDTO.getIsLi()) && (null == memberDTO.getIsAll() || "".equals(memberDTO.getIsAll()))){//获得字，未领取奖品
					
					memberDTO.setForLoad("/wechat/map");//已获得字，未领奖
				}else if("Y".equals(memberDTO.getIsAll())){
					
					memberDTO.setForLoad("/wechat/finish");//已领奖
				}else{
					
					memberDTO.setForLoad("/wechat/index");//正常操作
				}
				memberDTO.setCode(riteCode);
				memberDTO.setAddress(resultRite.getModule().getAddress());
				result = new ResultDO<>(memberDTO);
			}

		//log.info(JSONObject.toJSONString(result));
	    	return result;
    }
	
	
	
	/**
	 * 兑换
	 * @param openId
	 * @param riteCode
	 * @return
	 */
	@Override
	public ResultDO<?> exchange(String openId,String changeCode,String riteCode){
		ResultDO<?> result = null;
		ResultDO<MemberDraw> resultDraw = memberDrawService.selectById(openId);
		if(!resultDraw.isSuccess()){
			Member member = new Member();
			member.setIsAll("Y");
			member.setOpenId(openId);
			result = memberService.updateByOpenId(member);
			if(result.isSuccess()){
				result = riteService.addCountByCode(changeCode);
				if(result.isSuccess()){
					MemberDrawDTO drawDTO = new MemberDrawDTO();
					drawDTO.setOpenId(openId);
					drawDTO.setRiteCode(riteCode);
					drawDTO.setCreateTime(new Date());
					result = memberDrawService.save(drawDTO);
					if(result.isSuccess()){
						result = riteService.addCountByDraw(riteCode);
					}
					
				}
			}
		}else{
			result = new ResultDO<>(BaseResultCode.COMMON_NO,Boolean.FALSE);
		}
		
		return result;
	}
	
	
	
}
