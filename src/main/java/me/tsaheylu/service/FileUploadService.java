package me.tsaheylu.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

  public abstract String updateAvatar(
      long id, HttpServletRequest request, ServletContext servletContext);
}
