package cn.dreamslink.thread;

public class UnlockThread {
	public static void main(String[] args) {
		UnlockClass unlockClass1 = new UnlockClass(1);
		UnlockClass unlockClass2 = new UnlockClass(2);
		unlockClass1.start();
		unlockClass2.start();
	}
    
}

class UnlockClass extends Thread{
	private int num;
	private Object lock = new Object();
	public UnlockClass(int num) {
		this.num = num;
	}
	@Override
	public void run() {
		//由于线程1和线程2 各自拥有的lock对象，即使锁上了lock，也只是锁自己独有的，所以两个线程仍然是可以同时进行的。
		synchronized(lock){
			for(int i=0;i<10;i++){
			      System.out.println(i + "===num=== "+num);
			      try {
					Thread.currentThread().sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
