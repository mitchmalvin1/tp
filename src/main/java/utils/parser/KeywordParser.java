package utils.parser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.Command;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

/**
 * Abstract class for parsing keyword-specific commands
 */
public abstract class KeywordParser {

    protected static final int FORMAT_HELP_WIDTH = 200;
    protected static final int FORMAT_HELP_LEFT_PAD = 0;
    protected static final int FORMAT_HELP_DESC_PAD = 10;

    /**
     * Wrapper around {@link Option} constructor to set option to accept multiple tokens (whitespace-separated
     * arguments). The arguments to this option should then be obtained using
     * {@link org.apache.commons.cli.CommandLine#getOptionValues(char)}.
     *
     * @param option      See {@link Option#Option(String, String, boolean, String)}
     * @param longOption  See {@link Option#Option(String, String, boolean, String)}
     * @param hasArg      See {@link Option#Option(String, String, boolean, String)}
     * @param description See {@link Option#Option(String, String, boolean, String)}
     * @param required    If Option is a required option
     * @return Configured Option
     */
    protected static Option buildMultipleTokenOption(String option, String longOption, boolean hasArg,
            String description,
            boolean required) {
        Option opt = new Option(option, longOption, hasArg, description);
        opt.setArgs(Option.UNLIMITED_VALUES);
        opt.setRequired(required);

        return opt;
    }

    @SuppressWarnings("unchecked") // Safe, CLI library just returns List instead of List<String>
    public Command parseTokens(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        try {
            return handleAction(action, flags);
        } catch (MissingArgumentException e) {
            String missingArgumentOption = e.getOption().getArgName();
            throw InvalidSyntaxException.buildMissingArgumentMessage(missingArgumentOption);
        } catch (MissingOptionException e) {
            List<String> opts = e.getMissingOptions();
            String missingOptions = opts.stream().map(str -> "-" + str).collect(Collectors.joining(", "));
            throw InvalidSyntaxException.buildMissingOptionMessage(missingOptions);
        } catch (ParseException e) {
            throw InvalidSyntaxException.buildGenericMessage();
        }
    }

    /**
     * Derived classes should override this method and handle the action corresponding
     *
     * @param action Action of the corresponding keyword
     * @param tokens Rest of the user input
     * @return Parsed command
     * @throws ParseException               Syntax errors in command
     * @throws UnrecognizedCommandException Unrecognized action for this keyword
     */
    protected abstract Command handleAction(String action, List<String> tokens)
            throws ParseException, InkaException;

    /**
     * Combines help messages for all actions into a single message
     *
     * @param keyword     Associated keyword
     * @param actionList  Actions
     * @param headerList  Descriptions of actions
     * @param optionsList All action Options for this keyword
     * @return Formatted help string
     */
    protected String formatHelpMessage(String keyword, String[] actionList, String[] headerList,
            Options[] optionsList) {
        assert optionsList.length == headerList.length;
        assert optionsList.length == actionList.length;

        HelpFormatter formatter = new HelpFormatter();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (int i = 0; i < optionsList.length; i++) {
            String syntax = "`" + keyword + " " + actionList[i] + "`";
            formatter.printHelp(printWriter, FORMAT_HELP_WIDTH, syntax, headerList[i], optionsList[i],
                    FORMAT_HELP_LEFT_PAD, FORMAT_HELP_DESC_PAD, "\n",
                    false);
        }

        // Fix for extra newlines at end
        return stringWriter.toString().trim() + System.lineSeparator();
    }
}