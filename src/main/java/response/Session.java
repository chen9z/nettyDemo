package response;

/**
 * Created by chen on 2018/11/29.
 */
public class Session {

    public Session(String userId, String userName) {
        this.userId=userId;
        this.userName=userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userId;
    private String userName;


}
