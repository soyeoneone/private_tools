package cn.dreamslink.thread;

public class Mutex {
	public static void main(String[] args) {
		ShareObject shareobject = new ShareObject();
		ChangeShareObject[] changeobject = new ChangeShareObject[5];
		for(int i=0;i<changeobject.length;i++) {
			changeobject[i] = new ChangeShareObject(shareobject,i);
		}
		for(int i=0;i<changeobject.length;i++) {
			changeobject[i].start();
		}

	}
}

class ChangeShareObject extends Thread {
	private ShareObject shareObject;
	private int num;
	public ChangeShareObject(ShareObject shareObject,int i) {
		this.shareObject = shareObject;
		this.num = i;
	}
	@Override
	public void run() {
		for(int i = 0;i < 10;i++){
			try {
				shareObject.setNumber(num);
				Thread.currentThread().sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class ShareObject {
	private int number;
	//加上synchronized后，before和after成对出现。也就是执行完这个方法后，才会被别的线程执行。
	//若不加锁，可能是先出现好几个before再出现after，也就是在等待的时候里，其他的线程同时访问到这个方法中执行了。
	public synchronized void setNumber(int num) throws InterruptedException {
		System.out.println("before  " + number);
		this.number = num;
		Thread.currentThread().sleep(50);
		System.out.println("after   " + number);
	}
}
