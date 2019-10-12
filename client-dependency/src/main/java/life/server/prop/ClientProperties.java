package life.server.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("life.client")
@Data
public class ClientProperties {

    @Value("server.port")
    private String port;

    private String hostName;

    private String appName;

    private String defaultZone;
    /**
     * 心跳发送周期时间
     */
    private long cycle = 5*1000;

}
