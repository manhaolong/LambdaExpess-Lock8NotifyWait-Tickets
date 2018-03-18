package myCode;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @Description: 第4种获得多线程的方式,线程池
 * @author zzyy
 * @date 2018年3月3日
 * 
 * SSS
 * Collections Arrays Executors
 */
public class ExecutorsDemo
{
	public static void main(String[] args)
	{
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null;
		try 
		{
			for (int i = 1; i <=15; i++) 
			{
				result = service.schedule(() -> {
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(10);
				}, 2, TimeUnit.SECONDS);
				System.out.println("  ********result: "+result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}		
	}

	public static void testThreadPool()
	{
		//ExecutorService service = Executors.newFixedThreadPool(5);//一池5线程
		//ExecutorService service = Executors.newSingleThreadExecutor();//一池1线程
		ExecutorService service = Executors.newCachedThreadPool();//一池N线程
		Future<Integer> result = null;
		
		try 
		{
			for (int i = 1; i <=15; i++) 
			{
				result = service.submit( () -> {
					Thread.sleep(400);
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(10);
				} );
				System.out.println("  ********result: "+result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
}
