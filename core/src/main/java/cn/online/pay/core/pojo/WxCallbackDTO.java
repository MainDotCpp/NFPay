package cn.online.pay.core.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WxCallbackDTO {
    @Data
    public static class Resource {
        private String originalType;
        private String algorithm;
        private String ciphertext;
        private String associatedData;
        private String nonce;
    }

    private String id;
    private LocalDateTime createTime;
    private String resourceType;
    private String eventType;
    private String summary;
    private Resource resource;
}
