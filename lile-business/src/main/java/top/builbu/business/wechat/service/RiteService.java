package top.builbu.business.wechat.service;

import top.builbu.business.wechat.dto.RiteDTO;
import top.builbu.business.wechat.entity.Rite;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface RiteService{
     
     PageDTO<RiteDTO> selectByList(RiteDTO dto, Pagination page);
     
     ResultDO<Rite> selectById(Long id);
     
     ResultDO<?> save(RiteDTO dto);

     ResultDO<?> update(RiteDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);

	ResultDO<RiteDTO> selectByCode(String openId, String riteCode);

	ResultDO<?> addCount(Long riteId);

	ResultDO<?> addCountByCode(String riteCode);

	ResultDO<?> addCountByDraw(String riteCode);
}
