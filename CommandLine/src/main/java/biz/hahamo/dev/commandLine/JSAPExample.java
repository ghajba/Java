package biz.hahamo.dev.commandLine;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.UnflaggedOption;

public class JSAPExample {
	public static void main(String... args) throws JSAPException {

		JSAP jsap = new JSAP();
        UnflaggedOption pathOption = new UnflaggedOption("output path").setStringParser(JSAP.STRING_PARSER)
                .setRequired(true);
        pathOption.setHelp("Output path where the CSV file should be saved. Mandatory.");
        UnflaggedOption filenameOption = new UnflaggedOption("output filename").setStringParser(JSAP.STRING_PARSER)
                .setRequired(true);
        filenameOption.setHelp("The name of the resulting CSV file. Mandatory.");

        FlaggedOption timeoutOption = new FlaggedOption("connection timeout").setStringParser(JSAP.INTEGER_PARSER)
                .setDefault("30").setShortFlag('t').setLongFlag("timeout");
        timeoutOption.setHelp("Connection timeout in seconds. Optional.");
        FlaggedOption proxyHostOption = new FlaggedOption("proxy host").setStringParser(JSAP.STRING_PARSER)
                .setShortFlag('h').setLongFlag("host");
        proxyHostOption.setHelp("Host of the proxy server. Optional. Example: 'http://localhost'");
        FlaggedOption proxyPortOption = new FlaggedOption("proxy port").setStringParser(JSAP.INTEGER_PARSER)
                .setDefault("8080").setShortFlag('p').setLongFlag("port");
        proxyPortOption.setHelp("Port of the proxy server. Optional.");

        UnflaggedOption greedyOption = new UnflaggedOption("other parameters").setStringParser(JSAP.STRING_PARSER) .setGreedy(true);
        greedyOption.setHelp("This is a greedy option to make life more complicated with swallowing any unflagged option provided but not expected.");

        jsap.registerParameter(pathOption);
        jsap.registerParameter(filenameOption);
        jsap.registerParameter(timeoutOption);
        jsap.registerParameter(proxyHostOption);
        jsap.registerParameter(proxyPortOption);
        jsap.registerParameter(greedyOption);

        JSAPResult configuration = jsap.parse(args);

        if (!configuration.success()) {
            for (java.util.Iterator errs = configuration.getErrorMessageIterator(); errs.hasNext(); ) {
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
	}
}