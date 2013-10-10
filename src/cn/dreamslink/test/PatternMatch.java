package cn.dreamslink.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatch {
	public static void main(String[] args) {
		String javascript = "this.createFlight(\"0\", \"14\", \"9\", \"WWWWM4FF3E\", \"WWWWM4FF3E\", \"13947.0\", \"\", \"\");this.createFlight(\"1\", \"14\", \"9\", \"WWWWM4FF3E\", \"WWWWM4FF3E\", \"13947.0\", \"\", \"\");this.createFlight(\"2\", \"14\", \"9\", \"WWWWM4FF3E\", \"WWWWM4FF3E\", \"13947.0\", \"WWWWM3RC2V\", convertAssociatedFlights('0'));";
		String matchID = "this.createFlight\\(\"(\\d+)\", \"(\\d+)\", \"(\\d+)\", \"(\\S+)\", \"\\S+\", \"" + "(\\d+\\.\\d+)" + ".*?\\);";
		Pattern pattern = Pattern.compile(matchID);
		Matcher matcher = pattern.matcher(javascript);
		System.out.println("javascript===" + javascript);
		System.out.println("matchId======" + matchID);
		while (matcher.find()) {
			System.out.println(matcher.group(0));
		}
	}
}
