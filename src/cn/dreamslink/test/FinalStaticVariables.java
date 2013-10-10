package cn.dreamslink.test;

import java.util.ArrayList;
import java.util.List;

public class FinalStaticVariables {
	//add or remove keyword "static",there will be different outputs.
	public static final List list = new ArrayList();
	public static void main(String[] args) {
		FinalStaticVariables finalVariables1 = new FinalStaticVariables();
		finalVariables1.list.add(0, "aa");
		FinalStaticVariables finalVariables2 = new FinalStaticVariables();
		finalVariables2.list.add(0, "bb");
		System.out.println(finalVariables1.list.get(0));
		System.out.println(finalVariables2.list.get(0));
	}
}
