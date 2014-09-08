package biz.hahamo.dev.commandLine;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLIExample {

    public static void main(final String... args) throws ParseException {

        System.out.println("CLIExample is not yet implemented :( \n");
        Options options = new Options();

        Option outputPath = OptionBuilder.hasArg()
                .withDescription("Output path where the CSV file should be saved. Mandatory.").create("outputPath");
        outputPath.setRequired(true);

        Option outputFile = OptionBuilder.hasArg().withDescription("The name of the resulting CSV file. Mandatory.")
                .create("outputFile");

        outputFile.setRequired(true);

        options.addOption(outputPath);
        options.addOption(outputFile);

        options.addOption("t", "timeout", true, "Connection timeout in seconds. Optional.");

        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java -jar commandLine.jar", options);
    }

}