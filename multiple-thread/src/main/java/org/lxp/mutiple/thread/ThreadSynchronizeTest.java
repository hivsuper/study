package org.lxp.mutiple.thread;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 三种方法实现生产者/消费者
 * @author Super.Li
 * @date Jul 4, 2017
 */
public class ThreadSynchronizeTest {

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumerViaBlockingQueue();
        producerConsumer.test();
    }
}

abstract class ProducerConsumer {
    protected int capacity = 10;
    protected int element = 0;

    protected abstract void produce() throws InterruptedException;

    protected abstract void consume() throws InterruptedException;

    public void test() {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producer.start();
        consumer.start();
    }
}

/**
 * 方法一：ReentrantLock结合Condition
 */
class ProducerConsumerViaReentrantLock extends ProducerConsumer {
    private Stack<Integer> stack = new Stack<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    @Override
    protected void produce() throws InterruptedException {
        try {
            lock.lock();
            if (stack.size() == capacity) {
                notFull.await();
            }
            ++element;
            System.out.println(Thread.currentThread().getId() + " produce " + element);
            stack.push(element);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void consume() throws InterruptedException {
        try {
            lock.lock();
            if (stack.isEmpty()) {
                notEmpty.await();
            }
            int element = stack.pop();
            System.out.println(Thread.currentThread().getId() + " consume " + element);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 方法二：synchronized结合wait/notify/notifyAll
 */
class ProducerConsumerViaObjectLock extends ProducerConsumer {
    private Stack<Integer> stack = new Stack<>();
    private Object lock = new Object();

    @Override
    protected void produce() throws InterruptedException {
        /**
         * 1. lock为监视器<br/>
         * 2. wait/notify/notifyAll方法必须在synchronized块内调用<br/>
         * 3. 调用wait/notify/notifyAll方法但不持有监视器的使用权将会抛出java.lang.
         * IllegalMonitorStateException<br/>
         */
        synchronized (lock) {
            if (stack.size() == capacity) {
                lock.wait();
            }
            ++element;
            System.out.println(Thread.currentThread().getId() + " produce " + element);
            stack.push(element);
            lock.notifyAll();
        }
    }

    @Override
    protected void consume() throws InterruptedException {
        synchronized (lock) {
            if (stack.isEmpty()) {
                lock.wait();
            }
            int element = stack.pop();
            System.out.println(Thread.currentThread().getId() + " consume " + element);
            lock.notifyAll();
        }
    }
}

/**
 * 方法三：BlockingQueue
 */
class ProducerConsumerViaBlockingQueue extends ProducerConsumer {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);

    @Override
    protected void produce() throws InterruptedException {
        ++element;
        System.out.println(Thread.currentThread().getId() + " produce " + element);
        queue.put(element);
    }

    @Override
    protected void consume() throws InterruptedException {
        int element = queue.take();
        System.out.println(Thread.currentThread().getId() + " consume " + element);
    }
}