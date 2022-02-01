package me.tsaheylu.service;

public interface MailService {

  public abstract void sendEmailValidationEmail(
      long id, String email, String nickname, String code);

  public abstract void sendForgetPassEmail(Long id, String emails, String nickname, String code);

  public abstract void sendInviteFriendByEmail(
      String email, String nickname, String subject, String msgBody);

  public abstract void sendFeedbackToDeveloper(Long id, String text);
}
