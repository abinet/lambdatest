package de.binetsky.myHandler;

import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.assertTrue;

/**
 * Created by abinetsky on 12/14/16.
 */
public class HandlerTest {
    @Test
    public void testAll() {
        new Handler().handleRequest(null, null);
    }

    @Test
    public void testPattern() {
        final Matcher matcher = Target.PATTERN.matcher("Накоплено 6\u00A0316 \u00A0евро История \u00A0\u00A0\u00A0 Списано 2\u00A0055 \u00A0евро История     100% Перенести     Списать     Изменить");
        assertTrue(matcher.matches());
    }

}