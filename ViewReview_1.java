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
    public static String username;
    public static Object[][] s;
    public database db;

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
    
    public ViewReview_1(String username, Object[][] s){
        this.username = username;
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
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnBackToChoose.setBounds(120, 32, 210, 29);
        panel_1.add(btnBackToChoose);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(10, 30, 10, 30));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        
        
        table = new JTable(s, new Object[]{"Rating", "Comment"});
        TableColumn col1 = table.getColumnModel().getColumn(0);
        col1.setPreferredWidth(50);
        table.setSurrendersFocusOnKeystroke(true);
        table.setEnabled(true);
        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        System.out.println(table.getModel().getValueAt(1, 1).toString());
        for (int i = 0; i < s.length; i++){
            if(s[i][1] != null && s[i][1].toString().length() > 25){
                resize(i, s[i][1].toString());
            }
        }
        scrollPane.setViewportView(table);
        
    }
    
    public void resize(int index, String comment){
        
        int startIndex = 0;
        int endIndex = 33;
        String temp = comment.substring(0, 25);
        int size = comment.length();
        int spaceIndex = 25;
        String renewData = "<html>";
        while(size > 25){
            table.setRowHeight(index, table.getRowHeight(index) + 20);
            spaceIndex = getSpace(temp);
            renewData += temp.substring(0, spaceIndex) + "<br/>";
            size -= spaceIndex;
            startIndex += spaceIndex;
            System.out.println("space index: " + spaceIndex);
            if(size > 25){
                endIndex = 25;
                temp = comment.substring(startIndex, startIndex + 25);
                spaceIndex  = getSpace(temp);
            }
        }
        renewData += comment.substring(comment.length()- size, comment.length());
        System.out.println("old: " + s[index][1]);
        System.out.println("new: " + renewData);
        s[index][1] = renewData;
    }
    
    public int getSpace(String comment){
        System.out.println("space comment: " + comment);
        int index  = 0;
        boolean b = false;
        for(int i = 24; i > 0; i--){
            if(comment.charAt(i) == ' '){
                index = i;
                b = true;
                break;
            }
        }
        return b ? index : 25;
    }
}

