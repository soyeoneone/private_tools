package cn.dreamslink.test;

import java.util.HashSet;
import java.util.Set;

public class CompentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set set = new HashSet();
		int a[] = new int[]{6161,8151,16071,7448,8668};
		int b[] = new int[]{4735,5585,8695,4405,4493,4833,6013,9123};
		for (int i = 0; i<a.length;i++) {
			for (int j=0;j<b.length;j++) {
				long sum = a[i] * b[j];
				System.out.println(a[i] + "*" + b[j] + "=" +sum);
				set.add(sum);
			}
		}
		System.out.println(set.size());
	}

}
