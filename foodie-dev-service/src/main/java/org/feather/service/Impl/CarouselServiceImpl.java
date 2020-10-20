package org.feather.service.Impl;

import org.feather.mapper.CarouselMapper;
import org.feather.pojo.Carousel;
import org.feather.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-05-06 10:13
 **/
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Carousel> queryAll(Integer isShow) {
        Example example=new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> result = carouselMapper.selectByExample(example);
        return result;
    }
}
