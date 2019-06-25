package org.raven.serializer.withMsgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.TemplateRegistry;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2018/2/12 11:20:00
 */
public class MessagePackFactory {

    /**
     * @return
     */
    public static MessagePack create() {

        TemplateRegistry registry = new CustomTemplateRegistry();
        MessagePack messagePack = new CustomMessagePack(registry);
        return messagePack;
    }

}