package command;

import lombok.Data;

/**
 * Created by chen on 2018/11/21.
 */
@Data
public abstract class Packet {

    private Byte version=1;


    public abstract Byte getCommond();

}

