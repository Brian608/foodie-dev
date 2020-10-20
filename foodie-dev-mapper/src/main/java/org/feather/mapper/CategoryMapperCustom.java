package org.feather.mapper;

import org.apache.ibatis.annotations.Param;
import org.feather.pojo.vo.CategoryVO;
import org.feather.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    public List<CategoryVO> getSubCatList(Integer rootCatId);


    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}