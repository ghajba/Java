package biz.hahamo.dev.commandLine;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class CLIExample {

    private CLIExample() {

    }

    public static void main(final String... args) {

        System.out.println("CLIExample is not yet fully implemented :( \n");
        Options options = new Options();

        // example for OptionBuilder and required arguments
        // Option outputPath = OptionBuilder.hasArg()
        // .withDescription("Output path where the CSV file should be saved. Mandatory.").create("outputPath");
        // outputPath.setRequired(true);

        options.addOption("t", "timeout", true, "Connection timeout in seconds. Optional.");
        options.addOption("h", "host", true, "Host of the proxy server. Optional. Example: 'http://localhost'");
        options.addOption("p", "port", true, "Port of the proxy server. Optional.");

        CommandLineParser parser = new BasicParser();
        try {
            String errorText = "";
            CommandLine cmd = parser.parse(options, args);
            if (cmd.getArgList().size() < 1) {
                errorText = "ERROR: Parameter 'output path' is required.";
            }
            if (cmd.getArgList().size() < 2) {
                errorText = "ERROR: Parameter 'output filename' is required.";
            }
            if (errorText.length() != 0) {
                throw new ParseException(errorText);
            }

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            printHelp(options);
            System.exit(-1);
        }

    }

    /**
     * @param options
     */
    private static void printHelp(final Options options) {

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter
                .printHelp(
                        "java -jar commandLine.jar <output path> <output filename> "
                                + "[(-t|--timeout) <connection timeout>] [(-h|--host) <proxy host>] "
                                + "[(-p|--port) <proxy port>] "
                                + "[other parameters1 other parameters2 ... other parametersN]",
                        options);
    }
}