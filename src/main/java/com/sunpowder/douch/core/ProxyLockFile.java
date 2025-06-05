package com.sunpowder.douch.core;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.io.RandomAccessFile;

public class ProxyLockFile {
    private FileLock lock;
    private FileChannel channel;
    public boolean lock(File file) throws IOException {
        channel = new RandomAccessFile(file, "rw").getChannel();
        lock = channel.tryLock();
        return lock != null;
    }
    public void release() throws IOException {
        if (lock != null) lock.release();
        if (channel != null) channel.close();
    }
}
