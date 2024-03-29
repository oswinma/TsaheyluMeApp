package me.tsaheylu.serviceImpl;


import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.FavURLDtoMapper;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FavURLChannel;
import me.tsaheylu.common.FavURLStatus;
import me.tsaheylu.controller.WebsocketController;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.URLInfo;
import me.tsaheylu.repository.FavurlRepo;
import me.tsaheylu.service.*;
import me.tsaheylu.util.CommonUtils;
import me.tsaheylu.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavURLServiceImpl implements FavURLService {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    private final FavURLDtoMapper favurlDtoMapper;

    private final FavurlRepo favurlRepo;

    private final UrlinfoService urlinfoService;
    private final FriendService friendService;
    private final MessageService messageService;

    private final PushChannelService pushChannelService;

/*
    @Override
    public HashMap<String, Object> getFavurlsByStatus(Long toids, String startCursor, int status) {
        // TODO Auto-generated method stub
        if (toids != null) {

            Long toid = Long.valueOf(toids);

            Long fromlimit = 0l;
            if (startCursor != null && !startCursor.equals("")) {
                fromlimit = Long.valueOf(startCursor);
            }

            List<FavURLDTO> list = favurlDao.getFavURLShowListByToidStatusLimit(toid, status, fromlimit);

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
*/


    @Override
    public HashMap<String, Object> getFavurlsByStatusAndPage(Long toid, int status, final int pageIndex, @RequestParam final int pageSize) {
        // TODO Auto-generated method stub

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<FavURLDTO> pageList = favurlRepo.getDtoListByToidAndStatus(toid, status, pageable);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("FavurlDtoList", pageList.getContent());
        data.put("ListSize", pageList.getTotalElements());
        data.put("pageIndex", pageIndex);
        data.put("pageSize", pageSize);
        return (HashMap<String, Object>) data;
    }

    @Override
    public HashMap<String, Object> getFavurlsByFavAndPage(Long toid, boolean fav, final int pageIndex, @RequestParam final int pageSize) {
        // TODO Auto-generated method stub

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<FavURLDTO> pageList = favurlRepo.getDtoListByToidAndFav(toid, fav, pageable);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("FavurlDtoList", pageList.getContent());
        data.put("ListSize", pageList.getTotalElements());
        data.put("pageIndex", pageIndex);
        data.put("pageSize", pageSize);
        return (HashMap<String, Object>) data;
    }

   /* @Override
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
    }*/

   /* @Override
    public boolean batchUpdateFavurlStatus(List<FavURL> favURLS) {

        ArrayList<FavURL> list = new ArrayList<FavURL>();
        Long toid = 0l;
        int increase = 0;
        for (FavURL favURL : favURLS) {
            Long id = favURL.getId();
            int status = favURL.getStatus();

            FavURL fu = get(Long.valueOf(id));

            if (fu != null) {
                if (status == FavURLStatus.NEW.getId()) {
                    increase = increase + 1;
                }

                if (status == FavURLStatus.ARCHIVE.getId()) {
                    increase = increase - 1;
                }

                if (status == FavURLStatus.REMOVE.getId()) {
                    fu.setFav(false);
                    if (fu.getStatus() == FavURLStatus.NEW.getId()) {
                        increase = increase - 1;
                    }
                }

                toid = fu.getToid();
                fu.setStatus(status);
                list.add(fu);
            }

            if (toid != 0l) {
                favurlRepo.saveAll(list);
                return true;
            }
        }
        return false;
    }*/

    @Override
    public FavURL save(FavURL favURL) {
        return favurlRepo.save(favURL);
    }

    @Override
    public FavURLDTO update(FavURLDTO favURLDTO) {
        FavURL favURL = favurlDtoMapper.DtoTo(favURLDTO);
        favurlRepo.save(favURL);
        return toDto(favURL);
    }

    public FavURL get(Long id) {

        Optional<FavURL> opt = favurlRepo.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public FavURLDTO getDto(Long id) {

        return favurlRepo.getDtoById(id);

    }

    @Override
    public void delete(Long id) {
        favurlRepo.deleteById(id);
    }

    @Override
    public FavURLDTO createFavurl(FavURLDTO favURLDTO) {
        FavURL favURL = favurlDtoMapper.DtoTo(favURLDTO);
        favurlRepo.save(favURL);
        return null;
    }

    FavURLDTO toDto(FavURL favURL) {
        return favurlRepo.getDtoById(favURL.getId());
    }

    List<FavURLDTO> toDtoList(List<FavURL> favURLList) {
        return favurlRepo.getDtoListById(favURLList.stream().map(FavURL::getId).collect(Collectors.toList()));
    }


    @Override
    public List<FavURL> sendFavurls(Long fromid, String groupidss, boolean toall, boolean tome, String url, String urltitle, String iconurls) {

//        Long fromid = Long.valueOf(fromids);
        URLInfo urlInfo = urlinfoService.getByUrl(url);
        if (urlInfo == null) {
            urlInfo = new URLInfo();
            urlInfo.setUrl(url);
            urlInfo.setHost(CommonUtils.getHostByUrl(url));
            urlInfo.setTitle(urltitle);
            urlInfo.setShare(1l);
            if (iconurls != null) {
                urlInfo.setIcon(iconurls);
            }

            urlInfo = urlinfoService.save(urlInfo);
        } else {
            urlInfo.setShare(urlInfo.getShare() + 1);
            urlInfo = urlinfoService.save(urlInfo);
        }

        Long urlid = urlInfo.getId();

        Date sendtime = new Timestamp(System.currentTimeMillis());


        List<FavURL> list = null;
        if (toall) {
            list = sendToAll(fromid, urlid, sendtime);
        } else {
            list = sendToGroup(fromid, groupidss, urlid, sendtime);
        }

        if (tome) {
            FavURL fu = buildFavurl(fromid, fromid, urlid, sendtime, true);
            list.add(fu);
        }

        return saveFavurlList(list);
    }

    private List<FavURL> sendToAll(Long fromid, Long urlid, Date sendtime) {
        List<Friend> friends = friendService.getSendFriendList(fromid);
        List<FavURL> list = new ArrayList<FavURL>();

        for (int i = 0, len = friends.size(); i < len; i++) {
            Friend f = friends.get(i);
            FavURL fu = buildFavurl(fromid, f.getToid(), urlid, sendtime, f.isPopup());
            list.add(fu);
        }

        return list;
    }

    private List<FavURL> sendToGroup(Long fromid, String groupidss, Long urlid, Date sendtime) {

        List<FavURL> list = new ArrayList<FavURL>();
        if (groupidss != null) {
            String groupids[] = groupidss.split("\\|");
            List<String> groupidlist = new ArrayList<String>();

            for (int i = 0, len = groupids.length; i < len; i++) {
                String groupid = groupids[i];
                groupidlist.add(groupid);
            }

            List<Friend> friends = friendService.getSendFriendListByFromidAndGroupidList(fromid, groupidlist);

            for (int j = 0, leng = friends.size(); j < leng; j++) {
                Friend f = friends.get(j);
                FavURL fu = buildFavurl(fromid, f.getFromid(), urlid, sendtime, f.isPopup());
                list.add(fu);
            }
        }
        return list;
    }

    public FavURL buildFavurl(Long fromid, Long toid, Long urlid, Date sendtime, boolean popup) {
        int status = FavURLStatus.PENDING.getId();
        String channel = null;

        FavURL t = new FavURL();

        if (!popup) {
            channel = FavURLChannel.WEB.getType();
            status = FavURLStatus.NEW.getId();
        } else {
            int count = favurlRepo.getNumByToidAndStatus(toid, FavURLStatus.PENDING.getId());
            if (count > Constants.MAX_TABS) {
                status = FavURLStatus.NEW.getId();
                channel = FavURLChannel.WEB.getType();
            }
        }

        t.setFromid(fromid);
        t.setToid(toid);
        t.setUrlid(urlid);
        t.setChannel(channel);
        t.setStatus(status);
        t.setSendtime(sendtime);

        return t;
    }

    public List<FavURL> saveFavurlList(List<FavURL> flist) {
        List<FavURL> list = new ArrayList<>();

        if (flist != null) {
            int len = flist.size();
            if (len > 0) {
                List<Message> mlist = new ArrayList<Message>();

                favurlRepo.saveAll(flist);
                FavURL fu = flist.get(0);
                Long serial = fu.getId();
                for (int i = 0; i < len; i++) {
                    FavURL ful = flist.get(i);
                    ful.setSerial(serial);
                    logger.debug(ful.getStatus() + "");
                    if (ful.getStatus() == FavURLStatus.PENDING.getId()) {

                        pushChannelService.sendToChannel(ful);
                    } else {
                        Message sm = messageService.buildFavurlSendMessage(ful);
                        mlist.add(sm);
                    }
                }
                if (!flist.isEmpty()) {
                    Iterable<FavURL> iterable = favurlRepo.saveAll(flist);
                    iterable.forEach(list::add);

                }

                if (!mlist.isEmpty()) {
                    messageService.saveAll(mlist);

                    mlist.forEach(m -> {
                        messageService.updateMsgNumToChannel(m);
                    });

                }
            }
        }
        return list;
    }

    @Override
    public FavURLDTO updateChannel(Long id, String channel) {
        // TODO Auto-generated method stub

        Optional<FavURL> optionalFavURL = favurlRepo.findById(id);
        FavURLDTO favURLDTO = null;
        if (optionalFavURL.isPresent()) {
            FavURL favURL = optionalFavURL.get();
            pushChannelService.removeFromChannel(favURL); //avoid duplicate tabs in browser when send favurl to same user
            Date readtime = DateUtils.getCurrentTime();
            favURL.setReadtime(readtime);

            Message message = null;
            if (FavURLChannel.CHROME.getType().equals(channel)) {
                favURL.setStatus(FavURLStatus.ARCHIVE.getId());
                favURL.setChannel(FavURLChannel.CHROME.getType());
                favURLDTO = toDto(favURL);
                message = messageService.buildFavurlReadMessage(favURLDTO);
            }

            if (FavURLChannel.WEB.getType().equals(channel)) {
                favURL.setStatus(FavURLStatus.NEW.getId());
                favURL.setChannel(FavURLChannel.WEB.getType());
                message = messageService.buildFavurlSendMessage(favURL);
            }

            favurlRepo.save(favURL);

            if (message != null) {
                messageService.saveEntity(message);
                messageService.updateMsgNumToChannel(message);
            }

        }

        return favURLDTO;
    }
}
