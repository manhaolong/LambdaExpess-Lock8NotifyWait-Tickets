package myCode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Ticket//资源类  类  = 实例变量+实例方法
{
	private int number = 30;
	
	//Lock implementations provide more extensive locking operations
	//than can be obtained using synchronized methods and statements. 
	private Lock lock = new ReentrantLock();	//List list = new ArrayList();
	
	public void sale()
	{
		lock.lock();
		try
		{
			if(number > 0)
			{
				System.out.println(Thread.currentThread().getName()+"\t 卖出第:"+(number--)+"\t 还剩下: "+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 第一个case
 * @Description: 卖票程序复习线程知识 ,三个售票员	卖出	30张票
 * @author zzyy
 * @date 2018年3月15日
 * 1 多线程编写套路------上
 * 		1.1	线程		操作(实例方法)		资源类
 * 		1.2  高内聚  低耦合
 */
public class SaleTicket
{
	public static void main(String[] args)
	{
		Ticket ticket = new Ticket();
		
		//Thread(Runnable target, String name) 	Allocates a new Thread object.		
		
		new Thread(() -> { for (int i = 1; i <=40; i++) 	ticket.sale(); }, "AA").start();
		new Thread(() -> { for (int i = 1; i <=40; i++) 	ticket.sale(); }, "BB").start();
		new Thread(() -> { for (int i = 1; i <=40; i++) 	ticket.sale(); }, "CC").start();
		
		new Thread(() -> {  for (int i = 1; i <=40; i++) ticket.sale();}   , "your thread name") .start();
		
		
		/*new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "AA").start();
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "CC").start();		*/
		
	}
}

//1 class MyThread implements Runnable

//2 匿名内部类

//3 lambda Express


