import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ViewReview_1 {

    private JFrame frame;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void vr1Window() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewReview_1 window = new ViewReview_1();
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
    public ViewReview_1() {
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
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 60));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblViewReview = new JLabel("View Review");
        lblViewReview.setForeground(Color.ORANGE);
        lblViewReview.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewReview.setBounds(180, 20, 90, 20);
        panel.add(lblViewReview);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(450, 90));
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(null);
        
        JButton btnBackToChoose = new JButton("Back to choose Functionality");
        btnBackToChoose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBackToChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities();
                cf.cfWindow();
            }
        });
        btnBackToChoose.setBounds(120, 32, 210, 29);
        panel_1.add(btnBackToChoose);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(10, 30, 10, 30));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(new Object[][]{
                 {"Good", "test1"}, {"Netural", "test2"}
            }, new Object[]{"Rating", "Comment"});
        TableColumn col1 = table.getColumnModel().getColumn(0);
        col1.setPreferredWidth(50);
        table.setSurrendersFocusOnKeystroke(true);
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        scrollPane.setViewportView(table);
        
    }
}
