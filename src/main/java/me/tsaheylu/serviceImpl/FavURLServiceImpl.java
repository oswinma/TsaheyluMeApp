package me.tsaheylu.serviceImpl;


import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.FavURLDtoMapper;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FavURLStatus;
import me.tsaheylu.dao.mapper.FavURLDaoMapper;
import me.tsaheylu.dao.mapper.FavURLDtoDaoMapper;
import me.tsaheylu.dao.mapper.UserDaoMapper;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.service.FavURLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FavURLServiceImpl implements FavURLService {

    private final FavURLDaoMapper favurlDao;
    private final UserDaoMapper userDaoMapper;
    private final FavURLDtoDaoMapper favurlDtoDao;

    private final FavURLDtoMapper favurlDtoMapper;

    @Override
    public HashMap<String, Object> getFavurlsByStatus(Long toids, String startCursor, int status) {
        // TODO Auto-generated method stub
        if (toids != null) {

            Long toid = Long.valueOf(toids);

            Long fromlimit = 0l;
            if (startCursor != null && !startCursor.equals("")) {
                fromlimit = Long.valueOf(startCursor);
            }

            List<FavURLDTO> list =
                    favurlDao.getFavURLShowListByToidStatusLimit(toid, status, fromlimit);

            if (!list.isEmpty()) {
                fromlimit = fromlimit + Constants.PAGE_SIZE;
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("FavurlDtoList", list);
            data.put("startCursor", fromlimit);
            return (HashMap<String, Object>) data;
        }
        return null;
    }

    @Override
    public FavURL getArchive(Long toid) {

        return favurlDao.Get(toid);
    }

    @Override
    public FavURL getNew(Long toid) {
        return favurlDao.Get(toid);
    }

    @Override
    public HashMap<String, Object> getFav(Long userid, String startCursor) {

        if (userid != null) {

            Long toid = Long.valueOf(userid);
            Long fromlimit = 0l;
            if (startCursor != null && !startCursor.equals("")) {
                fromlimit = Long.valueOf(startCursor);
            }

            List<FavURLDTO> list = favurlDao.getFavFavURLShowListByToidLimit(toid, fromlimit);

            if (!list.isEmpty()) {
                fromlimit = fromlimit + Constants.PAGE_SIZE;
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("FavurlDtoList", list);
            data.put("startCursor", fromlimit);
            return (HashMap<String, Object>) data;
        }

        return null;
    }

    @Override
    public boolean batchUpdateFavurlStatus(List<FavURL> favURLS) {

        ArrayList<FavURL> list = new ArrayList<FavURL>();
        Long toid = 0l;
        int increase = 0;
        for (FavURL favURL : favURLS) {
            Long id = favURL.getId();
            int status = favURL.getStatus();

            FavURL fu = favurlDao.Get(Long.valueOf(id));

            if (fu != null) {
                if (status == FavURLStatus.NEW) {
                    increase = increase + 1;
                }

                if (status == FavURLStatus.ARCHIVE) {
                    increase = increase - 1;
                }

                if (status == FavURLStatus.REMOVE) {
                    fu.setFav(false);
                    if (fu.getStatus() == FavURLStatus.NEW) {
                        increase = increase - 1;
                    }
                }

                toid = fu.getToid();
                fu.setStatus(status);
                list.add(fu);
            }

            if (toid != 0l) {
                favurlDao.BatchUpdate(list);
                return true;
            }
        }
        return false;
    }

    @Override
    public FavURL save(FavURL favURL) {
        favURL.setId(favurlDao.Insert(favURL));
        return favURL;
    }

    @Override
    public FavURLDTO update(FavURLDTO favURLDTO) {
        FavURL favURL = favurlDtoMapper.DtoTo(favURLDTO);
        favurlDao.Update(favURL);
        return toDto(favURL);
    }

    @Override
    public FavURL get(Long id) {
        return favurlDao.Get(id);
    }

    @Override
    public void delete(Long id) {
        favurlDao.Delete(id);
    }

    FavURLDTO toDto(FavURL favURL) {
        return favurlDtoDao.getById(favURL.getId());
    }

    List<FavURLDTO> toDtoList(List<FavURL> favURLList) {
        return favurlDtoDao.getListById(favURLList);
    }
}
