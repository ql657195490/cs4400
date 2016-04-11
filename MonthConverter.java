
public class MonthConverter {
    public static String month;
    
    //constructor
    public MonthConverter(){
        
    }
    
    public String changeMonth(String month){
        String date = month.substring(3, 5);
        month = month.substring(0, 2);
        
        switch (month){
            case "01":
                this.month = "Jan " + date + " ";
                break;
            case "02":
                this.month = "Feb " + date + " ";
                break;
            case "03":
                this.month = "Mar " + date + " ";
                break;
            case "04":
                this.month = "Apr " + date + " ";
                break;
            case "05":
                this.month = "May " + date + " ";
                break;
            case "06":
                this.month = "Jun " + date + " ";
                break;
            case "07":
                this.month = "Jul " + date + " ";
                break;
            case "08": 
                this.month = "Aug " + date + " ";
                break;
            case "09":
                this.month = "Sept " + date + " ";
                break;
            case "10":
                this.month = "Oct " + date + " ";
                break;
            case "11":
                this.month = "Nov " + date + " ";
                break;
            case "12":
                this.month = "Dec " + date + " ";
                break;
        }
        System.out.println(this.month);
        return this.month;
    }
}
