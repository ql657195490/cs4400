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
    public static String month1;
    public static String month2;
    public static String month3;
    public static int current;
    public static int date2;
    public static int date3;
    public int year;
    public MonthConverter mc;

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
        month1 = "";
        month2 = "";
        month3 = "";
        date2 = 0;
        date3 = 0;
        //month = now.get(Calendar.MONTH);
        mc = new MonthConverter();
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
        
        
        now = Calendar.getInstance();
        current = now.get(Calendar.MONTH);
        year = now.get(Calendar.YEAR);
        if(current == 2){
            date2 = 1;
            date3 = 12;
        }else if(current == 1){
            date2 = 12;
            date3 = 11;
        }else{
            date2 = current - 1;
            date3 = current - 2;
        }
        if (current < 10){
            month1 = "-0";
        }else{
            month1 = "-";
        }
        if (date2 < 10){
            month2 = "-0";
        }else{
            month2 = "-";
        }
        if (date3 < 10){
            month3 = "-0";
        }else{
            month3 = "-";
        }
        
        
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
                    
                    String sql = "SELECT sum(ifnull(p1, 0) + ifnull(p2,0) + ifnull(p3, 0) + ifnull(p4, 0) + ifnull(p5, 0))FROM (SELECT sum(fClassPrice) as p1 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date3) + "-%' and class = 'first' AND isStudent = 'false')as a1"
                            + " JOIN(SELECT sum(sClassPrice)  as p2 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month3 + String.valueOf(date3) + "-%' and class = 'second' AND isStudent = 'false') as a2 "
                            + " JOIN (SELECT sum(fClassPrice) * 0.8 as p3 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month3 + String.valueOf(date3) + "-%' and class = 'first' AND isStudent = 'true')as a3"
                            + " JOIN (SELECT sum(sClassPrice) * 0.8  as p4 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month3 + String.valueOf(date3) + "-%' and class = 'second' AND isStudent = 'true') as a4"
                            + " JOIN (SELECT sum(numOfBaggages - 2) * 30 as p5 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month3 + String.valueOf(date3) + "-%' AND numOfBaggages > 2) as a5";
                    s[0][0] = mc.managerMonth(String.valueOf(date3));
                    s = db.getRevenueReport(sql, s, 0);
                    sql = "SELECT sum(ifnull(p1, 0) + ifnull(p2,0) + ifnull(p3, 0) + ifnull(p4, 0) + ifnull(p5, 0))FROM (SELECT sum(fClassPrice) as p1 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' and class = 'first' AND isStudent = 'false')as a1"
                            + " JOIN(SELECT sum(sClassPrice)  as p2 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' and class = 'second' AND isStudent = 'false') as a2 "
                            + " JOIN (SELECT sum(fClassPrice) * 0.8 as p3 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' and class = 'first' AND isStudent = 'true')as a3"
                            + " JOIN (SELECT sum(sClassPrice) * 0.8  as p4 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' and class = 'second' AND isStudent = 'true') as a4"
                            + " JOIN (SELECT sum(numOfBaggages - 2) * 30 as p5 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' AND numOfBaggages > 2) as a5";
                    s[1][0] = mc.managerMonth(String.valueOf(date2));
                    s = db.getRevenueReport(sql, s, 1);
                    sql = "SELECT sum(ifnull(p1, 0) + ifnull(p2,0) + ifnull(p3, 0) + ifnull(p4, 0) + ifnull(p5, 0))FROM (SELECT sum(fClassPrice) as p1 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' and class = 'first' AND isStudent = 'false')as a1"
                            + " JOIN(SELECT sum(sClassPrice)  as p2 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' and class = 'second' AND isStudent = 'false') as a2 "
                            + " JOIN (SELECT sum(fClassPrice) * 0.8 as p3 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' and class = 'first' AND isStudent = 'true')as a3"
                            + " JOIN (SELECT sum(sClassPrice) * 0.8  as p4 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' and class = 'second' AND isStudent = 'true') as a4"
                            + " JOIN (SELECT sum(numOfBaggages - 2) * 30 as p5 FROM reservation natural join trainRoute natural join reserves NATURAL JOIN customer WHERE isCanceled = 'false' and departureDate like '" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' AND numOfBaggages > 2) as a5";
                    s[2][0] = mc.managerMonth(String.valueOf(current));

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
                            + "'" +  String.valueOf(year) + month3 + String.valueOf(date3) + "%' group by trainNum order by a desc;";
                    s[0][0] = mc.managerMonth(String.valueOf(date3));
                    s = db.getPopularRouteReport(sql, s, 0);
                    sql = "select trainNum, count(trainNum) as a from (reserves  natural joi"
                            + "n reservation) where isCanceled = 'false' and departureDate like "
                            + "'" +  String.valueOf(year) + month2 + String.valueOf(date2) + "-%' group by trainNum order by a desc;";
                    s[3][0] = mc.managerMonth(String.valueOf(date2));;
                    s = db.getPopularRouteReport(sql, s, 3);
                    sql = "select trainNum, count(trainNum) as a from (reserves  natural joi"
                            + "n reservation) where isCanceled = 'false' and departureDate like "
                            + "'" +  String.valueOf(year) + month1 + String.valueOf(current) + "-%' group by trainNum order by a desc;";
                    s[6][0] = mc.managerMonth(String.valueOf(current));
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
