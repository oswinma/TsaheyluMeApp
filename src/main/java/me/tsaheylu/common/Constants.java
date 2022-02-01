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

  public static final String DEFAULT_EMAIL_FROM = "support@tsahaylu.com";

  public static final String TWITTER_API_KEY = "Jwp7H6jf5pS61Dsx28Uxg";
  public static final String TWITTER_API_SECRET = "G0KV7prWD7U1fierlXooZVGnMpmHSj1Y6b85B88";

  public static final String FACEBOOK_API_KEY = "141416579340734";
  public static final String FACEBOOK_API_SECRET = "081cbda671968b5c7aeec24ed007cceb";

  public static final String FACEBOOK_API_KEY_TEST = "812771222205263";
  public static final String FACEBOOK_API_SECRET_TEST = "bd7e829c829e421ab348b6f271374ab2";

  public static final String GOOGLE_API_KEY_TEST =
      "920446357904-8ejcev21hac9kffaurtd64i9d1lim0dl.apps.googleusercontent.com";
  public static final String GOOGLE_API_SECRET_TEST = "piYHrnJ3IAW-MyyEapjsKCCG";

  public static final String GOOGLE_API_KEY =
      "920446357904-l4151jjr757mkpi6sq47okocgtkg7t88.apps.googleusercontent.com";
  public static final String GOOGLE_API_SECRET = "1fkdMpDB4vXW0FT_GHFNt5Lo";

  public static final String googleOAuthReturnUrlPrd =
      "https://tsahaylu.com/api/service/googleOAuth?is_return=true";
  public static final String googleOAuthReturnUrlDev =
      "http://localhost/api/service/googleOAuth?is_return=true";

  public static final String twitterOAuthReturnUrlPrd =
      "https://tsahaylu.com/api/service/twitterOAuth?is_return=true";

  public static final String facebookOAuthReturnUrlPrd =
      "https://tsahaylu.com/api/service/facebookOAuth?is_return=true";
  public static final String facebookOAuthReturnUrlDev =
      "http://localhost/api/service/facebookOAuth?is_return=true";

  public static final String twitterOAuthUserInfoResourceURL =
      "https://api.twitter.com/1.1/account/verify_credentials.json?include_email=true&include_entities=false&skip_status=true";
  public static final String TWITTER_FRIENDS_LINK =
      "https://api.twitter.com/1.1/friends/list.json?count=200&include_user_entities=false&skip_status=true";

  public static final String facebookOAuthUserInfoResourceURL =
      "https://graph.facebook.com/v3.2/me?fields=email,first_name,picture.type(large)";
  public static final String FACEBOOK_FRIENDS_LINK = "https://graph.facebook.com/v3.2/";

  //    public static String googleOAuthScope = "email
  // https://www.googleapis.com/auth/contacts.readonly";
  public static String googleOAuthScope = "https://www.googleapis.com/auth/contacts.readonly";
  public static final String googleOAuthUserInfoResourceURL =
      //                  "https://www.googleapis.com/auth/userinfo.profile";
      "https://www.googleapis.com/oauth2/v2/userinfo";
  //            "https://www.googleapis.com/plus/v1/people/me";
  public static final String GOOGLE_CONTACT_LINK =
      "https://www.google.com/m8/feeds/contacts/default/full?alt=json&max-results=100";

  public static String openidProviderFacebook = "facebook";
  public static String openidProviderGoogle = "google";
  public static String openidProviderTwitter = "twitter";

  public static String OAuthContactReturnUrlPrd = "https://tsahaylu.com/OpenidReturn.html";
  public static String OAuthContactReturnUrlDev = "http://127.0.0.1/OpenidReturn.html";

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
