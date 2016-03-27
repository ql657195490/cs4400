import java.util.ArrayList;


public class MakeReservationData {
    public static Object[][] s1;
    public boolean needResize;
    
    //constructor
    public MakeReservationData(boolean nr){
        
        this.needResize = nr;
        if(needResize){// if need to resize the array, call resize method
            resize();
        }
    }
    
    /**
     * method to remove the reservation data
     * @param s1 the array we use to save the data
     * @param list the arraylist we use to save the data that we want to remove
     * */
    public void removeReservationData(Object[][] s1, String rs){
        for (int i = 0 ; i < s1.length; i++){
           if (rs.equals(s1[i][0])){
               s1 = renewReservationData(s1, i);
               setReservationData(s1);
           }
        }
    }
    
    /**
     * method to create a new array after remove the data
     * @param s1 the array we use to store the data
     * @param index the index we want to remove
     * @return return a new array
     * */
    public Object[][] renewReservationData(Object[][] s1, int index){
        Object[][] temp = new Object[s1.length - 1][s1[0].length];
        int count = 0;
        for (int i = 0; i < temp.length; i++){
            if (count == index){
                count++;
            }
            for (int j = 0; j< temp[0].length; j++){
                temp[i][j] = s1[count][j];
            }
            count++;
        }
        return temp;
    }
    
    /**
     * method to save the reservationData
     * @param s1 the array use to save the data
     * */
    public void setReservationData(Object[][] s1){
        this.s1 = s1;
    }
    
    /**
     * method to get the data
     * @return return the data
     * */
    public Object[][] getReservationData(){
        return this.s1;
    }
    
    
    /**
     * method to resize the array
     * @return return the new array after resize
     * */
    public void  resize(){
        Object temp[][] = new Object[s1.length + 1][9];
        for (int i = 0; i < s1.length; i++){
            for (int j = 0; j < 9; j++){
                temp[i][j] = s1[i][j];
            }
        }
        this.s1 = temp;
    }
}
