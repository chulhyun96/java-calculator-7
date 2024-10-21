package calculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InputParser {

    private static final String COMMON_DELIMITER = ",";
    private static final String DEFAULT_DELIMITER_PATTERN = "[,:]";
    private static final String CUSTOM_DELIMITER_START_POSITION = "//";
    private static final String CUSTOM_DELIMITER_END_POSITION = "\\n";
    private static final String SINGLE_INPUT_PATTERN = "-?\\d+";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;
    private static final int OFFSET = 2;


    public List<Integer> parseInputToIntList(String input) {
        if (input.isEmpty()) return List.of(0);

        if (isSingleInput(input)) return convertToIntList(input);

        String standardInput = standardizeDelimiters(input);
        return convertToIntList(standardInput);
    }

    private String standardizeDelimiters(String input) {
        if (isStartingWithCustom(input)) {
            int delimiterEndIndex = findDelimiterEndIndex(input);
            Map<String, String> substring = getDelimiterAndNumbers(input, delimiterEndIndex);
            return replaceDelimiterToCommon(substring.get("numbers"), substring.get("delimiter"));
        }
        return replaceDelimiterToCommon(input, DEFAULT_DELIMITER_PATTERN);
    }

    private Map<String,String> getDelimiterAndNumbers(String input, int delimiterEndIndex) {
        HashMap<String, String> map = new HashMap<>();
        String delimiter = input.substring(CUSTOM_DELIMITER_START_INDEX, delimiterEndIndex);
        String numbersPart = input.substring(delimiterEndIndex + OFFSET);
        map.put("delimiter", delimiter);
        map.put("numbers", numbersPart);
        return map;
    }

    private List<Integer> convertToIntList(String input) {
        return Arrays.stream(input.split(COMMON_DELIMITER)) // 1,2,3 -> "1","2","3"
                .map(Validator::validateIfNotNumber)
                .map(Validator::validateIfInputNegative)
                .toList();
    }

    private String replaceDelimiterToCommon(String input, String delimiter) {
        if (input.contains(",") || input.contains(":")) {
            return input.replaceAll(delimiter, COMMON_DELIMITER);
        }
        return input.replaceAll(Pattern.quote(delimiter), COMMON_DELIMITER);
    }

    private boolean isSingleInput(String input) {
        return input.matches(SINGLE_INPUT_PATTERN);
    }

    private boolean isStartingWithCustom(String input) {
        return input.startsWith(CUSTOM_DELIMITER_START_POSITION);
    }

    private int findDelimiterEndIndex(String input) {
        return input.indexOf(CUSTOM_DELIMITER_END_POSITION);
    }
}
