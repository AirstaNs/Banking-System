package banking.RandomGenerate;

import java.security.SecureRandom;

public class GenerateSecureRandomInt implements GenerateIntValue {

    private static GenerateSecureRandomInt Instance = null;
    private static final SecureRandom random = new SecureRandom();

    private GenerateSecureRandomInt() {

    }

    @Override
    public int generate(int max_value, int minValue) {
        return random.nextInt(max_value - minValue) + minValue;
    }

    public static GenerateSecureRandomInt getInstance() {
        if (Instance == null) Instance = new GenerateSecureRandomInt();
        return Instance;
    }
}
