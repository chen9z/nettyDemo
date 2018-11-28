package util;

import attribute.Attributes;
import io.netty.channel.Channel;

/**
 * Created by chen on 2018/11/28.
 */
public class LoginUtils {

    public static boolean hasLogin(Channel channel) {
        Boolean b = channel.attr(Attributes.LOGIN).get();
        return b != null && b;
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }
}
