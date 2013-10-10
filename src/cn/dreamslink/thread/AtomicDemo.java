package cn.dreamslink.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
	private int normalInt; 
	public AtomicDemo(int normalInt) {
		this.normalInt = normalInt;
	}
	public void increase(){
		normalInt++;
	}
	public static void main(String[] args) {
		AtomicDemo atomicDemo = new AtomicDemo(0);
		long start1 = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			atomicDemo.increase();
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1-start1);
		
		AtomicInteger atomicInteger = new AtomicInteger(0);
		long start2 = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
		    atomicInteger.incrementAndGet();
		}
		long end2 = System.currentTimeMillis();
		System.out.println(end2-start2);
	}
}
