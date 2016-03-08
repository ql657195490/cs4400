import java.sql.*;

public class database {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    public boolean cUsername;
    
    public database(){
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
     * use to check the user is a customer or manager
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
    
    
}
