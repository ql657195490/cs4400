import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JRadioButton;

public class database {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    public boolean cUsername;
    public int resize;
    private boolean mb;
    private MonthConverter mc;
    public static float totalPrice;
    
    public database(){
        resize = 0;
       try{
           driver = "com.mysql.jdbc.Driver";
           url = "jdbc:mysql://127.0.0.1:3306/cs4400?useSSL=false"; //enter your own mysql info
           username = "root"; //your username
           password = "ql19930305"; //your password
           Class.forName(driver);
           con = DriverManager.getConnection(url, username, password);
           statement = con.prepareStatement("use cs4400"); // to connect the current database
           statement.executeQuery();
       }catch(Exception e){
           
       }
    }
    
    public void search(String sql){
        try{
            statement = con.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()){
            }
        }catch (Exception e){
        }
    }
    
    /**
     * method to insert or update in database
     * @param sql the sql language to database
     * */
    public void update(String sql)throws Exception{
        statement = con.prepareStatement(sql);
        statement.executeUpdate();
    }
    
    /***method use to check the username is exist or not
     * @param sql the sql language to search username
     */
    public void checkUsername(String sql)throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
            this.setCheckUsername(result.getString(1));
        }
    }
    
    
    /**
     * use to check the user is a customer or manager also check email
     * @param sql the sql language to check 
     * @return userType in the table customer
     * */
    public String checkFunctionality(String sql)throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
            return result.getString(1);
        }
        return "";
    }
    /**
     * method to check the username and password is match or not
     * @param sql the sql language to do the check
     * @return return a password 
     * */
    public String checkLoginInfo(String sql)throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
            return result.getString(1);
           
        }
        return "";
    }
    
    public Object[][] viewSchedule(String sql)throws Exception{
        Object ss[][] = new Object[100][4];
        int count = 0;
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
           for (int i = 0; i < 4; i++){
               
               if((!result.getString(i + 1).equals("00:00:00"))){
                   ss[count][i] = result.getString(i + 1);
                   if (i == 3){
                       ss[count][i] = ss[count][i] + "(" + result.getString(5) + ")";
                   }
               }
           }
           if (count == ss.length - 1){
               ss = this.resize(ss);
               
           }
           count++;
        }
        return  ss;
    }
    
    public void setCheckUsername(String sql){
        if (sql == null || sql.equals("")){
            this.cUsername = true;
        }else{
            this.cUsername = false;
        }
    }
    
    public boolean getCheckUsername(){
        return this.cUsername;
    }
    
    /**
     * method to resize the array if the array is full
     * @param ss the array the pass in
     * @return return the new array after resize
     * */
    public Object[][] resize(Object[][] ss){
        Object[][] temp = new Object[ss.length * 2][ss[0].length];
        for (int i = 0; i < ss.length; i++){
            for (int j = 0; j < ss[0].length; j++){
                temp[i][j] = ss[i][j];
            }
        }
        this.resize++;
        return temp;
    }
    
    /**
     * method to create the array for combobox
     * @param sql the sql query
     * @return return an array
     * */
    public String[] getStation(String sql) throws Exception{
        int stationNum = arraySize("select * from station");
        String[] station = new String[stationNum];
        int count = 0;
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
            station[count] = result.getString(2) + "(" + result.getString(1) + ")";
            count++;
        }
        return station;
    }
    
    /**
     * method to decide the size of array
     * @param sql the sql query 
     * @return the array size
     * */
    public int arraySize(String sql)throws Exception{
        int arraySize = 0;
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        while (result.next()){
            if (result.getString(1) != null){
                arraySize++;
            }else if (result.getString(2) != null){
                arraySize++;
            }
        }
        return arraySize;
    }
    
    /**
     * method to list all possible train when customer searching in make reservation
     * @param sql sql query
     * @return the list in Object[][] array
     * */
    public Object[][] TrainOption(String sql, int size) throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
   
        mb = false;
        Object[][] ss = new Object[size][4];
        int count = 0;
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        java.util.Date dp = null;
        java.util.Date aa = null;
        String dps = "";
        String aas = "";
        String duration = "";
        int hour = 3600000;
        int minute = 60000;
        while (result.next()){
            ss[count][0] = result.getString(1);
            dps = result.getString(2);
            aas = result.getString(3);
            dp = df.parse(dps);
            aa = df.parse(aas);
            long time = aa.getTime() - dp.getTime();
            duration = "";
            duration += String.valueOf(time / hour) + "hr";
            time = time % hour;
            duration += String.valueOf(time / minute) + "min";
            ss[count][1] = "<html>" + dps + "-" + aas + "<br/>"
                    + duration;
            ss[count][2] = new JRadioButton(result.getString(4));
            ss[count][3] = new JRadioButton(result.getString(5));
            count++;
        }
        return ss;
    }
    
    /**
     * method to get all card number for the current customer
     * @param sql the sql query that pass in
     * @return the arraylist that store card number
     * 
     * */
    public ArrayList getCard(String sql)throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList list = new ArrayList();
        while (result.next()){
            list.add(result.getString(1));
        }
        return list;
    }
    
    /**
     * method to get reservation ID
     * @param sql the sql query that pass in
     * @return return the reservationID
     * */
   public String getReservationID(String sql)throws Exception{
       String rid = "";
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       while (result.next()){
           rid = result.getString(1);
       }
       return rid;
   }
   
   /**
    * method to return all the reservation for the current resercationID
    * @param sql the sql query that we pass in
    * @return return an array with all the reservation data
    * 
    * */
   public Object[][] getUpdateReservation(String sql, int size)throws Exception{
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       Object[][] temp = new Object[size][9];
       float[][] total = new float[size][2];
       
       int count = 0;
       
       DateFormat df = new SimpleDateFormat("hh:mm:ss");
       java.util.Date dp = null;
       java.util.Date aa = null;
       String duration = "";
       int hour = 3600000;
       int minute = 60000;
       while(result.next()){
           dp = df.parse(result.getString(2));
           aa = df.parse(result.getString(3));
           
           long time = aa.getTime() - dp.getTime();
           duration = "";
           duration += String.valueOf(time / hour) + "hr";
           time = time % hour;
           duration += String.valueOf(time / minute) + "min";
           mc = new MonthConverter();
           temp[count][0] = new JRadioButton(); 
           temp[count][1] = result.getString(1) ; //index 1: train number
           temp[count][2] = "<html>" + mc.changeMonth(result.getString(11).substring(5, 10)) + result.getString(2) + "-" + result.getString(3) + "<br>" + duration; // index 2: time
           temp[count][3] = result.getString(4); //index 3: departs from
           temp[count][4] = result.getString(5); //index 4: arrives at
           temp[count][5] = result.getString(6); //index 5 : class//
           if (result.getString(6).equals("first")){
               temp[count][6] = result.getString(9);//index 6: price
           }else{
               temp[count][6] = result.getString(10);
           }
           temp[count][7] = result.getString(7); //index 7: number of baggages
           temp[count][8] = result.getString(8); // index 8: passenger name
           total[count][0] = Float.parseFloat(temp[count][6].toString());
           total[count][1] = Float.parseFloat(temp[count][7].toString());
           count++;
           
       }
       setUpdateTotalPrice(total);
       return temp;
   }
   
   /**
    * method to get the array size of update reservation
    * @param sql sql that we pass in
    * @return the size of the array
    * 
    * */
   public int UpdateReservationSize(String sql)throws Exception{
       int size = 0;
       
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       while(result.next()){
           size++;
       }
       return size;
   }
   
   /**
    * method to get the departure date
    * @param sql the sql query that we pass in
    * @return the earliest departure date
    * */
   public String getDepatureDate(String sql)throws Exception{
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       java.util.Date min = null;
       String minDate = "";
       java.util.Date temp = null;
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       
       while(result.next()){
           String tempDate = result.getString(1);
           temp = df.parse(tempDate);
           if (min == null){
               min = temp;
               minDate = tempDate;
           }else{
               if (temp.getTime() < min.getTime()){
                   min = temp;
                   minDate = tempDate;
               }
           }
       }
       return minDate;
   }
   
   /**
    * method to get the size of the review array
    * @param the sql query that we pass in
    * @return the size of array that we use to store the review data
    * */
   public int getReviewSize(String sql)throws Exception{
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       int count = 0;
       while(result.next()){
           count++;
       }
       return count;
   }
   
   /**
    * method use to get the data of review
    * @param sql the sql query that we pass in
    * @param the size of the array
    * @return the array we use to store the data
    * */
   public Object[][] getReviewData(String sql, int size)throws Exception{
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       Object[][] s = new Object[size][2];
       int count = 0;
       while (result.next()){
           s[count][0] = result.getString(1);
           s[count][1] = result.getString(2);
           count++;
       }
       return s;
   }
   
   /**
    * method to get the data for revenue report
    * @param sql the sql query that we pass in
    * @param s the array we use to store the data
    * @param index the index of the array we use to store data
    * @return the array we use to store the data
    * */
   public Object[][] getRevenueReport(String sql, Object[][] s, int index) throws Exception{ 
       
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       while(result.next()){
           s[index][1] = result.getString(1);
           
       }
       return s;
   }
   
   /**
    * method to get the data for popular route report
    * @param sql the sql query that we pass in
    * @param s the array we use to store the data
    * @Param index the index of the array we use to store the data
    * @return the array 
    * */
   public Object[][] getPopularRouteReport(String sql, Object[][] s, int index)throws Exception{
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       int count = 0;
       while(result.next()){
           if (count == 3){
               break;
           }
           s[index][1] = result.getString(1);
           s[index][2] = result.getString(2);
           index++;
           count++;
       }
       return s;
   }
   
   /**
    * method to calculate the total price after update your reservation
    * @param total the array use to store the price and number of bags
    * */
   public void setUpdateTotalPrice(float[][] total){
       float tp = 0;
       for (int i = 0; i < total.length; i++){
           tp += total[i][0];
           if (total[i][1] > 2){
               tp += (total[i][1] - 2) * 30;
           }
       }
       
       this.totalPrice = tp;
   }
}
