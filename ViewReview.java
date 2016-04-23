import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ViewReview {

    private JFrame frame;
    private JTextField textField;
    public static String username;
    public static Object[][] s;
    public database db;

    /**
     * Launch the application.
     */
    public static void vrWindow(    ) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewReview window = new ViewReview();
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
    public ViewReview() {
        db = new database();
        initialize();
    }
    
    public ViewReview(String usernmae){
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
        
        JLabel lblViewReview = new JLabel("View Review");
        lblViewReview.setForeground(Color.ORANGE);
        lblViewReview.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewReview.setBounds(180, 20, 90, 20);
        panel.add(lblViewReview);
        
        JLabel lblTrainNumber = new JLabel("Train Number");
        lblTrainNumber.setBounds(60, 75, 90, 20);
        panel.add(lblTrainNumber);
        
        textField = new JTextField();
        textField.setBounds(256, 70, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnNewButton.setBounds(100, 228, 100, 29);
        panel.add(btnNewButton);
        
        JButton btnNext = new JButton("Next");
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tn = "";
                try{
                    tn = db.checkFunctionality("SELECT trainNum FROM trainRoute WHERE trainNum = '"
                            + textField.getText().trim() + "';");
                    int size = db.getReviewSize("SELECT * FROM review");
                    s = db.getReviewData("SELECT rating, comment FROM review where trainNum = '"
                            + textField.getText().trim() + "';", size);
                }catch(Exception ee){}
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "train number is empty");
                }else if (tn.equals(textField.getText().trim())){
                    frame.dispose();
                    ViewReview_1 vr1 = new ViewReview_1(username, s);
                    vr1.vr1Window();
                }else{
                    JOptionPane.showMessageDialog(null, "invalid train number");
                }
            }
        });
        btnNext.setBounds(230, 228, 100, 29);
        panel.add(btnNext);
    }
}
