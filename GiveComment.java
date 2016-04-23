import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GiveComment {

    private JFrame frame;
    public GiveReview gr;
    private JTextArea textArea;
    private static String comment;
    /**
     * Launch the application.
     */
    public static void gcWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GiveComment window = new GiveComment();
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
    public GiveComment() {
        gr = new GiveReview();
      
        
        initialize();
    }
    
    public GiveComment(String comment){
        this.comment = comment;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(175, 150, 300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(300,40));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(null);
        
        JButton btnBack = new JButton("Done");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(textArea.getText());
                gr.saveComment(textArea.getText());
                frame.dispose();
            }
        });
        btnBack.setBounds(100, 6, 100, 29);
        panel.add(btnBack);
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
//        if (b){
//            textArea.setText(comment);
//        }
        textArea.setText(comment);
  
        scrollPane.setViewportView(textArea);
    }
    
    

}
