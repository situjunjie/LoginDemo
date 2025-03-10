package cn.cloud.system.module.system.controller.admin.auth;

import cn.hutool.core.util.StrUtil;
import cn.cloud.system.framework.common.pojo.CommonResult;
import cn.cloud.system.framework.security.config.SecurityProperties;
import cn.cloud.system.framework.security.core.util.SecurityFrameworkUtils;
import cn.cloud.system.module.system.controller.admin.auth.vo.AuthLoginReqVO;
import cn.cloud.system.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.cloud.system.module.system.enums.logger.LoginLogTypeEnum;
import cn.cloud.system.module.system.service.auth.AdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.cloud.system.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "登录")
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request,
                securityProperties.getTokenHeader(), securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return success(true);
    }








}
