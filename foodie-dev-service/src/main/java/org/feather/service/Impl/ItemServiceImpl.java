package org.feather.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.feather.enums.CommentLevel;
import org.feather.mapper.*;
import org.feather.pojo.*;
import org.feather.pojo.vo.CommentLevelCountsVO;
import org.feather.pojo.vo.ItemCommentVO;
import org.feather.pojo.vo.SearchItemsVO;
import org.feather.pojo.vo.ShopcartVO;
import org.feather.service.ItemService;
import org.feather.utils.DesensitizationUtil;
import org.feather.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-05-07 14:51
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example=new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example=new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example=new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(example);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts=getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts=getCommentCounts(itemId,CommentLevel.NORMAL.type);
        Integer badCounts=getCommentCounts(itemId,CommentLevel.BAD.type);
        Integer totalCounts=goodCounts+normalCounts+badCounts;
        CommentLevelCountsVO commentLevelCountsVO=new CommentLevelCountsVO();
        commentLevelCountsVO.setTotalCounts(totalCounts);
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setBadCounts(badCounts);
        return commentLevelCountsVO;
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId,Integer level){
        ItemsComments condition=new ItemsComments();
        condition.setItemId(itemId);
        if (level!=null){
            condition.setCommentLevel(level);
        }
        return  itemsCommentsMapper.selectCount(condition);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPageComments(String itemId, Integer level,Integer page,Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);
        //mybatis-pageHelper分页
        PageHelper.startPage(page,pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(map);
        for (ItemCommentVO vo:list){
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        return setterPagedGrid(list,page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searhItems(String keywords, String sort, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.searchItems(map);

        return setterPagedGrid(list, page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searhItems(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("catId",catId);
        map.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list=itemsMapperCustom.searchItemsByThirdCat(map);
        return setterPagedGrid(list,page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String ids[]=specIds.split(",");
        List<String> specIdsList=new ArrayList<>();
        Collections.addAll(specIdsList,ids);
       return itemsMapperCustom.queryItemsBySpecIds(specIdsList);
    }

    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        PageInfo<?> pageList=new PageInfo<>(list);
        PagedGridResult grid=new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }


}
