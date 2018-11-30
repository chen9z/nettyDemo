package response;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/11/28.
 */
@Data
public class MessageResponsePacket extends Packet {
    @Override
    public Byte getCommond() {
        return Command.MESSAGE_RESPONSE;
    }

    private String responseMessage;

    private String fromUserId;

    private String fromUserName;

}
