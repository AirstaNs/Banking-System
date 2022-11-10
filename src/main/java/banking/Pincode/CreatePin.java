package banking.Pincode;

import banking.Formatted.Formatted;
import banking.RandomGenerate.GenerateSecureRandomInt;

public class CreatePin  {
    private static final int MAX_VALUE_PIN = 10000;
    private static final int MIN_VALUE_PIN = 0;

    private Formatted formatPin;

    public CreatePin(Formatted format) {
        this.formatPin = format;
    }

    public String create() {
        int gen = GenerateSecureRandomInt.getInstance().generate(MAX_VALUE_PIN, MIN_VALUE_PIN);
        return formatPin.castToFormat(gen);
    }
}
