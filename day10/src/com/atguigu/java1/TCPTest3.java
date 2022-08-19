package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
 *
 * @author kasio
 * @create 2021-03-04 22:14
 */
public class TCPTest3 {

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void client() throws IOException {
        //1.
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
        //2.
        OutputStream os = socket.getOutputStream();
        //3.
        FileInputStream fis = new FileInputStream(new File("me.png"));
        //4
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        //关闭数据的输出
        socket.shutdownOutput();

        //5.接受来自于服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[20];
        int len2;
        while ((len2 = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len2);
        }
        System.out.println(baos.toString());

        //6.
        baos.close();
        is.close();
        fis.close();
        os.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        //1.
        ServerSocket ss = new ServerSocket(9090);
        //2.
        Socket socket = ss.accept();
        //3.
        InputStream is = socket.getInputStream();
        //4.
        FileOutputStream fos = new FileOutputStream(new File("me2.png"));
        //5.
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        System.out.println("图片接收成功！");

        //6.服务器端给与客户端反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好美女，照片我已收到，非常漂亮！".getBytes());

        //7.
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
