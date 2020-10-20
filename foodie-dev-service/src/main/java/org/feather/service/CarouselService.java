package org.feather.service;

import org.feather.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    /**
     * 查询轮播图列表
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
