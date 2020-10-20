package org.feather.mapper;

import org.apache.ibatis.annotations.Param;
import org.feather.my.mapper.MyMapper;
import org.feather.pojo.Items;
import org.feather.pojo.vo.ItemCommentVO;
import org.feather.pojo.vo.SearchItemsVO;
import org.feather.pojo.vo.ShopcartVO;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom  {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap")Map<String,Object> map);
    public List<SearchItemsVO> searchItems(@Param("paramsMap")Map<String,Object> map);
    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap")Map<String,Object> map);
    public  List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List sprecIdsList);
}