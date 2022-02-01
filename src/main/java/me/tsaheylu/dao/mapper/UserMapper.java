package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.Contact;
import me.tsaheylu.dto.UserDTO;
import me.tsaheylu.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

  public abstract User getUserByID(@Param("id") Long id, @Param("status") int status);

  public abstract User getUserByEmail(@Param("email") String email);

  public abstract Integer checkUser(
      @Param("email") String email, @Param("password") String password);

  public abstract List<Contact> search(
      @Param("word") String word,
      @Param("startCursor") Long startCursor,
      @Param("fromid") Long fromid);

  public abstract User Get(@Param("id") Long id);

  public abstract UserDTO GetUserDTO(@Param("id") Long id);

  public abstract void Insert(User user);

  public abstract void Update(User user);

  public abstract void Delete(@Param("id") Long id);

  public abstract List<Contact> getContactListByUserIdList(
      @Param("list") List<Long> list, @Param("currentuserid") Long currentuserid);

  public abstract List<User> getAllUsers(@Param("id") Long id);


}
