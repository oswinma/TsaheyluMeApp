package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavURLDTO {

    private Long id;

    private Long fromid;

    private Long toid;

    private String url;

    private int status;

    private int fstatus;

    private Date sendtime;

    private Long serial;

    private Date readtime;

    private String comment;

    private boolean fav;

    private String channel;

    private String title;

    private String icon;

    private String host;

    private String nickname;

    private String avatarURL;

    private Long urlid;

    private Long share = 0l;

    private Long favs = 0l;

    //
    //	public static FavURLShow getFavURLShow(FavURL fu)
    //	{
    //		URLInfo ui = OfyDAOFactory.getInstance().getURLInfoDAO().Get(fu.getUrlid());
    //		User u=OfyDAOFactory.getInstance().getUserDAO().Get(fu.getFromid());
    //		this.id=fu.getId();
    //		this.fromid=fu.getFromid();
    //		this.toid=fu.getToid();
    //		this.url=ui.getUrl();
    //		this.status=fu.getStatus();
    //		this.fstatus=fu.getFstatus();
    //		this.sendtime=fu.getSendtime();
    //		this.serial=fu.getSerial();
    //		this.comment=fu.getComment();
    //		this.fav=fu.isFav();
    //		this.channel=fu.getChannel();
    //
    //		this.urlid=ui.getId();
    //		this.title=ui.getTitle();
    //		this.icon=ui.getIcon();
    //		this.host=ui.getHost();
    //		this.nickname=u.getNickname();
    //		this.avatarURL=u.getAvatarURL();
    //		this.readtime=fu.getReadtime();
    //		this.share=ui.getShare();
    //		this.favs=ui.getFavs();
    //	}
    //
    //	public static FavURLShow getFavURLShow(FavURL fu, URLInfo ui,User u)
    //	{
    //		this.id=fu.getId();
    //		this.fromid=fu.getFromid();
    //		this.toid=fu.getToid();
    //		this.url=ui.getUrl();
    //		this.status=fu.getStatus();
    //		this.fstatus=fu.getFstatus();
    //		this.sendtime=fu.getSendtime();
    //		this.serial=fu.getSerial();
    //		this.comment=fu.getComment();
    //		this.fav=fu.isFav();
    //		this.channel=fu.getChannel();
    //
    //		this.urlid=ui.getId();
    //		this.title=ui.getTitle();
    //		this.icon=ui.getIcon();
    //		this.host=ui.getHost();
    //		this.nickname=u.getNickname();
    //		this.avatarURL=u.getAvatarURL();
    //		this.readtime=fu.getReadtime();
    //		this.share=ui.getShare();
    //		this.favs=ui.getFavs();
    //	}
    //

   
}
