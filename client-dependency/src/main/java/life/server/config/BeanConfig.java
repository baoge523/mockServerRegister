package life.server.config;

import life.server.boot.Start;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init")
    public Start start(){
        return new Start();
    }

}
