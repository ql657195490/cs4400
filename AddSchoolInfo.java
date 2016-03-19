import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddSchoolInfo {

    private JFrame frame;
    private JTextField textField;
    public String sql;
    public database db;
    public login li;
    public static String username;

    /**
     * Launch the application.
     */
    public static void asiWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddSchoolInfo window = new AddSchoolInfo(username);
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
    public AddSchoolInfo(String username) {
        this.username = username;
        initialize();
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
        panel.setBounds(0, 0, 450, 278);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Add School Info");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(165, 20, 120, 16);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("School Email Address");
        lblNewLabel_1.setBounds(50, 70, 140, 16);
        panel.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(220, 64, 184, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Your school Email address ends with .edu");
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        lblNewLabel_2.setBounds(50, 90, 235, 16);
        panel.add(lblNewLabel_2);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnNewButton.setBounds(85, 190, 100, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Sumbit");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String edu = textField.getText().trim();
                if (edu.length() < 4){
                    JOptionPane.showMessageDialog(null, "invalid school information");
                }else{
                    edu = edu.substring(edu.length() - 3, edu.length());
                    if (edu.equals("edu")){
                        db = new database();
                        //li = new login();
                        System.out.println(username);
                        sql = "update customer set isStudent = 'true' where username = '" + username + "'";
                        System.out.println(sql);
                        try{
                            db.update(sql);
                        }catch (Exception ee){}
                        JOptionPane.showMessageDialog(null, "add school information successful");
                        frame.dispose();
                        CustomerFunctionalities cf = new CustomerFunctionalities(username);
                        cf.cfWindow();
                    }else {
                        JOptionPane.showMessageDialog(null, "invalid school information");
                    }
                }
               
            }
        });
        btnNewButton_1.setBounds(235, 190, 100, 29);
        panel.add(btnNewButton_1);
    }
}
