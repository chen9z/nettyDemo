package request;

import command.Command;
import command.Packet;

/**
 * Created by chen on 2018/11/28.
 */
public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommond() {
        return Command.MESSAGE_REQUEST;
    }

    private String requestMessage;

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }
}
