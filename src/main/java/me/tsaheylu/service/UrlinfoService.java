package me.tsaheylu.service;

import me.tsaheylu.model.URLInfo;

public interface UrlinfoService {

    URLInfo getByUrl(String url);

    URLInfo save(URLInfo urlInfo);
}
