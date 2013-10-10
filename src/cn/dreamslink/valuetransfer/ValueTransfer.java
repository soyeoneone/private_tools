package cn.dreamslink.valuetransfer;

public class ValueTransfer {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("hello");
		changeValue(sb);
		System.out.println(sb);
	}
	public static void changeValue(StringBuffer str) {
		str = str.append("world");//the output above:helloword
		str = new StringBuffer("word");//the output above:hello
	}
}
