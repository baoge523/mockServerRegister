package life.server.config;

import life.server.RegisterCenter;
import life.server.job.CheckServer;
import life.server.prop.CheckServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfigBean {

    @Autowired
    private CheckServerProperties properties;

    @Bean(initMethod = "start")
    public CheckServer checkServer(RegisterCenter center){
        CheckServer checkServer = new CheckServer(properties.getCycle(),properties.getMaxTime(),center);
        return checkServer;
    }
}
