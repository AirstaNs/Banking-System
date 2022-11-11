package banking.Card;

import banking.Formatters.FormatterInt;
import banking.RandomGenerate.GenerateSecureRandomInt;

import java.util.StringJoiner;

public class Pin {
    private static final int MAX_VALUE_PIN = 10000;
    private static final int MIN_VALUE_PIN = 0;
    private FormatterInt formatPin;
    private final String pin;

    protected Pin(FormatterInt format) {
        this.formatPin = format;
        this.pin = create();
    }

    private String create() {
        int gen = GenerateSecureRandomInt.getInstance().generate(MAX_VALUE_PIN, MIN_VALUE_PIN);
        return formatPin.toFormat(gen);
    }

    public void setFormatPin(FormatterInt formatPin) {
        this.formatPin = formatPin;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return String.format("Your card PIN:%n%s",pin);
    }
}
