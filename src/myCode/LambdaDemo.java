package myCode;

@FunctionalInterface
interface Foo
{
	//public void sayHello();
	//public void sayH886();
	
	public int add(int x,int y);
	
	default int div(int x,int y)
	{
		return x/y;
	}
	public static int sub(int x, int y)
	{
		return x - y;
	}
}


/**
 * 
 * @Description: Lambda Express  
 * @author zzyy
 * @date 2018年3月15日
 * 1	拷贝中括号+写死右箭头+落地大括号
 * 2	一个接口里面有且仅有一个方法的接口,才可以使用Lambda Express
 * 3	@FunctionalInterface
 * 4	default默认方法实现
 * 5	静态方法实现
 */
public class LambdaDemo
{
	public static void main(String[] args)
	{
//		Foo  foo = new Foo() {
//			@Override
//			public void sayHello()
//			{
//				System.out.println("******hello 1018");
//			}
//
//			@Override
//			public void sayH886()
//			{
//				
//			}
//		};
//		foo.sayHello();
		
//		Foo foo = () -> { System.out.println("******hello 1018 lambda");   };
//		foo.sayHello();
		
		Foo foo = (x,y) -> {  return x + y; };
		int result = foo.add(3, 15);
		System.out.println("*****result: "+result);
		
		result = foo.div(10, 2);
		System.out.println("*****result: "+result);
		
		Foo.sub(10, 3);
	}
}
