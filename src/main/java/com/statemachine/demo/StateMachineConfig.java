package com.statemachine.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @author
 * @packageName com.statemachine.demo
 * @date 2020/3/5 20:11
 * @Description <a href="goodmanalibaba@foxmail.com"></a>
 * @Versin 1.0
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEnvents> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEnvents> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listenerStatus());
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEnvents> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.CREATED)
                .end(OrderStatus.FINISHED)
                .states(EnumSet.allOf(OrderStatus.class));
    }



    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEnvents> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatus.CREATED)
                .target(OrderStatus.WAITING_FOR_RECEIVE)
                .event(OrderEnvents.PAY)
                .and()
                .withExternal()
                .source(OrderStatus.WAITING_FOR_RECEIVE)
                .target(OrderStatus.FINISHED)
                .event(OrderEnvents.RECEIVE);
    }

    @Bean
    public StateMachineListener<OrderStatus, OrderEnvents> listenerStatus() {
        return new StateMachineListenerAdapter<OrderStatus, OrderEnvents>() {
            @Override
            public void stateChanged(State<OrderStatus, OrderEnvents> from, State<OrderStatus, OrderEnvents> to) {
                logger.info("State change to " + to.getId().getValue());
            }
        };
    }


}
