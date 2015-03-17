package biz.hahamo.dev.commandLine;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class CLIExample {

    private CLIExample() {

    }

    public static void main(final String... args) {

        final Options options = new Options();

        // example for OptionBuilder and required arguments
        Option timeout = OptionBuilder.hasArg().withLongOpt("timeout")
                .withDescription("Connection timeout in seconds. Optional.").create("t");
        timeout.setType(Number.class);
        options.addOption(timeout);

        //options.addOption("t", "timeout", true, "Connection timeout in seconds. Optional.");
        options.addOption("h", "host", true, "Host of the proxy server. Optional. Example: 'http://localhost'");
        options.addOption("p", "port", true, "Port of the proxy server. Optional.");

        final CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        String errorText = "";
        try {
            cmd = parser.parse(options, args);
        } catch (final ParseException e) {
            errorText += e.getMessage();
        }
        if (cmd.getArgList().size() < 1) {
            errorText = "ERROR: Parameter 'output path' is required." + (errorText.length() == 0 ? "" : "\n" + errorText);
        }
        if (cmd.getArgList().size() < 2) {
            errorText += "\nERROR: Parameter 'output filename' is required.";
        }
        if (errorText.length() != 0) {
            System.err.println(errorText);
            printHelp(options);
            System.exit(-1);
        }

        System.out.println("You want to save the CSV file to: " + cmd.getArgList().get(0));
        System.out.println("The name of the file your want to save is: " + cmd.getArgList().get(1));
        try {
            System.out.println("Connection timeout is set to: " + cmd.getParsedOptionValue("t") + " seconds.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cmd.getOptionValue('h') != null) {
            System.out.println("The proxy host is: " + cmd.getOptionValue("h"));
            System.out.println("The proxy port was set to: " + cmd.getOptionValue("p", "8080"));
        } else {
            System.out.println("No proxy configured.");
        }
        final String[] greedyValues = cmd.getArgs();
        if (greedyValues == null || greedyValues.length == 2) {
            System.out.println("No extra arguments provided");
        } else {
            System.out.println("Following extra parameters were provided:");
            for (int i = 2; i < greedyValues.length; i++) {
                System.out.println(greedyValues[i]);
            }
        }
        System.out.println("\nThanks for running this sample application.");

    }

    /**
     * @param options
     */
    private static void printHelp(final Options options) {

        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java -jar commandLine.jar <output path> <output filename>", options, true);
    }
}