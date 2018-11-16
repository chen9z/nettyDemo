import java.net.Socket;
import java.util.Date;

/**
 * Created by chen on 2018/11/16.
 */
public class ClientDemo {

    public static void main(String args[]) {
            try {

                while (true) {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    socket.getOutputStream().write((new Date()+":hello world 张琦").getBytes());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
