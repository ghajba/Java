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

        System.out.println("CLIExample is not yet implemented :( \n");
        Options options = new Options();

        Option outputPath = OptionBuilder.hasArg()
                .withDescription("Output path where the CSV file should be saved. Mandatory.").create("outputPath");
        outputPath.setRequired(true);

        Option outputFile = OptionBuilder.hasArg().withDescription("The name of the resulting CSV file. Mandatory.")
                .create("outputFile");

        outputFile.setRequired(true);

        // options.addOption(outputPath);
        // options.addOption(outputFile);

        options.addOption("t", "timeout", true, "Connection timeout in seconds. Optional.");
        options.addOption("h", "host", true, "Host of the proxy server. Optional. Example: 'http://localhost'");
        options.addOption("p", "port", true, "Port of the proxy server. Optional.");

        CommandLineParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            System.out.println(cmd.getArgList());

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter
                    .printHelp(
                            "java -jar commandLine.jar <output path> <output filename> [-t|--timeout connection timeout] [-h|--host proxy host] [-p|--port proxy port]",
                            options);
        }

    }
}