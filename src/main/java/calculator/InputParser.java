package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputParser {

    private static final String COMMON_DELIMITER = ",";
    private static final String DEFAULT_DELIMITER_PATTERN = "[,:]";
    private static final String CUSTOM_DELIMITER_START_POSITION = "//";
    private static final String CUSTOM_DELIMITER_END_POSITION = "\\n";
    private static final String SINGLE_INPUT_PATTERN = "-?\\d+";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;
    private static final int OFFSET = 2;

    private String delimiter;
    private String numbers;


    public List<Integer> parseInputToIntList(String input) {
        if (input.isEmpty()) return List.of(0);

        if (isSingleInput(input)) return convertToIntList(input);

        String standardInput = standardizeDelimiters(input);
        return convertToIntList(standardInput);
    }

    private String standardizeDelimiters(String input) {
        if (isStartingWithCustom(input)) {
            int delimiterEndIndex = findDelimiterEndIndex(input);
            setDelimiterAndNumbers(input, delimiterEndIndex);
            return replaceDelimiterToCommon(this.numbers, this.delimiter);
        }
        return replaceDelimiterToCommon(input, DEFAULT_DELIMITER_PATTERN);
    }

    private boolean isStartingWithCustom(String input) {
        return input.startsWith(CUSTOM_DELIMITER_START_POSITION);
    }

    private boolean isSingleInput(String input) {
        return input.matches(SINGLE_INPUT_PATTERN);
    }

    private int findDelimiterEndIndex(String input) {
        return input.indexOf(CUSTOM_DELIMITER_END_POSITION);
    }

    private void setDelimiterAndNumbers(String input, int delimiterEndIndex) {
        this.delimiter = input.substring(CUSTOM_DELIMITER_START_INDEX, delimiterEndIndex);
        this.numbers = input.substring(delimiterEndIndex + OFFSET);
    }

    private String replaceDelimiterToCommon(String input, String delimiter) {
        if (input.contains(",") || input.contains(":")) {
            return input.replaceAll(delimiter, COMMON_DELIMITER);
        }
        return input.replaceAll(Pattern.quote(delimiter), COMMON_DELIMITER);
    }

    private List<Integer> convertToIntList(String input) {
        return Arrays.stream(input.split(COMMON_DELIMITER))
                .map(Validator::validateIfNotNumber)
                .map(Validator::validateIfInputNegative)
                .toList();
    }
}
