package cn.cloud.system.module.system.controller.admin.user;

import cn.cloud.system.framework.common.pojo.CommonResult;
import cn.cloud.system.framework.common.pojo.PageResult;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserPageReqVO;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserRespVO;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import cn.cloud.system.module.system.convert.user.UserConvert;
import cn.cloud.system.module.system.dal.dataobject.user.AdminUserDO;
import cn.cloud.system.module.system.service.user.AdminUserService;
import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.cloud.system.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/system/user")
@Validated
public class UserController {

    @Resource
    private AdminUserService userService;

    @PostMapping("/create")
    @Operation(summary = "新增用户")
    public CommonResult<Long> createUser(@Valid @RequestBody UserSaveReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    @PutMapping("update")
    @Operation(summary = "修改用户")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserSaveReqVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }


    @GetMapping("/page")
    @Operation(summary = "获得用户分页列表")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageReqVO pageReqVO) {
        // 获得用户分页列表
        PageResult<AdminUserDO> pageResult = userService.getUserPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }
        return success(new PageResult<>(UserConvert.INSTANCE.convertList(pageResult.getList()),
                pageResult.getTotal()));
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        AdminUserDO user = userService.getUser(id);
        if (user == null) {
            return success(null);
        }
        // 拼接数据
        return success(UserConvert.INSTANCE.convert(user));
    }



}
