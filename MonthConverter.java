
public class MonthConverter {
    public static String month;
    public static String searchMonth;
    
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
        return this.month;
    }
    
    public String managerMonth(String month){
        String temp = "";
        switch (month){
        case "1":
            temp = "January";
            break;
        case "2":
            temp = "February";
            break;
        case "3":
            temp = "March";
            break;
        case "4":
            temp = "April";
            break;
        case "5":
            temp = "May";
            break;
        case "6":
            temp = "June";
            break;
        case "7":
            temp = "July";
            break;
        case "8": 
            temp = "August";
            break;
        case "9":
            temp = "September";
            break;
        case "10":
            temp = "October";
            break;
        case "11":
            temp = "November";
            break;
        case "12":
            temp = "December ";
            break;
    }
        return temp;
    }
}
