package request;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/12/3.
 */
@Data
public class LogoutRequestPacket extends Packet {
    private String userId;
    @Override
    public Byte getCommond() {
        return Command.LOGOUT_REQUEST;
    }
}
