package banking.client;


import banking.Formatters.FormatterInt;
import banking.Formatters.UIDFormat;

import java.util.Objects;

/**
 * A class that allows you to get a unique identifier for the user.
 */
public class UID {

    /**
     * Counts users, Responsible for the uniqueness of each user.
     */
    private static int count = 0;
    /**
     * Unique user ID, assigned when creating an object from {@link #count}
     */
    private final int ID;
    /**
     * Specifies the format for the string representation
     */
    private FormatterInt formatter = new UIDFormat();

    public UID() {
        ID = increaseID();

    }

    /**
     * The method initializes user counters when programs are enabled.
     * @param count number of users.
     */
    public static void setCount(int count) {
        UID.count = count;
    }

    public UID(int id) {
        ID = id;
    }

    private synchronized int increaseID() {
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
