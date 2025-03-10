package cn.cloud.system.module.system.api.user;

import cn.hutool.core.convert.Convert;
import cn.cloud.system.framework.common.pojo.CommonResult;
import cn.cloud.system.framework.common.util.collection.CollectionUtils;
import cn.cloud.system.module.system.api.user.dto.AdminUserRespDTO;
import cn.cloud.system.module.system.enums.ApiConstants;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.trans.service.AutoTransable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.cloud.system.module.system.api.user.AdminUserApi.PREFIX;

@FeignClient(name = ApiConstants.NAME) // TODO 测试：fallbackFactory =
@Tag(name = "RPC 服务 - 管理员用户")
@AutoTrans(namespace = PREFIX, fields = {"nickname"})
public interface AdminUserApi extends AutoTransable<AdminUserRespDTO> {

    String PREFIX = ApiConstants.PREFIX + "/user";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "通过用户 ID 查询用户")
    @Parameter(name = "id", description = "用户编号", example = "1", required = true)
    CommonResult<AdminUserRespDTO> getUser(@RequestParam("id") Long id);


    @GetMapping(PREFIX + "/list")
    @Operation(summary = "通过用户 ID 查询用户们")
    @Parameter(name = "ids", description = "部门编号数组", example = "1,2", required = true)
    CommonResult<List<AdminUserRespDTO>> getUserList(@RequestParam("ids") Collection<Long> ids);

    /**
     * 获得用户 Map
     *
     * @param ids 用户编号数组
     * @return 用户 Map
     */
    default Map<Long, AdminUserRespDTO> getUserMap(Collection<Long> ids) {
        List<AdminUserRespDTO> users = getUserList(ids).getCheckedData();
        return CollectionUtils.convertMap(users, AdminUserRespDTO::getId);
    }

    /**
     * 校验用户是否有效。如下情况，视为无效：
     * 1. 用户编号不存在
     * 2. 用户被禁用
     *
     * @param id 用户编号
     */
    default void validateUser(Long id) {
        validateUserList(Collections.singleton(id));
    }

    @GetMapping(PREFIX + "/valid")
    @Operation(summary = "校验用户们是否有效")
    @Parameter(name = "ids", description = "用户编号数组", example = "3,5", required = true)
    CommonResult<Boolean> validateUserList(@RequestParam("ids") Collection<Long> ids);

    @Override
    @GetMapping("select")
    default List<AdminUserRespDTO> selectByIds(List<?> ids) {
        return getUserList(Convert.toList(Long.class, ids)).getCheckedData();
    }

    @Override
    @GetMapping("select-list")
    default AdminUserRespDTO selectById(Object id) {
        return getUser(Convert.toLong(id)).getCheckedData();
    }

}
