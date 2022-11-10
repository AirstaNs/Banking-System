package banking.Formatted;

public class FormattedCardNineChars implements Formatted{
    @Override
    public  String castToFormat(int value) {
        return String.format("%09d", value);
    }
}
