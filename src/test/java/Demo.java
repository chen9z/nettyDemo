import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

/**
 * Created by chen on 2018/11/21.
 */
public class Demo {

    @Test
    public void func1() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9, 100);
        System.out.println(byteBuf.maxCapacity());
    }

    @Test
    public void func2() {
        double a=1;
        double b=0.9;
        System.out.println(a - b);

    }
}
