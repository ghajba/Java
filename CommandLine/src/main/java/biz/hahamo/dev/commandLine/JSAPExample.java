package biz.hahamo.dev.commandLine;

import java.io.IOException;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.UnflaggedOption;

public class JSAPExample {

    public static void main(final String... args) throws JSAPException, IOException {

        JSAP jsap = new JSAP();
        UnflaggedOption pathOption = new UnflaggedOption("output path").setRequired(true);
        pathOption.setHelp("Output path where the CSV file should be saved. Mandatory.");
        UnflaggedOption filenameOption = new UnflaggedOption("output filename").setRequired(true);
        filenameOption.setHelp("The name of the resulting CSV file. Mandatory.");

        FlaggedOption timeoutOption = new FlaggedOption("connection timeout").setStringParser(JSAP.INTEGER_PARSER)
                .setDefault("30").setShortFlag('t').setLongFlag("timeout");
        timeoutOption.setHelp("Connection timeout in seconds. Optional.");
        FlaggedOption proxyHostOption = new FlaggedOption("proxy host").setShortFlag('h').setLongFlag("host");
        proxyHostOption.setHelp("Host of the proxy server. Optional. Example: 'http://localhost'");
        FlaggedOption proxyPortOption = new FlaggedOption("proxy port").setStringParser(JSAP.INTEGER_PARSER)
                .setDefault("8080").setShortFlag('p').setLongFlag("port");
        proxyPortOption.setHelp("Port of the proxy server. Optional.");

        UnflaggedOption greedyOption = new UnflaggedOption("other parameters").setStringParser(JSAP.STRING_PARSER)
                .setGreedy(true).setRequired(false);
        greedyOption.setHelp("This is a greedy option to make life more complicated with swallowing any unflagged " +
                "option provided but not expected.");

        jsap.registerParameter(pathOption);
        jsap.registerParameter(filenameOption);
        jsap.registerParameter(timeoutOption);
        jsap.registerParameter(proxyHostOption);
        jsap.registerParameter(proxyPortOption);
        jsap.registerParameter(greedyOption);

        JSAPResult configuration = jsap.parse(args);

        if (!configuration.success()) {
            for (java.util.Iterator errs = configuration.getErrorMessageIterator(); errs.hasNext();) {
                System.err.println("ERROR: " + errs.next());
            }
            System.err.println();
            System.err.println("Usage:");
            System.err.println("\tjava -jar commandLine.jar ");
            System.err.println("\t\t" + jsap.getUsage());
            System.err.println();
            System.err.println(jsap.getHelp());
            return;
        }

        System.out.println("You want to save the CSV file to: " + configuration.getString("output path"));
        System.out.println("The name of the file your want to save is: " + configuration.getString("output filename"));
        System.out.println("Connection timeout is set to: " + configuration.getInt("connection timeout") + " seconds.");
        if (configuration.getString("proxy host") != null) {
            System.out.println("The proxy host is: " + configuration.getString("proxy host"));
            System.out.println("The proxy port was set to: " + configuration.getInt("proxy port"));
        }
        else {
            System.out.println("No proxy configured.");
        }
        String[] greedyValues = configuration.getStringArray("greedy");
        if (greedyValues == null || greedyValues.length == 0) {
            System.out.println("No extra arguments provided");
        }
        else {
            System.out.println("Following extra parameters were provided:");
            for (String s : greedyValues) {
                System.out.println(s);
            }
        }
        System.out.println("\nThanks for running this sample application.");
    }
}