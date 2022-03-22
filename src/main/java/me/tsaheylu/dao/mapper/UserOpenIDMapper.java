package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.UserOpenID;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserOpenIDMapper {

    UserOpenID getuser_openidsByToken(@Param("token") String token);

    UserOpenID getuser_openidsByuser_id(@Param("user_id") String user_id);

    UserOpenID getuser_openidsByprovider_id(@Param("provider_id") String provider_id);

    void DetachOpenID(@Param("token") String token, @Param("user_id") String user_id);

    void DetachOpenIDsByUser(@Param("user_id") String user_id);

    void AttachOpenID(UserOpenID uo);

    UserOpenID Get(@Param("id") Long id);

    void Insert(UserOpenID useropenid);

    void Update(UserOpenID useropenid);

    void Delete(@Param("id") Long id);

    UserOpenID getByUseridProvider(
            @Param("userid") Long userid, @Param("provider") String provider);
}
