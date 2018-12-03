package response;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/12/3.
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private String message;
    @Override
    public Byte getCommond() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
