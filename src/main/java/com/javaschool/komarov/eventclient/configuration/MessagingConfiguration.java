package com.javaschool.komarov.eventclient.configuration;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:jms.properties")
public class MessagingConfiguration {
    private final Environment environment;

    @Autowired
    public MessagingConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(environment.getProperty("activemq.broker-url"));
        connectionFactory.setUserName(environment.getProperty("activemq.user"));
        connectionFactory.setPassword(environment.getProperty("activemq.password"));
        System.out.println(connectionFactory.hashCode());
        return connectionFactory;
    }
}
