package life.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 心跳响应
 */
@AllArgsConstructor
@Data
public class HeartbeatResponse {
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 状态码
     */
    private int status;
}
