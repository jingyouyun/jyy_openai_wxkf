package cn.jingyouyun.openai.wxkf.conf.log;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;

/**
 * 描述：
 *
 * @author zhujingchun
 * @date 2023/2/21
 */
public class StackTraceConverter extends ThrowableProxyConverter {

    public String convert(ILoggingEvent event) {
        IThrowableProxy throwableProxy = event.getThrowableProxy();
        if (throwableProxy == null) {
            return "";
        } else {
            StringBuilder buf = new StringBuilder(32);
            buf.append(event.getThrowableProxy().getClassName());
            buf.append(":");
            buf.append(event.getThrowableProxy().getMessage());
            return buf.toString();
        }
    }
}
