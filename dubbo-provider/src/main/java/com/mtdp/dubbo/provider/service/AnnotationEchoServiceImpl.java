package com.mtdp.dubbo.provider.service;

import com.mtdp.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author <a href="mailto:wangte@meitaun.com">Te</a>
 * @date Created At 2020/8/12
 */
@Service
public class AnnotationEchoServiceImpl implements EchoService {

    @Override
    public String echo() {
        return "dubbo annotation";
    }
}
