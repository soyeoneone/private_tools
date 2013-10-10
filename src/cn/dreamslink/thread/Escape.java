package cn.dreamslink.thread;

public class Escape {
	public static void main(String args[]) {
		int n = 41;
		final Unsafe leaks[] = new Unsafe[1];
		new Thread(new Runnable() {
			public void run() {
				Thread.yield();
				new Unsafe(leaks);
			}
		}).start();
		//给leaks[0]赋值的线程正在执行（就是unsafe构造函数中），
		while (leaks[0] == null)
			Thread.currentThread().yield();
		if (leaks[0].n != n)
			System.out.println("break!");
		if (leaks[0].n == n)
			System.out.println("not break!");
		System.exit(0);
	}
}

class Unsafe {
	public final int n;
	public final long range = Long.MAX_VALUE;

	public  Unsafe(Unsafe[] leak) {
		leak[0] = this;
		for (long i = 0; i < range; i++);
		this.n = 41;
	}
}
