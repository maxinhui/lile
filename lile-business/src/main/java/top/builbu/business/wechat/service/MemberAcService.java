package top.builbu.business.wechat.service;

import top.builbu.business.wechat.dto.MemberAcDTO;
import top.builbu.business.wechat.entity.MemberAc;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface MemberAcService{
     
     PageDTO<MemberAcDTO> selectByList(MemberAcDTO dto, Pagination page);
     
     ResultDO<MemberAc> selectById(Long id);
     
     ResultDO<?> save(MemberAcDTO dto);

     ResultDO<?> update(MemberAcDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);
}
