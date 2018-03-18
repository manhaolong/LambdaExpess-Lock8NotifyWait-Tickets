package myCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException
	{
		lock.lock();
		try 
		{
			while(number != 0)
			{
				condition.await();//this.wait();
			}
			//2 干活
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//3 通知
			condition.signalAll();//this.notifyAll();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws InterruptedException
	{
		lock.lock();
		try 
		{
			while(number == 0)
			{
				condition.await();//this.wait();
			}
			//2 干活
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//3 通知
			condition.signalAll();//this.notifyAll();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
		
	
	/*public synchronized void increment() throws InterruptedException
	{
		//1 判断
		//if(number != 0)
		while(number != 0)
		{
			this.wait();// A......C......
		}
		//2 干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3 通知
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException
	{
		//1 判断
		//if(number == 0)
		while(number == 0)
		{
			this.wait();
		}
		//2 干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3 通知
		this.notifyAll();
	}	*/
	
}


/**
 * 
 * @Description: 
 * 现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮，变量初始值为零。
 * @author zzyy
 * @date 2018年3月15日
 * 1 多线程编写套路------上
 * 		1.1	线程		操作(实例方法)		资源类
 * 		1.2  高内聚  低耦合
 * 
 * 2 多线程编写套路------下
 * 		2.1 判断
 * 		2.2 干活
 * 		2.3 通知
 */
public class NotifyWaitDemo
{
	public static void main(String[] args)
	{
		ShareData sd = new ShareData();
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					Thread.sleep(200);
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					Thread.sleep(300);
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();		
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					Thread.sleep(400);
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					Thread.sleep(500);
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "DD").start();			
		
		
	}
}





