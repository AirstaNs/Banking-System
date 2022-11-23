package banking.client;


import banking.Formatters.FormatterInt;
import banking.Formatters.UIDFormat;

import java.util.Objects;


public class UID {

    private static int count = 0;
    private final int ID;
    private FormatterInt formatter = new UIDFormat();

    public UID() {
        ID = increaseID();

    }

    public static void setCount(int count) {
        UID.count = count;
    }

    public UID(int id) {
        ID = id;

    }

    private int increaseID() {
        return ++count;
    }


    public int getID() {
        return ID;
    }
    public  String getFormattedID(){
        return formatter.toFormat(count);
    }
    public void setFormatter(FormatterInt formatter){
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return getFormattedID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UID)) return false;
        UID uid = (UID) o;
        return ID == uid.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
