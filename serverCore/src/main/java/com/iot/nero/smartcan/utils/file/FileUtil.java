package com.iot.nero.smartcan.utils.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:59
 */
public class FileUtil {

    private FileChannel inChannel = null;
    private final ByteBuffer buf = ByteBuffer.allocate(1024);

    public void doWrite(RandomAccessFile file,byte[] data) throws IOException {
        inChannel = file.getChannel();
        buf.clear();
        buf.put(data);

        buf.flip();

        while (buf.hasRemaining())
            inChannel.write(buf);

        inChannel.close();
    }

    public byte[] doRead(RandomAccessFile file) throws IOException {
        inChannel = file.getChannel();

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining())

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        file.close();
        return buf.array();
    }

    public void doCopy(RandomAccessFile source,RandomAccessFile target) throws IOException {
        inChannel = source.getChannel();
        FileChannel outChannel = target.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
    }

}
