import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ManagerChooseFunctionality {

    private JFrame frame;
    public database db;
    public static Object[][] s;
    public static Date currentDate;
    public static DateFormat df;
    public static Calendar now;
    public static int month;

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
        db = new database();
        df = new SimpleDateFormat("yyyy-MM-dd");
        now = Calendar.getInstance();
        month = now.get(Calendar.MONTH);
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
        
        ImageIcon icon = new ImageIcon("/Users/Lei/Documents/java2/cs4400/bj1.jpg");
        
        
        
        JPanel panel_1 = new ImagePanel(icon);
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("<html><u>View revenue report</u></html>");
        lblNewLabel_1.setBounds(163, 70, 124, 16);
        panel_1.add(lblNewLabel_1);
        lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                s = new Object[3][2];
                
                try{
                    
                    String sql = "select sum(ifnull(p,0) + ifnull(p1,0)) from (select sum(sClassPrice) as p from "
                            + "(trainRoute natural join (select * from reserves where reservationID"
                            + " in (select ReservationID from Reservation where isCanceled ='false')  and (departureDate like '2016-01%')) as a ) where class = 'first') "
                            + "as b join (select sum(fClassPrice)  as p1 from (trainRoute natural join"
                            + " (select * from reserves where reservationID in (select ReservationID from Reservation where isCanceled ='false') and (departureDate like "
                            + "'2016-01%')) as a ) where class = 'second') as c";
                    System.out.println(sql);
                    s[0][0] = "January";
                    System.out.println("test1");
                    s = db.getRevenueReport(sql, s, 0);
                    System.out.println("test1");
                    sql = "select sum(ifnull(p,0) + ifnull(p1,0)) from (select sum(sClassPrice) as p from "
                            + "(trainRoute natural join (select * from reserves where reservationID"
                            + " in (select ReservationID from Reservation where isCanceled ='false')  and (departureDate like '2016-02%')) as a ) where class = 'first') "
                            + "as b join (select sum(fClassPrice)  as p1 from (trainRoute natural join"
                            + " (select * from reserves where reservationID in (select ReservationID from Reservation where isCanceled ='false') and (departureDate like "
                            + "'2016-02%')) as a ) where class = 'second') as c";
                    s[1][0] = "February";
                    s = db.getRevenueReport(sql, s, 1);
                    sql = "select sum(ifnull(p,0) + ifnull(p1,0)) from (select sum(sClassPrice) as p from "
                            + "(trainRoute natural join (select * from reserves where reservationID"
                            + " in (select ReservationID from Reservation where isCanceled ='false')  and (departureDate like '2016-03%')) as a ) where class = 'first') "
                            + "as b join (select sum(fClassPrice)  as p1 from (trainRoute natural join"
                            + " (select * from reserves where reservationID in (select ReservationID from Reservation where isCanceled ='false') and (departureDate like "
                            + "'2016-03%')) as a ) where class = 'second') as c";
                    s[2][0] = "March";
                    s = db.getRevenueReport(sql, s, 2);
                }catch(Exception ee){}
                frame.dispose();
                ViewRevenueReport vrr = new ViewRevenueReport(s);
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
        
        JLabel lblNewLabel_2 = new JLabel("<html><u>View popular route report</u></html>");
        lblNewLabel_2.setBounds(145, 109, 160, 16);
        panel_1.add(lblNewLabel_2);
        lblNewLabel_2.setForeground(SystemColor.textHighlight);
        lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        JButton btnLogout = new JButton("Log out");
        btnLogout.setBounds(179, 215, 92, 29);
        panel_1.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                System.exit(0);
            }
        });
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
                JLabel lblNewLabel = new JLabel("Choose Functionality");
                lblNewLabel.setBounds(150, 26, 150, 19);
                panel_1.add(lblNewLabel);
                lblNewLabel.setForeground(Color.ORANGE);
                lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                s = new Object[9][3];
                try{
                    String sql = "select trainNum, count(trainNum) as a from (reserves  natural joi"
                            + "n reservation) where isCanceled = 'false' and departureDate like "
                            + "'2016-01%' group by trainNum order by a desc;";
                    s[0][0] = "January";
                    s = db.getPopularRouteReport(sql, s, 0);
                    sql = "select trainNum, count(trainNum) as a from (reserves  natural joi"
                            + "n reservation) where isCanceled = 'false' and departureDate like "
                            + "'2016-02%' group by trainNum order by a desc;";
                    s[3][0] = "Feburary";
                    s = db.getPopularRouteReport(sql, s, 3);
                    sql = "select trainNum, count(trainNum) as a from (reserves  natural joi"
                            + "n reservation) where isCanceled = 'false' and departureDate like "
                            + "'2016-03%' group by trainNum order by a desc;";
                    s[6][0] = "March";
                    s = db.getPopularRouteReport(sql, s, 6);
    
                }catch(Exception ee){}
                frame.dispose();
                ViewPopularRouteReport vprr = new ViewPopularRouteReport(s);
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
    }
}
