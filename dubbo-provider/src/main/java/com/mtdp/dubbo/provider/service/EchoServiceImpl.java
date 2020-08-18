package com.mtdp.dubbo.provider.service;

import com.mtdp.dubbo.api.EchoService;

/**
 * @author <a href="mailto:wangte@meitaun.com">Te</a>
 * @date Created At 2020/8/12
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo() {
        return "hello world!";
    }
}
