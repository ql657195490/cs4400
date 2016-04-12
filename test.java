import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class test { //user for test the database class
    public static void main(String[] args){
        database db = new database();
        String username = "123";
        String station = "Boston(BBY)";
        
        Calendar currentDate = Calendar.getInstance(); 
        int a = currentDate.get(Calendar.YEAR);
        System.out.println("year: " + currentDate.get(Calendar.YEAR));
        System.out.println("month: " + (currentDate.get(Calendar.MONTH) + 1));
        System.out.println("date: " + currentDate.get(Calendar.DATE));
        String s = "1995-11-12";
        String s1 = "1996-11-11";
        Date dt = null;
        Date dt1 = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dt = df.parse(s);
            dt1 = df.parse(s1);
        }catch(Exception e){}
        System.out.println("----" + dt);
        if (dt.getTime() > dt1.getTime()){
            System.out.println("true");
        }else{
            System.out.println("false"); 
        }
        System.out.println(dt1.getTime() - dt.getTime());
        
        //test makeReservation
//        MakeReservation mr = new MakeReservation(username);
//        mr.mrWindow();
//        CustomerFunctionalities cf = new CustomerFunctionalities(username);
//        cf.cfWindow();
        
        
        //test updateReservation
//        UpdateReservation ur = new UpdateReservation(username);
//        ur.urWindow();

//        MakeReservation mr = new MakeReservation(username);
    }
    private class datetest{
        public int year;
        
        public datetest(){
            
        }
    }
    
//        mr.mrWindow();
       
}
