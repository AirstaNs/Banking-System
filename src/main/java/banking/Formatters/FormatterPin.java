package banking.Formatters;


public class FormatterPin implements FormatterInt {

    @Override
    public String toFormat(Integer value) {
        return String.format("%04d", value);
    }
}
