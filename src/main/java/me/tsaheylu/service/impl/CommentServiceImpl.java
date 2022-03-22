package me.tsaheylu.service.impl;


import me.tsaheylu.dao.mapper.FavURLDaoMapper;
import me.tsaheylu.dao.mapper.UserDaoMapper;
import me.tsaheylu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private FavURLDaoMapper favurlMapper;
  @Autowired private UserDaoMapper userDaoMapper;

}
