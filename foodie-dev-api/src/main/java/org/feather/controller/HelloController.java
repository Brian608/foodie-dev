package org.feather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-04-22 15:41
 **/
@ApiIgnore
@RestController
public class HelloController {
    final static Logger log= LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public  String hello(){
        log.debug("debug:hello");
        log.info("info:hello");
        log.warn("warn:hello");
        log.error("error:hello");
        return  "hello";
    }
}
