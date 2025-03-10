package cn.cloud.system.module.system.convert.user;

import cn.cloud.system.framework.common.util.collection.CollectionUtils;
import cn.cloud.system.framework.common.util.object.BeanUtils;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserRespVO;
import cn.cloud.system.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import cn.cloud.system.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default List<UserRespVO> convertList(List<AdminUserDO> list) {
        return CollectionUtils.convertList(list, user -> convert(user));
    }

    default UserRespVO convert(AdminUserDO user) {
        UserRespVO userVO = BeanUtils.toBean(user, UserRespVO.class);
        return userVO;
    }

    default List<UserSimpleRespVO> convertSimpleList(List<AdminUserDO> list) {
        return CollectionUtils.convertList(list, user -> {
            UserSimpleRespVO userVO = BeanUtils.toBean(user, UserSimpleRespVO.class);
            return userVO;
        });
    }

}
