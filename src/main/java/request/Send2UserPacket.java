package request;

import command.Command;
import command.Packet;

/**
 * Created by chen on 2018/12/3.
 */
public class Send2UserPacket extends Packet {
    @Override
    public Byte getCommond() {
        return Command.LOGOUT_REQUEST;
    }
}
