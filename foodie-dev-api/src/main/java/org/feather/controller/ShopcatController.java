package org.feather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.feather.pojo.vo.ShopcartVO;
import org.feather.utils.FeatherJSONResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-05-14 09:32
 **/
@Api(value = "购物车🛒接口controller",tags = {"购物车🛒相关api"})
@RequestMapping("shopcart")
@RestController
public class ShopcatController {
    @ApiOperation(value = "添加商品到购物车",notes = "添加商品到购物车",httpMethod = "GET")
    @PostMapping("/add")
    public FeatherJSONResult add(@RequestParam String userId,
                                 @RequestBody ShopcartVO shopcartVO,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        if (StringUtils.isBlank(userId)){
            return  FeatherJSONResult.errorMsg(null);
        }
        //TODO 前端用户在登陆的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return  FeatherJSONResult.ok();

    }

    @ApiOperation(value = "从购物车中删除商品",notes = "从购物车中删除商品",httpMethod = "POST")
    @PostMapping("/del")
    public FeatherJSONResult del(@RequestParam String userId,
                                 @RequestBody String  itemSpecId,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        if (StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)){
            return  FeatherJSONResult.errorMsg(null);
        }
        //TODO

        return  FeatherJSONResult.ok();

    }


}
