package response;

import command.Command;
import command.Packet;

/**
 * Created by chen on 2018/11/28.
 */
public class MessageResponsePacket extends Packet {
    @Override
    public Byte getCommond() {
        return Command.MESSAGE_RESPONSE;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
