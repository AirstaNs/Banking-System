package banking.Formatters;


public class PinFormat implements FormatterInt {

    @Override
    public String toFormat(Integer value) {
        return String.format("%04d", value);
    }
}
