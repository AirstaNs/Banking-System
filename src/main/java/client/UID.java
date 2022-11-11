package client;


import banking.Formatters.FormatterInt;
import banking.Formatters.FormatterUID;



public class UID { //FIXME -> default package

    private static int count = 0;
    private final int ID;
    private FormatterInt formatter = new FormatterUID();

    public UID() {
        ID =  increaseID();

    }

    private int increaseID() {
        return ++count;
    }



    public  int getID() {
        return ID;
    }
    public  String getFormattedID(){
        return formatter.toFormat(count);
    }
    public void setFormatter(FormatterUID formatter){
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return getFormattedID();
    }
}
//    public static void main(String[] args) {
//        Stream.generate(client.UID.Instance()::getID)
//                .limit(5)
//                .forEach(System.out::println);
//        client.UID u = new client.UID();
//        System.out.println(u.getID());
//    }
