import java.util.ArrayList;


public class test { //user for test the database class
    public static void main(String[] args){
//        database db = new database();
//        String username = "123";
//        String station = "Boston(BBY)";
//
//        MakeReservation mr = new MakeReservation(username);
//        mr.mrWindow();
        test t = new test();
        int[] arr;
        arr = t.ttt(10);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        

    }
    
    public int[] ttt(int a){
        int[] arr = new int[a];
        for (int i = 0; i < arr.length; i++){
            arr[i] = a;
        }
        return arr;
    }
}
