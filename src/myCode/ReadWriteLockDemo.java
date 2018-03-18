package myCode;

import java.util.concurrent.locks.ReentrantReadWriteLock;


class MyQueue
{
	private Object obj;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	public void writeObj(Object obj)
	{
		rwLock.writeLock().lock();
		try 
		{
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void readObj()
	{
		rwLock.readLock().lock();
		try 
		{
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rwLock.readLock().unlock();
		}
	}	
}

/**
 * 
 * @Description: 一个线程写入,100个线程读取
 * @author zzyy
 * @date 2018年3月17日
 */
public class ReadWriteLockDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		MyQueue q = new MyQueue();
		
		new Thread(() -> {
			q.writeObj("ClassName1018");
		}, "writeThread").start();
		
		Thread.sleep(100);
		
		for (int i = 1; i <=100; i++) 
		{
			new Thread(() -> {
				q.readObj();
			},String.valueOf(i)).start();
		}
		
	}
}
