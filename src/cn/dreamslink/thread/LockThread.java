package cn.dreamslink.thread;

public class LockThread {
	public static void main(String[] args) {
		Object lock = new Object();
		LockClass lockClass1 = new LockClass(1,lock);
		LockClass lockClass2 = new LockClass(2,lock);
		lockClass1.start();
		lockClass2.start();
	}
    
}

class LockClass extends Thread{
	private int num;
	private Object lock;
	public LockClass(int num,Object object) {
		this.num = num;
		this.lock = object;
	}
	@Override
	public void run() {
		//lock是两个线程共享的对象，如果某一个资源占用了lock，其他的线程就不能访问了。
		synchronized(lock){
			for(int i=0;i<10;i++){
			      System.out.println(i + "===num=== "+num);
			      try {
					Thread.currentThread().sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
