package org.feather.pojo.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-04-23 17:32
 **/
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装在此entry中")
@Data
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "杜雪松",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123456",required = true)
    private String confirmPassword;


}
