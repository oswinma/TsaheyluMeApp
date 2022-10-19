package me.tsaheylu.repository;

import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {


    User findByEmail(String email);


}

