package me.tsaheylu.service.impl;


import me.tsaheylu.dao.mapper.FavURLMapper;
import me.tsaheylu.dao.mapper.UserMapper;
import me.tsaheylu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private FavURLMapper favurlMapper;
  @Autowired private UserMapper userMapper;

}
