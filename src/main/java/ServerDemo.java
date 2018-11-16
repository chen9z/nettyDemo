import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by chen on 2018/11/16.
 */
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(()->{
            while (true) {
                try {
                    Socket socket=serverSocket.accept();
                    new Thread(()->{
                        System.out.println("创建了");
                        int len;
                        byte[] data = new byte[1024];
                        try {
                            InputStream in=socket.getInputStream();
                            while ((len = in.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
