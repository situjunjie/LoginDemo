package cn.cloud.system.module.system.convert.auth;

import cn.cloud.system.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.cloud.system.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginRespVO convert(OAuth2AccessTokenDO bean);

}
