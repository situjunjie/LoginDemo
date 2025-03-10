package cn.cloud.system.framework.desensitize.core.handler;

import cn.cloud.system.framework.desensitize.core.DesensitizeTest;
import cn.cloud.system.framework.desensitize.core.annotation.Address;
import cn.cloud.system.framework.desensitize.core.base.handler.DesensitizationHandler;

/**
 * {@link Address} 的脱敏处理器
 *
 * 用于 {@link DesensitizeTest} 测试使用
 */
public class AddressHandler implements DesensitizationHandler<Address> {

    @Override
    public String desensitize(String origin, Address annotation) {
        return origin + annotation.replacer();
    }

}
