package attribute;

import io.netty.util.AttributeKey;

/**
 * Created by chen on 2018/11/28.
 */
public interface Attributes{
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
