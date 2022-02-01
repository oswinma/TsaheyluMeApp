package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.UserOpenID;
import org.apache.ibatis.annotations.Param;

public interface UserOpenIDMapper {

  public abstract UserOpenID getuser_openidsByToken(@Param("token") String token);

  public abstract UserOpenID getuser_openidsByuser_id(@Param("user_id") String user_id);

  public abstract UserOpenID getuser_openidsByprovider_id(@Param("provider_id") String provider_id);

  public abstract void DetachOpenID(@Param("token") String token, @Param("user_id") String user_id);

  public abstract void DetachOpenIDsByUser(@Param("user_id") String user_id);

  public abstract void AttachOpenID(UserOpenID uo);

  public abstract UserOpenID Get(@Param("id") Long id);

  public abstract void Insert(UserOpenID useropenid);

  public abstract void Update(UserOpenID useropenid);

  public abstract void Delete(@Param("id") Long id);

  public abstract UserOpenID getByUseridProvider(
      @Param("userid") Long userid, @Param("provider") String provider);
}
