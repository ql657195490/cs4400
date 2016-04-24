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
         DateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
         System.out.println(sm.format(new Date()));
         String ss = "<html>Time is blablabla<br/>(Duration)";
         for(int i = 0; i < ss.length() - 1; i++){
             if (ss.charAt(i) == ' '){
                 System.out.println("space index is " + i);
             }
         }
         System.out.println(ss);
         System.out.println("length is " + ss.length());
         Calendar now = Calendar.getInstance();
         int a = now.get(Calendar.YEAR);
         System.out.println("a " + a);
//        int a = currentDate.get(Calendar.YEAR);
//        System.out.println("year: " + currentDate.get(Calendar.YEAR));
//        System.out.println("month: " + (currentDate.get(Calendar.MONTH) + 1));
//        System.out.println("date: " + currentDate.get(Calendar.DATE));
//        String s = "1995-11-12";
//        String s1 = "1996-11-11";
//             Date dt = null;
//             dt.
//        Date dt1 = null;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try{
//            dt = df.parse(s);
//            dt1 = df.parse(s1);
//        }catch(Exception e){}
//        System.out.println("----" + dt);
//        if (dt.getTime() > dt1.getTime()){
//            System.out.println("true");
//        }else{
//            System.out.println("false"); 
//        }
////        System.out.println(dt1.getTime() - dt.getTime());
//        Date dt = null;
//        String t1 = "11:30:22";
//       // DateFormat df = new SimpleDateFormat("hh-mm-ss")
//                
//        Object[][] s = new Object[3][2];
//        try{
//            String sql = "select sum(ifnull(p,0) + ifnull(p1,0)) from (select sum(sClassPrice) as p from "
//                    + "(trainRoute natural join (select * from reserves where reservationID"
//                    + " in (select ReservationID from Reservation where isCanceled ='false')  and (departureDate like '2016-01%')) as a ) where class = 'first') "
//                    + "as b join (select sum(fClassPrice)  as p1 from (trainRoute natural join"
//                    + " (select * from reserves where reservationID in (select ReservationID from Reservation where isCanceled ='false') and (departureDate like "
//                    + "'2016-01%')) as a ) where class = 'second') as c";
//            System.out.println(sql);
//            s[0][0] = "January";
//            System.out.println("test1");
//            s = db.getRevenueReport(sql, s, 0);
//    }catch(Exception e){}
//        System.out.println(s[0][1]);
//        System.out.println(s[1][1]);
        
        //test makeReservation
//        MakeReservation mr = new MakeReservation(username);
//        mr.mrWindow();
//        CustomerFunctionalities cf = new CustomerFunctionalities(username);
//        cf.cfWindow();
        
        
        //test updateReservation
//        UpdateReservation ur = new UpdateReservation(username);
//        ur.urWindow();

//        MakeReservation mr = new MakeReservation(username);
//        long a = 20;
//        int b = 3;
//        System.out.println(a/b);
    }
    private class datetest{
        public int year;
        
        public datetest(){
            
        }
    }
    
//        mr.mrWindow();
       
}
