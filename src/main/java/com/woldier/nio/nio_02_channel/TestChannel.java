package com.woldier.nio.nio_02_channel;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class TestChannel {
    @Test
    public void test01() {
        try (FileOutputStream fileInputStream = new FileOutputStream(".\\test.txt");
             FileChannel channel = fileInputStream.getChannel())
        {
            ByteBuffer allocate = ByteBuffer.allocateDirect(1024);//创建一个直接字节缓冲
            allocate.put("hello channel".getBytes());//写入数据到buffer
            allocate.flip();//转换为读模式
            channel.write(allocate);//放入channel
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    @Test
    public void test02() {
        try (FileInputStream fileInputStream = new FileInputStream(".\\test.txt");
             FileChannel channel = fileInputStream.getChannel())
        {
            ByteBuffer allocate = ByteBuffer.allocate(1024);//这里不能使用直接字节缓冲,不知道是为什么
            channel.read(allocate);
            allocate.flip();//转换为读模式
            System.out.println(new String(allocate.array(), 0, allocate.remaining()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

   @Test
    public void test03(){
       try (FileInputStream fileOutputStream = new FileInputStream(".\\test.txt");
            FileChannel inChannel = fileOutputStream.getChannel();
            FileOutputStream fileInputStream = new FileOutputStream(".\\test-out.txt");
            FileChannel outChannel = fileInputStream.getChannel()
       )
       {
           ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//创建一个字节缓冲

           while(true){
               // 必须先清空缓冲然后再写入数据到缓冲区
               buffer.clear();
               // 开始读取一次数据
               int flag = inChannel.read(buffer);
               if(flag == -1){
                   break;
               }
               // 已经读取了数据 ，把缓冲区的模式切换成可读模式
               buffer.flip();
               // 把数据写出到
               outChannel.write(buffer);
           }
       } catch (Exception e) {
           System.out.println(e);
       }
   }

}
