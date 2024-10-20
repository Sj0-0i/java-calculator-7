package calculator.service;

import calculator.dto.CalculatorRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterParser {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.*)\\\\n");
    private static final String DEFAULT_DELIMITERS = "[,:]";

    public static CalculatorRequest parseDelimiters(String input) {
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return CalculatorRequest.of(DEFAULT_DELIMITERS, input);
        }

        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (!matcher.find()) {
            System.out.println("잘못된 구분자 형식입니다.");
        }
        String customDelimiter = matcher.group(1);
        String calculation = input.substring(matcher.end());
        return CalculatorRequest.of(DEFAULT_DELIMITERS + "|" + Pattern.quote(customDelimiter), calculation);
    }
}