import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ManagerChooseFunctionality {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void mcfWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerChooseFunctionality window = new ManagerChooseFunctionality();
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
    public ManagerChooseFunctionality() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Choose Functionality");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setBounds(150, 20, 150, 20);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("<html><u>View revenue report</u></html>");
        lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ViewRevenueReport vrr = new ViewRevenueReport();
                vrr.vrrWindow();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblNewLabel_1.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel_1.setForeground(SystemColor.textHighlight);
            }
        });
        lblNewLabel_1.setForeground(SystemColor.textHighlight);
        lblNewLabel_1.setBounds(160, 70, 130, 20);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("<html><u>View popular route report</u></html>");
        lblNewLabel_2.setForeground(SystemColor.textHighlight);
        lblNewLabel_2.setBounds(145, 100, 160, 20);
        lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                ViewPopularRouteReport vprr = new ViewPopularRouteReport();
                vprr.vprrWindow();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblNewLabel_2.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel_2.setForeground(SystemColor.textHighlight);
            }
        });
        frame.getContentPane().add(lblNewLabel_2);
        
        JButton btnLogout = new JButton("Log out");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.setBounds(178, 225, 100, 29);
        frame.getContentPane().add(btnLogout);
    }
}
