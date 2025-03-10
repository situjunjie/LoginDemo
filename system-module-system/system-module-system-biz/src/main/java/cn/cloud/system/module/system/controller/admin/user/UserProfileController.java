package cn.cloud.system.module.system.controller.admin.user;

import cn.cloud.system.framework.common.pojo.CommonResult;
import cn.cloud.system.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.cloud.system.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserRespVO;
import cn.cloud.system.module.system.convert.user.UserConvert;
import cn.cloud.system.module.system.dal.dataobject.user.AdminUserDO;
import cn.cloud.system.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.cloud.system.framework.common.pojo.CommonResult.success;
import static cn.cloud.system.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    public CommonResult<UserRespVO> getUserProfile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());

        return success(UserConvert.INSTANCE.convert(user));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }


}
