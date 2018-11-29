package response;

import command.Command;
import command.Packet;
import lombok.Data;

/**
 * Created by chen on 2018/11/23.
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String msg;

    private String userName;

    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommond() {
        return Command.LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
