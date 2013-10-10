package cn.dreamslink.test;

import java.math.BigDecimal;

public class MathRound {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "86.59900213623047";
		a = "99.115";
		BigDecimal bigDecimal = new BigDecimal(a);
		////第一个参数2是小数点后保留几位,
		System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
		
	}
//	我們的第一個反應是做四捨五入。Math類中的round方法不能設置保留幾位小數，我們只能象這樣（保留兩位）：
//	public double round(double value){
//	    return Math.round(value*100)/100.0;
//	}
//	非常不幸，上面的代碼並不能正常工作，給這個方法傳入4.015它將返回4.01而不是4.02，如我們在上面看到的
//	4.015*100=401.49999999999994
//	因此如果我們要做到精確的四捨五入，不能利用簡單類型做任何運算
//	java.text.DecimalFormat也不能解決這個問題：
//	System.out.println(new java.text.DecimalFormat("0.00").format(4.025));
//	輸出是4.02
//	原來我們如果需要精確計算，非要用String來夠造BigDecimal不可

}
