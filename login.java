import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
import java.awt.event.KeyListener;

import javax.swing.JPasswordField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class login extends JFrame {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    public database db;
    public String sql;
    public String username;

    //constructor 
//    public login(){
//       buildwindow();
//       frame.setVisible(true);
//    }
//    /**
//     * Launch the application.
//     */
    public static void loginWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
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
    public login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setForeground(Color.WHITE);
        ImageIcon icon = new ImageIcon("/Users/Lei/Documents/java2/cs4400/dl.jpg");

        JPanel panel = new ImagePanel(icon);
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.WHITE);
        frame.getContentPane().add(panel, null);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login"); //longin Label
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(190, 31, 61, 19);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username"); //username label
        lblNewLabel_1.setBounds(48, 72, 74, 16);
        lblNewLabel_1.setForeground(Color.WHITE);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password"); // password label
        lblNewLabel_2.setBounds(48, 112, 61, 16);
        lblNewLabel_2.setForeground(Color.WHITE);
        panel.add(lblNewLabel_2);
        
        textField = new JTextField(); // textField for username
        textField.setBounds(200, 66, 147, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField(); // passwordField for password
      
        passwordField.setBounds(200, 106, 147, 28);
        panel.add(passwordField);
        
        JButton btnNewButton = new JButton("Login"); //login button
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new database();
                try{
                    if(textField.getText().trim().equals("") || passwordField.getText().trim().equals("")){
                        JOptionPane.showMessageDialog(null, "Invalid login information");
                    }else{
                        db.checkUsername("select username from user where username = '" + textField.getText().trim()
                                +"'");
                        if (!db.getCheckUsername()){
                            if(passwordField.getText().trim().equals(db.checkLoginInfo("select password from user"
                                    + " where username = '" + textField.getText().trim() + "'"))){
                                frame.dispose();
                                if (db.checkFunctionality("select userType from user where username = '" +
                                    textField.getText().trim() + "'").equals("customer")){
                                    login li = new login();
                                    li.setUsername(textField.getText().trim());
                                    System.out.println(li.getUsername());
                                    CustomerFunctionalities cf = new CustomerFunctionalities(li.getUsername());
                                    cf.cfWindow();
                                }else if (db.checkFunctionality("select userType from user where username = '" +
                                        textField.getText().trim() + "'").equals("manager")){
                                    ManagerChooseFunctionality mcf = new ManagerChooseFunctionality();
                                    mcf.mcfWindow();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Invalid login information");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Invalid login informaiton");
                        }
                    }
                       
                    }catch (Exception ee){
                        
                    }
                
               
            }
        
        });
       // btnNewButton.set
        btnNewButton.setBounds(94, 171, 86, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Register"); // register button
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
//                try{
//                    this.wait();
//                }catch (Exception ee){
//                    
//                }
                NewUserRegistration nur = new NewUserRegistration();
                nur.nurWindow();
            }
        });
        btnNewButton_1.setBounds(274, 171, 86, 29);
        panel.add(btnNewButton_1);
        
//        JLabel lblNewLabel_3 = new JLabel("New label");
//        ImageIcon image = new ImageIcon("/Users/Lei/Documents/java2/cs4400/xmf.jpg");
//        lblNewLabel_3.setIcon(image);
//        lblNewLabel_3.setBounds(274, 235, image.getIconWidth(), image.getIconHeight());
//        panel.add(lblNewLabel_3);
        
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    btnNewButton.doClick();
                }
            }
        });
        
        //ImageIcon image = new ImageIcon("xmf");
        //JLabel labeltp = new JLabel("");
       // labeltp.setIcon(image);
       
        //labeltp.setBounds(100, 300, image.getIconHeight(), image.getIconWidth());
        //panel.add(labeltp);
        
    }
    
    /**
     * methond to store the username
     * @param username the username that the user login
     * 
     * */
    public  void setUsername(String username){
        this.username = username;
    }
    
    /**
     * method to get the username
     * @return the username that the customer login
     * */
    public String getUsername(){
        return this.username;
    }
}
