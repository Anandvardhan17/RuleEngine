package com.anandvardhan.rule_engine.common_utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CommomUtilities {

	public List<String> splitString(String string) {

        // Step 1: Create a regex to match words and special characters
        String regex = "[a-zA-Z0-9']+|[><=\\(+\\)+]+|\\s+";

        // Step 2: Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        // Step 3: Store the results
        List<String> tokens = new ArrayList<>();

        // Step 4: Find matches and add them to the list
        while (matcher.find()) {
            String token = matcher.group().trim(); // Trim to remove extra spaces
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }

        return tokens;
    }

}
