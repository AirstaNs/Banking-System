package banking.Pincode.FormattedPin;


public class FormattedPinFourChar implements FormattedPin {
    @Override
    public  String castToNumbers(int value) {
        return String.format("%04d", value);
    }
}
