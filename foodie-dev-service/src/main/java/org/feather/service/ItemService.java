package org.feather.service;

import org.feather.pojo.Items;
import org.feather.pojo.ItemsImg;
import org.feather.pojo.ItemsParam;
import org.feather.pojo.ItemsSpec;
import org.feather.pojo.vo.CommentLevelCountsVO;
import org.feather.pojo.vo.ShopcartVO;
import org.feather.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    public Items queryItemById(String itemId);


    public List<ItemsImg> queryItemImgList(String itemId);

    public  List<ItemsSpec> queryItemSpecList(String itemId);

    public ItemsParam queryItemParam(String itemId);

    public CommentLevelCountsVO queryCommentCounts(String itemId);

    public PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

    public PagedGridResult searhItems(String keywords, String sort,
                                      Integer page, Integer pageSize);
    public PagedGridResult searhItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

    public List<ShopcartVO> queryItemsBySpecIds(String specIds);
}
