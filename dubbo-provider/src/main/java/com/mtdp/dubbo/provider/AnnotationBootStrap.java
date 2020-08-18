package com.mtdp.dubbo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author <a href="mailto:wangte@meitaun.com">Te</a>
 * @date Created At 2020/8/12
 */
public class AnnotationBootStrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();

        System.in.read();
    }


    /**
     * ProviderConfig：服务提供方配置
     * ApplicationConfig：应用配置
     * RegistryConfig：注册中心配置
     * ProtocolConfig：协议配置
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "com.mtdp.dubbo.provider.service")
    static class ProviderConfiguration {

        @Bean
        public ProviderConfig providerConfig() {
            ProviderConfig providerConfig = new ProviderConfig();
            providerConfig.setTimeout(1000);
            return providerConfig;
        }

        @Bean
        public ApplicationConfig applicationConfig() {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("dubbo-provider");
            return applicationConfig;
        }

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setProtocol("zookeeper");
            registryConfig.setAddress("127.0.0.1");
            registryConfig.setPort(2181);
            return registryConfig;
        }

        @Bean
        public ProtocolConfig protocolConfig() {
            ProtocolConfig protocolConfig = new ProtocolConfig();
            protocolConfig.setName("dubbo");
            protocolConfig.setPort(20880);
            return protocolConfig;
        }

        /**
         * 这个Bean必须进行配置，否则会出现ZK链接不上的异常
         * https://github.com/apache/dubbo/issues/5276
         *
         * org.apache.dubbo.config.bootstrap.DubboBootstrap#useRegistryAsConfigCenterIfNecessary()
         * 这里没有透传registry的timeout，导致在RegistryConfig中配置timeout不起作用
         */
        @Bean
        public ConfigCenterConfig configCenterConfig() {
            ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
            configCenterConfig.setTimeout(60000L);
            return configCenterConfig;
        }
    }
}
