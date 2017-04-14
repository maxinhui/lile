package top.builbu.business.wechat.service;

import top.builbu.business.wechat.dto.MemberDrawDTO;
import top.builbu.business.wechat.entity.MemberDraw;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface MemberDrawService{
     
     PageDTO<MemberDrawDTO> selectByList(MemberDrawDTO dto, Pagination page);
     

     
     ResultDO<?> save(MemberDrawDTO dto);

     ResultDO<?> update(MemberDrawDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);

	ResultDO<MemberDraw> selectById(String id);
}
