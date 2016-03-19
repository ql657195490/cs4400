import java.awt.EventQueue;
import java.io.*;

import sun.audio.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class NewUserRegistration{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private boolean b;
    private database db;
    public String sql;

    /**
     * Launch the application.
     */
    public static void nurWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    NewUserRegistration window = new NewUserRegistration();
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
    public NewUserRegistration() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        db = new database();
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel, null);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New User Registration");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(148, 31, 167, 41);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(40, 91, 78, 28);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Email Address");
        lblNewLabel_2.setBounds(40, 123, 89, 28);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Password");
        lblNewLabel_3.setBounds(40, 157, 78, 28);
        panel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Confirm Password");
        lblNewLabel_4.setBounds(40, 191, 122, 28);
        panel.add(lblNewLabel_4);
        
        textField = new JTextField("enter you username");
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }
        });
        textField.setForeground(new Color(204, 204, 204));
        
        textField.setBounds(196, 91, 200, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        textField_1.setForeground(new Color(204, 204, 204));
        textField_1.setBounds(196, 123, 200, 28);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblPasswordLengthNeed = new JLabel();
        panel.add(lblPasswordLengthNeed);
        
        passwordField = new JPasswordField();
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                lblPasswordLengthNeed.setText("password length need to larger than 10");
                lblPasswordLengthNeed.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
                lblPasswordLengthNeed.setForeground(Color.RED);
                lblPasswordLengthNeed.setBounds(200, 180, 200, 16);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                lblPasswordLengthNeed.setText("");
            }
        });
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblPasswordLengthNeed.setText("password length need to larger than 10");
                lblPasswordLengthNeed.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
                lblPasswordLengthNeed.setForeground(Color.RED);
                lblPasswordLengthNeed.setBounds(200, 180, 200, 16);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblPasswordLengthNeed.setText("");
            }
        });
        passwordField.setForeground(new Color(204, 204, 204));
        passwordField.setBounds(196, 157, 200, 28);
        panel.add(passwordField);
        
        JLabel label = new JLabel("");
        panel.add(label);
        passwordField_1 = new JPasswordField();
        passwordField_1.setForeground(new Color(204, 204, 204));
        passwordField_1.setBounds(196, 191, 200, 28);
        passwordField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                label.setText("must same as the password you enter in");
                label.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
                label.setForeground(Color.RED);
                label.setBounds(200, 210, 200, 16);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                label.setText("");
            }
        });
        panel.add(passwordField_1);
        
        
        JButton btnNewButton = new JButton("create");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().trim() == null || textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "username cannot be null");
                }else if (textField_1.getText().trim() == null || textField_1.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "email cannot be null");
                }else if (passwordField.getText().trim() == null || passwordField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "please enter the password");
                }else if (passwordField_1.getText().trim() == null || passwordField_1.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "please enter your password again");
                }else {
                    
                    try {
                      

//                        InputStream fileau=new FileInputStream(new File("buttonClick.wav" ));
//                        AudioStream as=new AudioStream(fileau);
//                        AudioPlayer.player.start(as);
                        db.checkUsername("select username from customer where username = " + textField.getText().trim());
                        if (db.getCheckUsername()){
                            JOptionPane.showMessageDialog(null, "username already exist");
                        }else if (!(db.checkFunctionality("select email from customer where email = '"
                                + textField_1.getText().trim()+ "'").equals(""))){
                            JOptionPane.showMessageDialog(null, "email already exist");
                        }else{
                            sql = "insert user values('" + textField.getText().trim()+ "', '" + passwordField.getText().trim()
                                    + "', 'customer')";
                            db.update(sql);
                            sql = "insert customer values('" + textField_1.getText().trim() + "', '" + textField.getText().trim()
                                    + "', 'false')";
//                             sql = "insert customer values('" + textField.getText().trim() + "', '" + passwordField.getText().trim()
//                                    + "', 1, '" + textField_2.getText().trim() + "', '" + textField_1.getText().trim()
//                                    + "', 0, 'No')";
                            db.update(sql);
                            JOptionPane.showMessageDialog(null, "register successful");
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
            }
        });
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setBounds(176, 305, 89, 29);
        panel.add(btnNewButton);
        
        

    }
    
    public void setB(boolean b){
        this.b = b;
    }
    
    public boolean getB(){
        return b;
    }
}
