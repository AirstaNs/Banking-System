package banking.Formatted;


public class FormattedFourChar implements Formatted {
    @Override
    public  String castToFormat(int value) {
        return String.format("%04d", value);
    }
}
