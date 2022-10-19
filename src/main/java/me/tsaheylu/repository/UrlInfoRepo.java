package me.tsaheylu.repository;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.URLInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UrlInfoRepo extends CrudRepository<URLInfo, Long> {


    URLInfo findByUrl(String url);
}

