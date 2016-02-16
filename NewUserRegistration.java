import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;


public class NewUserRegistration {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;

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
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
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
        
        textField = new JTextField();
        textField.setBounds(196, 91, 200, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(196, 123, 200, 28);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton = new JButton("create");
        btnNewButton.setBounds(177, 261, 89, 29);
        panel.add(btnNewButton);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(196, 157, 200, 28);
        panel.add(passwordField);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(196, 191, 200, 28);
        panel.add(passwordField_1);
    }
}
