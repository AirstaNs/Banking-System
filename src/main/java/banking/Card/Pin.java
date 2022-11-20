package banking.Card;

import banking.Formatters.FormatterInt;
import banking.RandomGenerate.GenerateSecureRandomInt;

public class Pin {
    private static final int MAX_VALUE_PIN = 10000;
    private static final int MIN_VALUE_PIN = 0;
    private FormatterInt formatPin;
    private final String pin;

    protected Pin(FormatterInt format) {
        this.formatPin = format;
        this.pin = create();
    }
    protected Pin(String pin) {
        this.pin = pin;
    }

    private String create() {
        int gen = GenerateSecureRandomInt.getInstance()
                                         .generate(MAX_VALUE_PIN, MIN_VALUE_PIN);
        return formatPin.toFormat(gen);
    }

    public void setFormatPin(FormatterInt formatPin) {
        this.formatPin = formatPin;
    }

    @Override
    public String toString() {
        return pin;
    }

    public void printConsole() {
        System.out.printf("Your card PIN:%n%s", pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;
        if (this.hashCode() != o.hashCode()) return false;

        Pin pin1 = (Pin) o;

        return pin.equals(pin1.pin);
    }

    @Override
    public int hashCode() {
        return pin.hashCode();
    }
}