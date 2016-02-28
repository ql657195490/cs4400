import java.awt.EventQueue;

import javax.swing.JFrame;
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

import javax.swing.JPasswordField;


public class login {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
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
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setForeground(Color.WHITE);
        frame.getContentPane().add(panel, null);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(190, 31, 61, 19);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(48, 72, 74, 16);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(48, 112, 61, 16);
        panel.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setBounds(200, 66, 147, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                boolean cm = false;// use for test customer or manager version
                if(cm){
                    CustomerFunctionalities cf = new CustomerFunctionalities();
                    cf.cfWindow();
                }else{
                    ManagerChooseFunctionality mcf = new ManagerChooseFunctionality();
                    mcf.mcfWindow();
                }
            }
        });
        btnNewButton.setBounds(94, 171, 86, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
//                frame.dispose();
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
        
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 106, 147, 28);
        panel.add(passwordField);
       
    }
}
