import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeReservation_1 {

    private JFrame frame;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void mrWindow_1() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_1 window = new MakeReservation_1();
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
    public MakeReservation_1() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 60));
        panel.setLayout(null);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(450, 60));
        panel_1.setLayout(null);
        panel_1.setBackground(Color.WHITE);
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MakeReservation mr = new MakeReservation();
                mr.mrWindow();
            }
        });
        btnNewButton.setBounds(80, 20, 100, 29);
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MakeReservation_2 mr2 = new MakeReservation_2();
                mr2.mrWindow_2();
            }
        });
        btnNewButton_1.setBounds(270, 20, 100, 29);
        panel_1.add(btnNewButton_1);
        
        JLabel lblSelectDeparture = new JLabel("Select Departure");
        lblSelectDeparture.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        lblSelectDeparture.setBounds(165, 20, 120, 20);
        panel.add(lblSelectDeparture);
        
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        
        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "1st Class Price", "2nd Class Price"};
        Object[][] s1 = {
                {1, 1, 1, 1},
                {1, 1, 1 ,1}
        };
        table = new JTable(s1, s);
        table.setPreferredScrollableViewportSize(new Dimension(450, 280));
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);
        
       
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
