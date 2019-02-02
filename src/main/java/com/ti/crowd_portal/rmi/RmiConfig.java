package com.ti.crowd_portal.rmi;

import com.ti.crowd_portal.service.AdvertiseService;
import com.ti.crowd_portal.service.ItemDetailService;
import com.ti.crowd_portal.service.ItemService;
import com.ti.crowd_portal.service.UserService;
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
    public RmiProxyFactoryBean advertiseService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/advertiseService");
        factoryBean.setServiceInterface(AdvertiseService.class);
        return factoryBean;
    }

    @Bean
    public RmiProxyFactoryBean userService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/userService");
        factoryBean.setServiceInterface(UserService.class);
        return factoryBean;
    }

    @Bean
    public RmiProxyFactoryBean itemDetailService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/itemDetailService");
        factoryBean.setServiceInterface(ItemDetailService.class);
        return factoryBean;
    }

    @Bean
    public RmiProxyFactoryBean itemService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/itemService");
        factoryBean.setServiceInterface(ItemService.class);
        return factoryBean;
    }
}
