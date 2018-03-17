package Demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber {
	
	//线程 操作 资源
	//高耦合 低内聚
	//1.判断 2.干活 3.通知
	//两个线程依次打印 12A34B56C ...... 5152Z(26英文 52字符)
	public static void main(String[] args) throws Exception {
		Number num = new Number();
		
		//AA线程抢占打印数字的资源
		new Thread(()->{
			try {
					num.printNumber();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"AA").start();
		
		Thread.sleep(100);
		//BB想成抢占字母资源
		new Thread(()->{
			try {
					num.printLetter();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
		
	}

}


//资源类
class Number{
	int  a = 0;
	char b = 'A'-1;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public  void printNumber() {
		lock.lock();
		try {
			//1.判断
			while(a < 52) {
				//2.干活
				System.out.print(++a);
				System.out.print(++a);
				//3.通知
				condition.signal();
				if(a==52) {
					return;
				}
				condition.await();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void printLetter() {
		
		lock.lock();
		try {
			//1.判断
			while(b < 'Z') {
				//2.干活
				b+=1;
				System.out.print(b);
				if(b =='Z') {
					return ;
				}
				//3.通知
				condition.signal();
				condition.await(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
}
