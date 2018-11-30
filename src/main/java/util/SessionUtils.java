package util;

import attribute.Attributes;
import io.netty.channel.Channel;
import response.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chen on 2018/11/28.
 */
public class SessionUtils {

    private static final Map<String, Channel> channelMap = new HashMap<>();
    public static boolean hasLogin(Channel channel) {
        Boolean b = channel.attr(Attributes.LOGIN).get();
        return channel.attr(Attributes.SESSION).get()!=null;
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static void bindSession(Session session, Channel channel) {
        channelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);

    }

    public static void unBindSession(Session session, Channel channel) {
        channelMap.remove(session.getUserId());
        channel.attr(Attributes.SESSION).set(null);
    }

    public static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    public static Channel getChannel(String toUserId) {
        return channelMap.get(toUserId);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }
}
