package banking.Formatters;

public class FormatterUID implements FormatterInt {

    @Override
    public String toFormat(Integer value) {
        return String.format("%09d", value);
    }
}
