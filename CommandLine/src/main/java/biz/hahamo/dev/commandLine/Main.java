package biz.hahamo.dev.commandLine;

public class Main {
	public static void main(String... args) {
		System.out.println("Simple application to demonstrate command line argument parsers.\n");
		System.out.println("Calling JSAP with your arguments...\n");
		JSAPExample.main(args);
		System.out.println("Calling CLI with your arguments...\n");
		CLIExample.main(args);
	}
}