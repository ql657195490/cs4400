import java.sql.*;
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
                System.out.println(result.getString(1) + "\t" + result.getString(2));
            }
        }catch (Exception e){
           // System.out.println("no match information");
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
    
    /*method use to check the username is exist or not
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

               ss[count][i] = result.getString(i + 1);
               if (i == 3){
                   ss[count][i] = ss[count][i] + "(" + result.getString(5) + ")";
               }
           }
           if (count == ss.length - 1){
               ss = this.resize(ss);
               
           }
           count++;
        }
        return  ss;
    }
    
    public void setCheckUsername(String s){
        if (s == null || s.equals("")){
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
    public Object[][] TrainOption(String sql) throws Exception{
        statement = con.prepareStatement(sql);
        result = statement.executeQuery();
   
        mb = false;
        Object[][] ss = new Object[100][4];
        int count = 0;
        while (result.next()){
            if (Integer.parseInt(result.getString(2).substring(0, 2)) - 
                    Integer.parseInt(result.getString(3).substring(0, 2)) < 0){
                mb = true;
            }else if(Integer.parseInt(result.getString(2).substring(0, 2)) - 
                    Integer.parseInt(result.getString(3).substring(0, 2)) == 0){
                if (Integer.parseInt(result.getString(2).substring(3,5)) - 
                        Integer.parseInt(result.getString(3).substring(3, 5)) < 0){
                    mb = true;
                }else if(Integer.parseInt(result.getString(2).substring(3,5)) - 
                        Integer.parseInt(result.getString(3).substring(3, 5)) == 0){
                    
                }
            }
            for (int i = 0; i < 4; i++){
                if (mb){
                    if (i == 0){
                        ss[count][i] = result.getString(i + 1);
                    }else if (i == 1){
                        ss[count][i] = result.getString(i + 1) + "-" + result.getString(i + 2);
                    }else{
                        ss[count][i] = new JRadioButton(result.getString(i + 2));
                    }
                }
            }
            mb = false;
            if (count == ss.length - 1){
                this.resize(ss);
            }
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
       System.out.println(sql);
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       Object[][] temp = new Object[size][9];
       System.out.println(temp.length);
       
       int count = 0;
       while(result.next()){
           System.out.println("test");
           temp[count][0] = new JRadioButton(); 
           temp[count][1] = result.getString(1) ; //index 1: train number
           System.out.println(temp[count][1]);
           temp[count][2] = result.getString(2) + result.getString(3); // index 2: time
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
           count++;
           
       }
       return temp;
   }
   
   public int UpdateReservationSize(String sql)throws Exception{
       int size = 0;
       
       statement = con.prepareStatement(sql);
       result = statement.executeQuery();
       while(result.next()){
           size++;
       }
       return size;
   }
}
