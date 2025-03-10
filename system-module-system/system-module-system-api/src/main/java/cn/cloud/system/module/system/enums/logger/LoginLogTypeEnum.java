package cn.cloud.system.module.system.enums.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录日志的类型枚举
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {

    LOGIN_USERNAME(100), // 使用账号登录

    LOGOUT_SELF(200),  // 自己主动登出
    ;

    /**
     * 日志类型
     */
    private final Integer type;

}
