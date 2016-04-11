import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class UpdateReservation_1 extends JFrame{
   
    public JFrame frame;
    public JPanel panel, panel1;
    public JScrollPane scrollPane;
    public JTable table;
    public DefaultTableModel dtm = new DefaultTableModel();
    public JLabel label;
    public JButton button1, button2;
    public static String trainNumber;
    public static String ReservationID;
    public static String username;
    public UpdateReservation_1 ur1;
    public static Object[][] s;
    public static Object[][] s1;
    public static int index;
    
    
    //test
//    public static void main(String[] args){
//        Object[][] s1 = {
//                {new JRadioButton(), "test1"},
//                {new JRadioButton(), "test2"}
//        };
//        Object[] s2 = {"Select", "test"};
//        UpdateReservation_1 ur1 = new UpdateReservation_1(s1, s2);
//    }
    public UpdateReservation_1(){
        
    }
    //Constructor
    public UpdateReservation_1(Object[][] s1, Object[] s2, String ReservationID, String username){
        this.s1 = s1;
        createPanel();
        createLabel();
        createButton(s1);
        createTable(s1, s2);
        frame.setBounds(100, 100, 950, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    //method to create all panels and set them to frame
    public void createPanel(){
        panel = new JPanel();
        panel1 = new JPanel();
        scrollPane = new JScrollPane();
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(950, 60));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel1.setPreferredSize(new Dimension(950, 90));
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(panel1, BorderLayout.SOUTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
    }
    
    //method to create all labels and set them to the panels
    public void createLabel(){
        label = new JLabel("Update Reservation");
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        label.setForeground(Color.ORANGE);
        label.setBounds(405, 20, 140, 20);
        panel.add(label);
    }
    
    //method to create all buttons and add them to the panels
    public void createButton(Object[][] s1){
        button1 = new JButton("Next");
        button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button1.setBounds(400, 30, 100, 29);
        button1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                s = new Object[1][8];
                for (int i = 0; i < 8; i++){
                    s[0][i] = s1[index][i + 1];
                }
                UpdateReservation_2 ur2 = new UpdateReservation_2(username, ReservationID,
                        trainNumber, s);
                frame.dispose();
                ur2.ur2Window();         
            }
            
        });
        panel1.add(button1);
        
        button2 = new JButton("Back");
        button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button2.setBounds(200, 30, 100, 29);
        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                UpdateReservation ur = new UpdateReservation(username);
                frame.dispose();
                ur.urWindow();
            }
            
        });
        panel1.add(button2);
        
        //add listener and action event to the button
        button1.addActionListener(new ActionListener() {//button "Next"
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UpdateReservation_2 ur2 = new UpdateReservation_2();
                ur2.ur2Window();
            }
        });
        
        button2.addActionListener(new ActionListener() {//button "Back"
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UpdateReservation ur = new UpdateReservation();
                ur.urWindow();
            }
        });
      
    }
    
    //method to set JTable and add it to JScrollPane
    public void createTable(Object[][] s1, Object[] s2){
       
        JRadioButton[] rb = new JRadioButton[s1.length];
        for (int i = 0; i < rb.length; i++){
            rb[i] = (JRadioButton)s1[i][0];
            rb[i].addItemListener(new UpdateReservationListener((String)s1[i][1], i));
            //s1[i][1] = rb[i];
        }
        
        dtm = new DefaultTableModel(s1, s2);
        table = new JTable(dtm){
            public void tableChanged(TableModelEvent e){
                super.tableChanged(e);
                
                repaint();
            }
        };
        
        ButtonGroup bg = new ButtonGroup();
        
        for (int i = 0; i< rb.length; i++){
            bg.add(rb[i]);
        }
        table.getColumn("Select").setCellRenderer(new Radiorenderer());
        table.getColumn("Select").setCellEditor(new radioEditor(new JCheckBox()));
        scrollPane.setViewportView(table);
    }
    
    /**
     * listener class for the radio button
     * */
   private class UpdateReservationListener implements ItemListener{
       private String trainNum;
       private int index;
        //constructor
        public UpdateReservationListener(String tarinNum, int index){
            this.trainNum = trainNum;
            this.index = index;
            ur1 = new UpdateReservation_1();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            ur1.setTrainNum(this.trainNum);
            ur1.setIndex(this.index);
        }
    }
   
   /**
    * method to set store the train number that current selected
    * @param trainNum the trainNum that current selected
    * */
   public void setTrainNum(String trainNum){
       this.trainNumber = trainNum;
   }
   
   /**
    * method to store the index that current selected
    * @param the index that the user current selected
    * */
   public void setIndex(int index){
       this.index = index;
   }
   
   /**
    * method to get the double array that we use to store the data
    * use when the next screen back to current screen
    * @return return the double array
    * */
   public Object[][] getData(){
       return this.s1;
   }

}

class Radiorenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null){
            return null;
        }
        return (Component)value;
    }
    
}

class radioEditor extends DefaultCellEditor implements ItemListener{
    
    JRadioButton radio;
    public radioEditor(JCheckBox chk){
        super(chk);
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean selected, int row, int col){
        if (value == null) return null;
        
        radio = (JRadioButton) value;
        radio.addItemListener(this);
        return (Component) value;
    }
    
    @Override
    public Object getCellEditorValue(){
        
        radio.removeItemListener(this);
        
        return radio;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        
        super.fireEditingStopped();
        //JOptionPane.showMessageDialog(null, radio.getText());
        
    }
}

