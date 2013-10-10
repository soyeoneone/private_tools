package cn.dreamslink.test;


public class InnerClass {

	
	protected static class TestInner {
		public static int value;
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		private int key;
		
	}
}
