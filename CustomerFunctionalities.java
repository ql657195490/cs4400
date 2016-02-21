import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CustomerFunctionalities {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void cfWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFunctionalities window = new CustomerFunctionalities();
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
    public CustomerFunctionalities() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel, null);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Choose Functionality");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(145, 20, 160, 35);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("<html><u>View Train Schedule</u></html>");
        lblNewLabel_1.setForeground(SystemColor.textHighlight);
       
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewTrainSchedule vts = new ViewTrainSchedule();
                vts.vtsWindow();
            }
        });
        lblNewLabel_1.setBounds(160, 60, 130, 35);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("<html><u>Make a new reservation</u></html>");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                MakeReservation mr = new MakeReservation();
                mr.mrWindow();
            }
        });
        lblNewLabel_2.setForeground(SystemColor.textHighlight);
        lblNewLabel_2.setBounds(150, 85, 150, 35);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("<html><u>Update a reservation</u></html>");
        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                UpdateReservation ur = new UpdateReservation();
                ur.urWindow();
            }
        });
        lblNewLabel_3.setForeground(SystemColor.textHighlight);
        lblNewLabel_3.setBounds(155, 110, 140, 35);
        panel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("<html><u>Cancel a reservation</u></html>");
        lblNewLabel_4.setForeground(SystemColor.textHighlight);
        lblNewLabel_4.setBounds(155, 135, 140, 35);
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("<html><u>    Give review</u></html>");
        lblNewLabel_5.setForeground(SystemColor.textHighlight);
        lblNewLabel_5.setBounds(185, 160, 80, 35);
        panel.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("<html><u>Add school information(Student discount)</u></html>");
        lblNewLabel_6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                AddSchoolInfo asi = new AddSchoolInfo();
                asi.asiWindow();
            }
        });
        lblNewLabel_6.setForeground(SystemColor.textHighlight);
        lblNewLabel_6.setBounds(90, 185, 270, 35);
        panel.add(lblNewLabel_6);
        
        JButton btnNewButton = new JButton("Log out");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login li = new login();
                li.loginWindow();
            }
        });
        btnNewButton.setBounds(300, 265, 87, 29);
        panel.add(btnNewButton);
    }

}
