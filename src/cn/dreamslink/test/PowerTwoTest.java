package cn.dreamslink.test;

public class PowerTwoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		int num = 9999;
		while (num != 0) {
			num = num & (num-1);
			count++;
		}
		System.out.println(count);
		System.out.println(Integer.toBinaryString(9999));
	}
	
}
