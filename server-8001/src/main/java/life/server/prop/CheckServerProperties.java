package life.server.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("register")
@Data
public class CheckServerProperties {

    /**
     * 服务器检查服务周期
     */
    private long cycle = 5 * 1000;
    /**
     * 最大的间隔时间
     */
    private long maxTime = 20 * 1000;

}
