package banking.Formatters;

public class FormatterUID implements Formatter {
    @Override
    public String castToFormat(int value) {
        return String.format("%09d", value);
    }
}
