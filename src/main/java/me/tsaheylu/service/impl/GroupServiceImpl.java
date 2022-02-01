package me.tsaheylu.service.impl;

import me.tsaheylu.dao.mapper.FavURLMapper;
import me.tsaheylu.dao.mapper.UserMapper;
import me.tsaheylu.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

  @Autowired private FavURLMapper favurlMapper;
  @Autowired private UserMapper userMapper;

}
