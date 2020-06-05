package com.springcloud.netty.Controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-26 21:45
 */
@Api(value = "文件",tags = "文件模块")
@RequestMapping("/file")
@RestController
public class FileController {


    @ApiOperation(value = "测试")
    @GetMapping("/")
    public Result<String> list(){
        return Response.ok("111");
    }


}
