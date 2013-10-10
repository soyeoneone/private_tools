package cn.dreamslink.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameRegMatch {
	public static void main(String[] args) {
		String testName = "rwerws's. 'sjghjhfgjhghjghjgjghjgjhgjghjghjgjgjgjgj";
		//First name or last name must be 1 to 40 characters (valid characters are a-z and hyphen (-), dot (.), apostrophe ('), space).
		Pattern pattern = Pattern.compile("[a-zA-Z\\-\\.' ]+");
		Matcher matcher = pattern.matcher(testName);
		if (matcher.find()) {
			System.out.println(matcher.group(0));
		} else {
			System.out.println("fail");
		}
	}
}
