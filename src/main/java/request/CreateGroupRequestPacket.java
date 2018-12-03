package request;

import command.Command;
import command.Packet;
import lombok.Data;

import java.util.List;

/**
 * Created by chen on 2018/12/3.
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    private String createUserId;
    @Override
    public Byte getCommond() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
