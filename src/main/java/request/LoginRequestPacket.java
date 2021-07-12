package request;

import command.Command;
import lombok.Data;
import command.Packet;

/**
 * Created by chen on 2018/11/21.
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;
    private String userName;
    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
