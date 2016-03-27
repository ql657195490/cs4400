import java.util.ArrayList;


public class test { //user for test the database class
    public static void main(String[] args){
        database db = new database();
        String username = "123";
        String station = "Boston(BBY)";

        MakeReservation mr = new MakeReservation(username);
        mr.mrWindow();
        
        

    }
}
