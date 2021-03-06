import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ViewRevenueReport {

    private JFrame frame;
    private JTable table;
    public static Object[][] s;

    /**
     * Launch the application.
     */
    public static void vrrWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewRevenueReport window = new ViewRevenueReport();
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
    public ViewRevenueReport() {
        initialize();
    }
    
    public ViewRevenueReport(Object[][] s){
        this.s = s;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 60));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblViewRevenueReport = new JLabel("View Revenue Report");
        lblViewRevenueReport.setBounds(150, 20, 150, 20);
        lblViewRevenueReport.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewRevenueReport.setForeground(Color.ORANGE);
        panel.add(lblViewRevenueReport);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(450, 120));
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ManagerChooseFunctionality mcf = new ManagerChooseFunctionality();
                mcf.mcfWindow();
            }
        });
        btnBack.setBounds(79, 53, 100, 29);
        panel_1.add(btnBack);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EmptyBorder(10, 20, 0, 20));
        panel_2.setBackground(Color.WHITE);
        frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(s
                , new Object[]{"Month", "Revenue"});
        scrollPane.setViewportView(table);
    }
}
