package response;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/12/3.
 */
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

}
