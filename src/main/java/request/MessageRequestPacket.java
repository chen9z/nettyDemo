package request;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/11/28.
 */
@Data
public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    private String requestMessage;

    private String toUserId;

    private String fromUserId;
}
