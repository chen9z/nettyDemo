package response;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/11/23.
 */
@Data
public class LoginResponsePack extends Packet {

    private boolean success;

    private String msg;

    @Override
    public Byte getCommond() {
        return Command.LOGIN_RESPONSE;
    }
}
