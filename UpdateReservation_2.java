import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class UpdateReservation_2 {

    private JFrame frame;
    private JTextField textField;
    private JTable table;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table_1;
    private JTable table_2;

    /**
     * Launch the application.
     */
    public static void ur2Window() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateReservation_2 window = new UpdateReservation_2();
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
    public UpdateReservation_2() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 850, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(850, 370));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        panel_3.setPreferredSize(new Dimension(850, 70));
        panel.add(panel_3, BorderLayout.NORTH);
        panel_3.setLayout(null);
        
        JLabel lblUpdateReservation = new JLabel("Update Reservation");
        lblUpdateReservation.setForeground(Color.ORANGE);
        lblUpdateReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblUpdateReservation.setBounds(355, 20, 140, 20);
        panel_3.add(lblUpdateReservation);
        
        JLabel lblCurrentTicket = new JLabel("Current Train Ticket");
        lblCurrentTicket.setBounds(10, 48, 140, 20);
        panel_3.add(lblCurrentTicket);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        panel_4.setPreferredSize(new Dimension(850, 40));
        panel.add(panel_4, BorderLayout.SOUTH);
        panel_4.setLayout(null);
        
        JLabel lblNew = new JLabel("New Departure Date");
        lblNew.setBounds(10, 10, 140, 20);
        panel_4.add(lblNew);
        
        textField = new JTextField();
        textField.setBounds(180, 6, 134, 28);
        panel_4.add(textField);
        textField.setColumns(10);
        
        JButton btnCalender = new JButton("calendar");
        btnCalender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCalender.setBounds(337, 7, 80, 29);
        panel_4.add(btnCalender);
        
        JButton btnSearch = new JButton("Search availability");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.setBounds(500, 7, 140, 29);
        panel_4.add(btnSearch);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));
        panel_5.setBorder(new EmptyBorder(10,10,10,10));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_5.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(new Object[][]{
                                        {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"}
                                        
                            }, new Object[]{"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"});
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setPreferredSize(new Dimension(850, 330));
        frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.WHITE);
        panel_6.setPreferredSize(new Dimension(850, 20));
        panel_2.add(panel_6, BorderLayout.NORTH);
        panel_6.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Update Train Ticket");
        lblNewLabel.setBounds(10, 0, 140, 20);
        panel_6.add(lblNewLabel);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBackground(Color.WHITE);
        panel_7.setPreferredSize(new Dimension(850, 150));
        panel_2.add(panel_7, BorderLayout.SOUTH);
        panel_7.setLayout(null);
        
        JLabel lblChangeFee = new JLabel("Change Fee");
        lblChangeFee.setBounds(10, 10, 80, 20);
        panel_7.add(lblChangeFee);
        
        textField_1 = new JTextField();
        textField_1.setBounds(170, 6, 134, 28);
        panel_7.add(textField_1);
        textField_1.setColumns(10);
        textField_1.setEnabled(false);
        
        JLabel lblUpload = new JLabel("Updated Total Cost");
        lblUpload.setBounds(10, 40, 130, 20);
        panel_7.add(lblUpload);
        
        textField_2 = new JTextField();
        textField_2.setBounds(170, 36, 134, 28);
        panel_7.add(textField_2);
        textField_2.setColumns(10);
        textField_2.setEnabled(false);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(55, 95, 100, 29);
        panel_7.add(btnBack);
        
        JButton btnSumbit = new JButton("Submit");
        btnSumbit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSumbit.setBounds(238, 95, 100, 29);
        panel_7.add(btnSumbit);
        
        JPanel panel_8 = new JPanel();
        panel_8.setBackground(Color.WHITE);
        panel_8.setBorder(new EmptyBorder(10, 10, 10 ,10));
        panel_2.add(panel_8, BorderLayout.CENTER);
        panel_8.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane_1 = new JScrollPane();
        panel_8.add(scrollPane_1, BorderLayout.CENTER);
        
        table_2 = new JTable(new Object[][]{
                {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"}
                
    }, new Object[]{"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"});
        table_2.setEnabled(false);
     
        scrollPane_1.setViewportView(table_2);
        
       
    }
}
