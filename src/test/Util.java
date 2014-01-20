package test;

public class Util {
	
	public static void print(String str) {
		if (str == null) print("");
		System.out.print(str);
	}

	public static void println(String str) {
		if (str == null) println("");
		System.out.println(str);
	}
}
