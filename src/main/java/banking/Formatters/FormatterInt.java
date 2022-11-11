package banking.Formatters;

public interface FormatterInt extends Formatter<Integer>{


    @Override
    String toFormat(Integer value);
}
