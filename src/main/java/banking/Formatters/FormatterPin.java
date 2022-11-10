package banking.Formatters;


public class FormatterPin implements Formatter {
    @Override
    public  String castToFormat(int value) {
        return String.format("%04d", value);
    }
}
