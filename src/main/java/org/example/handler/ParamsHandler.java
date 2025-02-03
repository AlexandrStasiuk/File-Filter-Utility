package org.example.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.example.model.Params;
import org.example.validator.ArgsValidator;

import java.nio.file.Paths;
import java.util.List;

import static org.example.constant.OptionConstant.*;

@Slf4j
public class ParamsHandler{

    public static Params parse(String[] args) throws ParseException {
        CommandLine commandLine = parseArgs(args);
        ArgsValidator argsValidator = new ArgsValidator();

        List<String> filePaths = commandLine.getArgList();
        argsValidator.validateFiles(filePaths);
        log.info("Files transferred for filtering: {}", filePaths);

        String outputPath = commandLine.getOptionValue(O_OPT);
        if (outputPath != null) {
            argsValidator.validateOutputPath(outputPath);
        }else
            outputPath = "";

        String prefix = commandLine.getOptionValue(P_OPT);
        if (prefix != null) {
            argsValidator.validatePrefix(prefix);
        }else
            prefix = "";

        return new Params(
                outputPath,
                prefix,
                commandLine.hasOption(A_OPT),
                commandLine.hasOption(S_OPT),
                commandLine.hasOption(F_OPT),
                filePaths.stream().map(Paths::get).toList()
        );
    }

    private static CommandLine parseArgs(String[] args) throws ParseException {
        Options options = defineOptions();
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);
            if(commandLine.getArgList().isEmpty())
                throw new ParseException("You must add at least 1 file");
            return commandLine;
        } catch (ParseException e) {
            if(args.length > 0)
                log.error(e.getMessage());
            else
                new HelpFormatter().printHelp("app <options> file1.txt file2.txt ... fileN.txt", options);
            throw e;
        }
    }

    private static Options defineOptions(){
        return new Options()
                .addOption(new Option(O_OPT, true, "Set the path for the results"))
                .addOption(new Option(P_OPT, true, "Set a name prefix for output files"))
                .addOption(new Option(A_OPT, "Enable the add results to existing files mode"))
                .addOption(new Option(S_OPT, "Get abbreviated statistics"))
                .addOption(new Option(F_OPT, "Get full statistics"));
    }

}
