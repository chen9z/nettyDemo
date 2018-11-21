package command;

import lombok.Data;

/**
 * Created by chen on 2018/11/21.
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;
    private String userName;
    private String password;
    @Override
    public Byte getCommond() {
        return Command.LOGIN_REQUEST;
    }
}
