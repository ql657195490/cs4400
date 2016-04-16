import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GiveReview {

    private JFrame frame;
    private JTextField textField;
    public static String username;
    public static String comment;
    public database db;

    /**
     * Launch the application.
     */
    public static void grWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GiveReview window = new GiveReview();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GiveReview() {
        this.comment = "";
        db = new database();
        initialize();
    }
    
    public GiveReview(String username){
        this.username = username;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblGiveReview = new JLabel("Give Review");
        lblGiveReview.setForeground(Color.ORANGE);
        lblGiveReview.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblGiveReview.setBounds(180, 20, 90, 20);
        panel.add(lblGiveReview);
        
        JLabel lblTrainNumber = new JLabel("Train Number");
        lblTrainNumber.setBounds(60, 70, 90, 20);
        panel.add(lblTrainNumber);
        
        textField = new JTextField();
        textField.setBounds(212, 66, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblRating = new JLabel("Rating");
        lblRating.setBounds(60, 110, 61, 16);
        panel.add(lblRating);
        
        String[] rating = {"very good", "good", "neutral", "bad", "very bad"};
        JComboBox comboBox = new JComboBox(rating);
        
        comboBox.setBounds(212, 116, 120, 27);
        panel.add(comboBox);
        
        JLabel lblComment = new JLabel("Comment");
        lblComment.setBounds(60, 150, 61, 20);
        panel.add(lblComment);
        
        JButton btnAddComment = new JButton("Add Comment");
        btnAddComment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddComment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                GiveComment gc = new GiveComment(comment);
                if (btnAddComment.getText().equals("Edit Comment")){

                }
                gc.gcWindow();
                btnAddComment.setText("Edit Comment");
            }
        });
        btnAddComment.setBounds(212, 145, 117, 29);
        panel.add(btnAddComment);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String real = "";
                try{
                    real = db.checkFunctionality("SELECT trainNum FROM trainRoute WHERE trainNum = '"
                            + textField.getText().trim() + "';");
                }catch(Exception a){}
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "train number is empty");
                }else if(!(textField.getText().trim().equals(real))){
                    JOptionPane.showMessageDialog(null, "train number doesn't exist");
                }else{
                    try{
                        String sql = "";
                        if (comment.equals("")){
                            sql = "INSERT review(rating, comment, trainNum, userName) VALUES('" 
                                    + comboBox.getSelectedItem().toString() + "', " + null +", '"
                                    + textField.getText().trim() + "', '" + username + "');";
                        }else{
                             sql = "INSERT review(rating, comment, trainNum, userName) VALUES('" 
                                    + comboBox.getSelectedItem().toString() + "', '" + comment +"', '"
                                    + textField.getText().trim() + "', '" + username + "');";
                        }
                            System.out.println(sql);
                        db.update(sql);
                    }catch (Exception ee){
                        
                    }
                }
                System.out.println(comment);
            }
        });
        btnSubmit.setBounds(175, 220, 100, 29);
        panel.add(btnSubmit);
    }
    
    public void saveComment(String comment){
        this.comment = comment;
    }
}
