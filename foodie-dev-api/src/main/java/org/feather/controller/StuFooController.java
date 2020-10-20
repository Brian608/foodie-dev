package org.feather.controller;

import org.feather.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-04-23 11:39
 **/
@ApiIgnore
@RestController
public class StuFooController {
    @Autowired
    private StuService stuService;
    @GetMapping("/getStu")
    public Object getStu(int id){
        return  stuService.getStuInfo(id);
    }
}
