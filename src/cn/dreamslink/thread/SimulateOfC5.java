package cn.dreamslink.thread;

public class SimulateOfC5 {
    public static void main(String[] args) {
		StartRouting startrouting = new StartRouting();
		CheckRouting checkrouting = new CheckRouting();
		startrouting.start();
		checkrouting.start();
	}
}

class StartRouting extends Thread{
	public static boolean flag = false;
    @Override
    public void run() {
    	for(int i=0;i<666666666;i++);
    	flag = true;
    }
}
class CheckRouting extends Thread{
	@Override
	public void run() {
		while(true) {
			//asynchronized异步。当某个线程执行完后再做某事。
			if(StartRouting.flag){
				System.out.println("finished");
				break;
			}else{
				System.out.println("not finished");}
		}
	}
}