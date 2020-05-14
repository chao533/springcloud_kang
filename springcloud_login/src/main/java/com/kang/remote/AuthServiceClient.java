package com.kang.remote;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.kang.common.constant.AppNameConstant;
import com.kang.remote.hystrix.AuthServiceHystrix;

@FeignClient(value = AppNameConstant.LOGIN,fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

    @PostMapping(value = "/oauth/token")
    Map<String,Object> getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password);


    @PostMapping(value = "/oauth/token")
    Map<String,Object> refreshToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type, @RequestParam(value = "refresh_token") String refreshToken);

}
