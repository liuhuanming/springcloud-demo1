package com.lmn.rabbit.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RemindInfo implements Serializable {
    private static final long serialVersionUID = 1209375276984075571L;
    private String messageId;
    private String createTime;
    // 消息主体
    private String message;
    private String senderName;
    private String toName;
}
