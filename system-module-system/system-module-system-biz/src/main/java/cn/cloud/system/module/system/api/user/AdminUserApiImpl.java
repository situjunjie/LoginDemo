package cn.cloud.system.module.system.api.user;

import cn.cloud.system.framework.common.pojo.CommonResult;
import cn.cloud.system.framework.common.util.object.BeanUtils;
import cn.cloud.system.module.system.api.user.dto.AdminUserRespDTO;
import cn.cloud.system.module.system.dal.dataobject.user.AdminUserDO;
import cn.cloud.system.module.system.service.user.AdminUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.cloud.system.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class AdminUserApiImpl implements AdminUserApi {

    @Resource
    private AdminUserService userService;

    @Override
    public CommonResult<AdminUserRespDTO> getUser(Long id) {
        AdminUserDO user = userService.getUser(id);
        return success(BeanUtils.toBean(user, AdminUserRespDTO.class));
    }



    @Override
    public CommonResult<List<AdminUserRespDTO>> getUserList(Collection<Long> ids) {
        List<AdminUserDO> users = userService.getUserList(ids);
        return success(BeanUtils.toBean(users, AdminUserRespDTO.class));
    }



    @Override
    public CommonResult<Boolean> validateUserList(Collection<Long> ids) {
        userService.validateUserList(ids);
        return success(true);
    }

}
