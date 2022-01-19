package com.fu.core.dlock.entity;

/**
 * 锁实例
 *
 */
public class Lock {

    /**
     * 锁的路径/标识
     */
    private final String name;

    /**
     * 获取的锁对象
     */
    private Object lock;

    /**
     * 是否获得锁成功
     */
    private boolean success;


    public Lock(String name) {
        this.name = name;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public boolean isSuccess() {
        return success;
    }

    public void success() {
        this.success = true;
    }

    public String getName() {
        return name;
    }
}
