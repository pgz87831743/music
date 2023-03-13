package jx.pgz.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {


    public static void main(String[] args) {


        try (FileChannel channel = new FileInputStream("src/main/resources/dd.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true){
                int read = channel.read(buffer);
                if (read==-1){
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.println((char)buffer.get());
                }
                buffer.clear();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
