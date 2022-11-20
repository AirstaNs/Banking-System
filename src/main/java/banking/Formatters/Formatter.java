package banking.Formatters;

public interface Formatter<T> {
    //TODO FACTORY
    String toFormat(T value);
  //  boolean isFormatted(T value);
}


