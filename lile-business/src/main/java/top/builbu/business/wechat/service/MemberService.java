package top.builbu.business.wechat.service;

import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.entity.Member;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface MemberService{
     
     PageDTO<MemberDTO> selectByList(MemberDTO dto, Pagination page);
     
     ResultDO<Member> selectById(Long id);
     
     ResultDO<?> save(MemberDTO dto);

     ResultDO<?> update(MemberDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);

	ResultDO<MemberDTO> selectByOpenId(String openId);

	ResultDO<?> updateByOpenId(Member member);
}
