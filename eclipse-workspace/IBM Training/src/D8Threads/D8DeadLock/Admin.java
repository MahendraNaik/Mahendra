package D8Threads.D8DeadLock;

import java.util.List;

public class Admin implements Runnable,MinMax
{
	List<Integer> list;

	public Admin(List<Integer> list) 
	{
		this.list=list;
	}
	public void run() 
	{
		while(true)
		{
			try
			{
				produce();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void produce() throws InterruptedException
	{
		if(list.size()==max)
		{
			synchronized (list) 
			{
				list.wait();
			}
		}
		else 
		{
			list.add((int)Math.ceil(Math.random()*1000));
			System.out.println("Admin has produced item...");
			synchronized (list) 
			{
				//Thread.sleep(500);
				list.notify();
			}
		}
	}

}
