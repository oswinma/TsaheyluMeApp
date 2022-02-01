package me.tsaheylu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/api/websocket/{myWebsocket}")
public class WebsocketController {
  private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

  public static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();

  @OnOpen
  public void onOpen(@PathParam("myWebsocket") String myWebsocket, Session session) {
    logger.info("Websocket Start Connecting:" + myWebsocket);
    System.out.println("enter" + myWebsocket);
    clients.put(myWebsocket, session);
  }

  @OnMessage
  public String onMessage(@PathParam("myWebsocket") String myWebsocket, String message) {
    return "Got your message (" + message + ").Thanks !";
  }

  @OnError
  public void onError(@PathParam("myWebsocket") String myWebsocket, Throwable throwable) {
    logger.info("Websocket Connection Exception:" + myWebsocket);
    logger.info(throwable.getMessage(), throwable);
    clients.remove(myWebsocket);
  }

  @OnClose
  public void onClose(@PathParam("myWebsocket") String myWebsocket) {
    logger.info("Websocket Close Connection:" + myWebsocket);
    clients.remove(myWebsocket);
  }

  public static void broadcast(String myWebsocket, String message) {
    if (clients.containsKey(myWebsocket)) {
      clients.get(myWebsocket).getAsyncRemote().sendText(message);
    } else {
      throw new NullPointerException("[" + myWebsocket + "]Connection does not exist");
    }
  }
}
