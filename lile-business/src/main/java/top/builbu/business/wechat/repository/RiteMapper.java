package top.builbu.business.wechat.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.wechat.dto.RiteDTO;
import top.builbu.business.wechat.entity.Rite;

@Repository
public interface RiteMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<RiteDTO> selectByList(@Param("dto")RiteDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    Rite selectByPrimaryKey(Long rite_id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long rite_id); 

      
    /**
     *插入
     *
     */
    int insert(Rite record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(Rite record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")RiteDTO dto);
    
    /**
     * 
     * @param openId
     * @param riteCode
     * @return
     */
    RiteDTO selectByCode(@Param("openId")String openId,@Param("riteCode")String riteCode);
    
    /**
     * 流量自加
     * @return
     */
    int addCount(@Param("riteId")Long riteId);

    
    int addCountByCode(@Param("riteCode")String riteCode);
    
    /**
     * 各点兑换数量自加
     * @param riteCode
     * @return
     */
    int addCountDraw(@Param("riteCode")String riteCode);
}
