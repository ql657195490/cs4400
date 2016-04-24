
public class UpdateReservationData {
    public  Object[][] s1;
    
    //constructor
    public UpdateReservationData(){
        
    }
    
    public UpdateReservationData(Object[][] s1){
        this.s1 = s1;
    }
    
    public void renewReservationData(String s){
        this.s1[0][1] = s;
    }
    
    public void setUpdateReservationData(Object[][] s1){
        this.s1 = s1;
    }
    public Object[][] currentSelectData(){
        return this.s1;
    }
    
}
