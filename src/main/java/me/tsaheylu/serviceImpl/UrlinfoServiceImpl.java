package me.tsaheylu.serviceImpl;


import lombok.RequiredArgsConstructor;
import me.tsaheylu.dao.mapper.FavURLDaoMapper;
import me.tsaheylu.dao.mapper.UserDaoMapper;
import me.tsaheylu.model.URLInfo;
import me.tsaheylu.repository.UrlInfoRepo;
import me.tsaheylu.service.CommentService;
import me.tsaheylu.service.UrlinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlinfoServiceImpl implements UrlinfoService {

    private final UrlInfoRepo urlInfoRepo;


    @Override
    public URLInfo getByUrl(String url) {
        return urlInfoRepo.findByUrl(url);
    }

    @Override
    public URLInfo save(URLInfo urlInfo) {
        return urlInfoRepo.save(urlInfo);
    }
}
