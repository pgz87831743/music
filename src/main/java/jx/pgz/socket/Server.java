package jx.pgz.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {


        //阻塞模式

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        List<SocketChannel> list = new ArrayList<>();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        while (true) {
            log.debug("connecting");
            SocketChannel sc = ssc.accept();//阻塞方法，线程停止运行  非阻塞模式下accept 没有连接返回的就是null
            log.debug("connected...{}", sc);
            //     list.add(sc);


//            for (SocketChannel channel : list) {
//                System.out.println("before read");
//                channel.read(buffer);//阻塞方法，线程停止运行
//                readBuffer(buffer);
//                System.out.println("after read");
//            }

        }


    }

    public static void readBuffer(ByteBuffer buffer) {
        buffer.flip();
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s);
        buffer.clear();
    }
}
