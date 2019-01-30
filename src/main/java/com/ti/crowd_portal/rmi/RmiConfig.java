package com.ti.crowd_portal.rmi;

import com.ti.crowd_portal.service.AdvertiseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @author Ti
 * @date 2018/12/10
 */

@Configuration
public class RmiConfig {
    @Bean
    public RmiProxyFactoryBean deptService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/advertiseService");
        factoryBean.setServiceInterface(AdvertiseService.class);
        return factoryBean;
    }
}
