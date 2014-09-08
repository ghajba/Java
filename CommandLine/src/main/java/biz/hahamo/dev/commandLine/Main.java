package biz.hahamo.dev.commandLine;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import com.martiansoftware.jsap.JSAPException;

public class Main {

    public static void main(final String... args) {

        System.out.println("Simple application to demonstrate command line argument parsers.\n");
        System.out.println("Calling JSAP with your arguments...\n");
        try {
            JSAPExample.main(args);
        } catch (JSAPException | IOException e) {
            System.err.println("Well, we ran into an unexpected Exception.");
            e.printStackTrace();
        }
        System.out.println("-----------------------");
        System.out.println("Calling JSAP with your arguments and XML configuration...\n");
        try {
            JSAPWithXML.main(args);
        } catch (JSAPException | IOException e) {
            System.err.println("Well, we ran into an unexpected Exception.");
            e.printStackTrace();
        }
        System.out.println("-----------------------");
        System.out.println("Calling CLI with your arguments...\n");
        try {
            CLIExample.main(args);
        } catch (ParseException e) {
            System.err.println("Well, we ran into an unexpected Exception.");
            e.printStackTrace();
        }
    }
}