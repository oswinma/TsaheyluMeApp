package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.dto.UserDTO;
import me.tsaheylu.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDaoMapper {

    User getUserByID(@Param("id") Long id, @Param("status") int status);

    User getUserByEmail(@Param("email") String email);

    Integer checkUser(
            @Param("email") String email, @Param("password") String password);

    List<ContactDTO> search(
            @Param("word") String word,
            @Param("startCursor") Long startCursor,
            @Param("fromid") Long fromid);

    User Get(@Param("id") Long id);

    UserDTO GetUserDTO(@Param("id") Long id);

    void Insert(User user);

    void Update(User user);

    void Delete(@Param("id") Long id);

    List<ContactDTO> getContactListByUserIdList(
            @Param("list") List<Long> list, @Param("currentuserid") Long currentuserid);

    List<User> getAllUsers(@Param("id") Long id);


}
