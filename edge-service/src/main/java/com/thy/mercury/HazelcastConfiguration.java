package com.thy.mercury;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.spring.context.SpringManagedContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

//@Configuration
public class HazelcastConfiguration {

//    @Bean
/*
    public Config config() {
        Config config = new Config();
        config.setInstanceName("mrc");
        config.setManagedContext(managedContext());

        GroupConfig groupConfig = new GroupConfig("mrc", "mrc");
        config.setGroupConfig(groupConfig);

        config.getNetworkConfig().setPortAutoIncrement(true).setPort(5701).setPortCount(100);

        ArrayList<Integer> outboundPorts = new ArrayList<>();
        outboundPorts.add(0);
        config.getNetworkConfig().setOutboundPorts(outboundPorts);

        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);

        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true).setMulticastGroup("224.2.2.3")
                .setMulticastPort(5701);

        return config; // Set up any non-default config here
    }
*/

/*
    @Bean
    public SpringManagedContext managedContext() {
        return new SpringManagedContext();
    }
*/


}