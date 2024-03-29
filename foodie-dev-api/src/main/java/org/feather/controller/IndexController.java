package org.feather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.feather.enums.YesOrNo;
import org.feather.pojo.Carousel;
import org.feather.pojo.Category;
import org.feather.pojo.vo.CategoryVO;
import org.feather.pojo.vo.NewItemsVO;
import org.feather.service.CarouselService;
import org.feather.service.CategoryService;
import org.feather.utils.FeatherJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: foodie-dev
 * @description: 首页
 * @author: 杜雪松(feather)
 * @create: 2020-05-06 10:18
 **/
@Api(value = "首页",tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表",notes = "获取首页轮播图列表",httpMethod = "GET")
    @GetMapping("/carousel")
    public FeatherJSONResult carousel(){
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return FeatherJSONResult.ok(list);
    }
    /**
     * 首页分类展示需求
     * 1。第一次刷新主页查询大分类，渲染展示到首页
     * 2。如果鼠标上移到大分类，则加载子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public FeatherJSONResult cats(){
        List<Category> list = categoryService.queryAllRootLevelCat();
        return FeatherJSONResult.ok(list);
    }
    @ApiOperation(value = "获取商品子分类",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public FeatherJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable Integer rootCatId){
        if (rootCatId==null){
            return  FeatherJSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO > list=categoryService.getSubCatList(rootCatId);
        return FeatherJSONResult.ok(list);
    }


    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public FeatherJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return FeatherJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return FeatherJSONResult.ok(list);
    }


}
