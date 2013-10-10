package cn.dreamslink.thread;

public class RaceConditon {
	public static int winner;
    public static void main(String[] args) {
		Thread runner1 = new Runner(1);
		Thread runner2 = new Runner(2);
		runner1.start();
		runner2.start();
		try {
			Thread.currentThread().sleep(100);
			System.out.println(winner);
			System.exit(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Runner extends Thread{
	public int num;
	public Runner(int num) {
		this.num = num;
	}
    public void run() {
    	while(true){
    	RaceConditon.winner = num;
    	System.out.println("num=="+num);
    	}
	}
}
