package me.tsaheylu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

  /**
   * 获取最原始被调用的堆栈信息
   *
   * @return
   */
  private static StackTraceElement findCaller() {
    // 获取堆栈信息
    StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
    if (null == callStack) {
      return null;
    }
    // 最原始被调用的堆栈信息
    StackTraceElement caller = null;
    // 日志类名称
    String logClassName = LogUtil.class.getName();
    // 循环遍历到日志类标识
    boolean isEachLogClass = false;

    // 遍历堆栈信息，获取出最原始被调用的方法信息
    for (StackTraceElement strackTraceEle : callStack) {
      // 遍历到日志类
      if (logClassName.equals(strackTraceEle.getClassName())) {
        isEachLogClass = true;
      }
      // 下一个非日志类的堆栈，就是最原始被调用的方法
      if (isEachLogClass) {
        if (!logClassName.equals(strackTraceEle.getClassName())) {
          isEachLogClass = false;
          caller = strackTraceEle;
          break;
        }
      }
    }
    return caller;
  }

  /**
   * 自动匹配请求类名，生成logger对象，此处 logger name 值为 [className].[methodName]() Line: [fileLine]
   *
   * @return
   * @author yzChen
   * @date 2016年10月13日 下午11:50:59
   */
  private static Logger logger() {
    StackTraceElement caller = findCaller(); // 最原始被调用的堆栈对象
    if (caller == null) {
      return LoggerFactory.getLogger(LogUtil.class);

    } else {
      return LoggerFactory.getLogger(
          caller.getClassName()
              + "."
              + caller.getMethodName()
              + "() Line: "
              + caller.getLineNumber());
    }
  }

  public static void trace(String msg) {
    trace(msg, null);
  }

  public static void trace(String msg, Throwable e) {
    logger().trace(msg, e);
  }

  public static void debug(String msg) {
    debug(msg, null);
  }

  public static void debug(String msg, Throwable e) {
    logger().debug(msg, e);
  }

  public static void info(String msg) {
    info(msg, null);
  }

  public static void info(String msg, Throwable e) {
    logger().info(msg, e);
  }

  public static void warn(String msg) {
    warn(msg, null);
  }

  public static void warn(String msg, Throwable e) {
    logger().warn(msg, e);
  }

  public static void error(String msg) {
    error(msg, null);
  }

  public static void error(String msg, Throwable e) {
    logger().error(msg, e);
  }
}
