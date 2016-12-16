package de.binetsky.myHandler;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abinetsky on 12/16/16.
 */
public class Target {
    public static final Pattern PATTERN = Pattern.compile
            ("Накоплено\\u0020([0-9]?\\D?[0-9]*)\\D{2}евро\\u0020История.{5}Списано\\u0020([0-9]?\\D?[0-9]*)\\D{2}евро.*", Pattern.DOTALL);


    private String name;
    private BigDecimal value;

    private Target(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public Target (String name, String text) {
        this.name = name;
        final Matcher matcher = PATTERN.matcher(text);
        if(matcher.matches() && matcher.groupCount()==3) {
            final Long total = Long.valueOf(matcher.group(1));
            final BigDecimal alreadySpent = BigDecimal.valueOf(Long.valueOf(matcher.group(2)));
            this.value = BigDecimal.valueOf(total).subtract(alreadySpent);
        } else {
            throw new IllegalArgumentException("Problem bei parse of string: " + text);
        }

    }

    public String name() {
        return name;
    }

    public BigDecimal value() {
        return value;
    }
}
