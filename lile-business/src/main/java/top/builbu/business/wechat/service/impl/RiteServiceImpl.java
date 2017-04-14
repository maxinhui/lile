package top.builbu.business.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.builbu.common.util.page.Pagination;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.ResultDO;
import top.builbu.business.wechat.dto.RiteDTO;
import top.builbu.business.wechat.entity.Rite;
import top.builbu.business.wechat.service.RiteService;
import top.builbu.business.wechat.repository.RiteMapper;
@Slf4j
@Service
public class RiteServiceImpl implements RiteService{
 
    @Autowired
	private RiteMapper riteMapper;
	
	
	@Override
	public PageDTO<RiteDTO> selectByList(RiteDTO dto,Pagination page ){
	    PageDTO<RiteDTO> pageDo = new PageDTO<RiteDTO>();
	    List<RiteDTO> result = riteMapper.selectByList(dto,page.getCurrentResult(),page.getPageSize());
		if(null!=result&&result.size()>0){
		    pageDo.setList(result);
		   
		}
		 pageDo.setPageCurrent(page.getPageCurrent());
		 pageDo.setPageSize(page.getPageSize());
		 pageDo.setTotal(riteMapper.selectByCount(dto));
		return pageDo;
	}
	
    @Override
	public ResultDO<Rite> selectById(Long id){
	    ResultDO<Rite> resultDo = null;
	    if(null!=id&&!"".equals(id)){
	        Rite result = riteMapper.selectByPrimaryKey(id);
	        if(null!=result){
	            resultDo = new ResultDO<>(result);
	        }else{
	            resultDo = new ResultDO<>(BaseResultCode.COMMON_NO_DATA,Boolean.FALSE);
	        }
	    }else{
	        resultDo = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
	    }
	    return resultDo;
	}
	
    @Override
    public ResultDO<?> save(RiteDTO dto){
    	ResultDO<?> result = null;
    	Rite record = new Rite(); 
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = riteMapper.insert(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("riteList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> update(RiteDTO dto){
    	ResultDO<?> result = null;
    	Rite record = new Rite();
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = riteMapper.updateByPrimaryKey(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("riteList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteById(Long id){
    	ResultDO<?> result = null;
    	Integer rowId = riteMapper.deleteByPrimaryKey(id);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteByCheck(Long[] delids){
    	ResultDO<?> result = null;
    	Integer rowId = riteMapper.deleteByAll(delids);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    
    @Override
    public ResultDO<RiteDTO> selectByCode(String openId , String riteCode){
    	ResultDO<RiteDTO> resultDo = null;
    	if(null != openId && !"".equals(openId) && null != riteCode && !"".equals(riteCode)){
    	    RiteDTO result = riteMapper.selectByCode(openId, riteCode);
    	    if(null != result){
    	    	resultDo = new ResultDO<RiteDTO>(result);
    	    }else{
    	    	resultDo = new ResultDO<>(BaseResultCode.COMMON_NO_DATA,Boolean.FALSE);
    	    }
    	}else{
    		resultDo = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
    	}
    	return resultDo;
    }
    
    /**
     * 流量自加
     */
    @Override
    public ResultDO<?> addCount(Long riteId){
    	ResultDO<?> result = null;
    	if(null != riteId && !"".equals(riteId)){
    		Integer rowId = riteMapper.addCount(riteId);
    		if(null != rowId && rowId > 0){
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
        	}else{
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
        	}
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
    	}
    	return result;
    }
    
    /**
     * 流量自加
     */
    @Override
    public ResultDO<?> addCountByCode(String riteCode){
    	ResultDO<?> result = null;
    	if(null != riteCode && !"".equals(riteCode)){
    		Integer rowId = riteMapper.addCountByCode(riteCode);
    		if(null != rowId && rowId > 0){
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
        	}else{
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
        	}
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
    	}
    	return result;
    }
    
    /**
     * 流量自加
     */
    @Override
    public ResultDO<?> addCountByDraw(String riteCode){
    	ResultDO<?> result = null;
    	if(null != riteCode && !"".equals(riteCode)){
    		Integer rowId = riteMapper.addCountDraw(riteCode);
    		if(null != rowId && rowId > 0){
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
        	}else{
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
        	}
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
    	}
    	return result;
    }
}
