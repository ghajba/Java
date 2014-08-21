package biz.hahamo.dev.commandLine;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

import java.io.IOException;

/**
 * Created by GHajba on 2014.08.21..
 */
public class JSAPWithXML {
    public static void main(String... args) throws IOException, JSAPException {
        JSAP jsap = new JSAP(JSAPWithXML.class.getResource("/configuration.xml"));

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

        System.out.println("You want to save the CSV file to: " + configuration.getString("output path"));
        System.out.println("The name of the file your want to save is: " + configuration.getString("output filename"));
        System.out.println("Connection timeout is set to: " + configuration.getInt("connection timeout") + " seconds.");
        if(configuration.getString("proxy host") != null) {
            System.out.println("The proxy host is: " + configuration.getString("proxy host"));
            System.out.println("The proxy port was set to: " + configuration.getInt("proxy port"));
        }
        else {
            System.out.println("No proxy configured.");
        }
        String[] greedyValues = configuration.getStringArray("greedy");
        if(greedyValues == null || greedyValues.length == 0) {
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
