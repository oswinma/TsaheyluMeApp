package me.tsaheylu.common;

public class Constants {

  public static String currentENV = "PRD";
  //    public static String currentENV = "DEV";

  public static final String envDEV = "DEV";
  public static final String envPRD = "PRD";

  public static final String CURRENTDOMAIN = "https://tsahaylu.com";
  public static final String SESSION_USERID = "_USERID";
  public static final String COOKIE_USERID = "current_USERID";
  public static final int COOKIE_PERIOD = 60 * 24 * 3600;

  public static final String RECIEVE_GROUP_NAME = "Recieve";
  public static final String RECIEVE_GROUP_DES = "Default recieve group";

  public static final int CODE_LENGTH = 10;
  public static final int PAGE_SIZE = 10;
  public static final int MAX_TABS = 20;

  public static final String ENCODE_METHOD = "MD5";

  public static final String PAGE_TEMPLATE_EMAILCONFIRM = "EmailConfirmation.html";
  public static final String PAGE_TEMPLATE_PASSRESET = "ForgotPass.html";
  public static final String PAGE_TEMPLATE_INVITATION = "Invitation.html";

  public static final String PAGE_LOGIN = "/login.html";
  public static final String PAGE_JSP_SIGNUP = "/jsp/OpenIDSignUp.jsp";
  public static final String PAGE_JSP_INVITESIGNUP = "/jsp/InviteSignUp.jsp";
  public static final String PAGE_SIGNUPE = "/signup.html";
  public static final String PAGE_HOME = "/new.html";
  public static final String PAGE_FRIENDS = "/friends.html";
  public static final String PAGE_FORGOT = "/forgot.html";
  public static final String PAGE_INVITATION = "/invitations.html";
  public static final String PAGE_JSP_FORMREDIRECTOIN = "/jsp/formredirection.jsp";
  public static final String PAGE_JSP_NOTICE = "/jsp/notice.jsp";
  //  public static final String PAGE_JSP_AVATAR = "/setting_basic.jsp";
  public static final String PAGE_JSP_RESTPASS = "/jsp/ResetPass.jsp";
  public static final String PAGE_JSP_LINKPAGE = "/jsp/link.jsp";

  public static final String PUBLIC_ID = "";

  public static final String DEFAULT_AVATAR_URL = "images/mystery-man.jpg";

  public static final String DEFAULT_ICON_URL = "../images/defaulticon.ico";

  public static final Long SYSTEMID = 1234567890l;

  public static final String RETURN_SUCCESS = "true";
  public static final String RETURN_FAILUTE = "false";

  public static final String EMAILADDPATTERN =
      "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

  public static final String FIREBASEDBURL = "https://tsahayluapp.firebaseio.com";

  //  public static final String AVATARSAVEPATH = "I:/Workspace/FutureTsahayluStatic/avatars";
  //  public static final String TMPAVATARSAVEPATH =
  // "I:/Workspace/FutureTsahayluStatic/avatars/tmp";
  public static final String AVATARSAVEPATH = "/home/oswin/tsahaylu/FutureTsahayluStatic/avatars";
  public static final String TMPAVATARSAVEPATH =
      "/home/oswin/tsahaylu/FutureTsahayluStatic/avatars/tmp";

  public static final String DEVEPLOPEREMAIL = "oswin.ma@gmail.com";

  public static Long TsahayluTeamID = 1000000l;
}
