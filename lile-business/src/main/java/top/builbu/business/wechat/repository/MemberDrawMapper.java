package top.builbu.business.wechat.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.wechat.dto.MemberDrawDTO;
import top.builbu.business.wechat.entity.MemberDraw;

@Repository
public interface MemberDrawMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<MemberDrawDTO> selectByList(@Param("dto")MemberDrawDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    MemberDraw selectByPrimaryKey(@Param("openId")String openId); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long id); 

      
    /**
     *插入
     *
     */
    int insert(MemberDraw record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(MemberDraw record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")MemberDrawDTO dto);

}
