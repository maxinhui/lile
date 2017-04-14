package top.builbu.business.wechat.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.wechat.dto.MemberAcDTO;
import top.builbu.business.wechat.entity.MemberAc;

@Repository
public interface MemberAcMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<MemberAcDTO> selectByList(@Param("dto")MemberAcDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    MemberAc selectByPrimaryKey(Long id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long id); 

      
    /**
     *插入
     *
     */
    int insert(MemberAc record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(MemberAc record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")MemberAcDTO dto);

}
