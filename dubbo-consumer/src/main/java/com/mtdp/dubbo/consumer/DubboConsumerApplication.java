package com.mtdp.dubbo.consumer;

import com.mtdp.dubbo.api.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangte
 */
public class DubboConsumerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

        EchoService echoService = (EchoService) context.getBean("echoService");
        System.out.println(echoService.echo());

        context.close();
    }

}
