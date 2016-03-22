import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MakeReservation_3 {

    private JFrame frame;
    private JTable table;
    private JTextField textField;
    public ArrayList list;

    /**
     * Launch the application.
     */
    public static void mrWindow_3() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_3 window = new MakeReservation_3();
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
    public MakeReservation_3() {
        initialize();
    }
    
    public MakeReservation_3(ArrayList list){
        this.list = list;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 950, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(950, 90));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblMakeReservation = new JLabel("Make Reservation");
        lblMakeReservation.setForeground(Color.ORANGE);
        lblMakeReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblMakeReservation.setBounds(410, 20, 130, 20);
        panel.add(lblMakeReservation);
        
        JLabel lblNewLabel = new JLabel("Currently Selected");
        lblNewLabel.setBounds(25, 60, 130, 20);
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(950,200));
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Student discount aplied or not");
        lblNewLabel_1.setBounds(25, 20, 190, 20);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblTotalCost = new JLabel("Total cost");
        lblTotalCost.setBounds(25, 60, 80, 20);
        panel_1.add(lblTotalCost);
        
        textField = new JTextField();
        textField.setBounds(267, 56, 134, 28);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Use card");
        lblNewLabel_2.setBounds(25, 100, 61, 20);
        panel_1.add(lblNewLabel_2);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(267, 96, 52, 27);
        panel_1.add(comboBox);
        
        JLabel lblAddCard = new JLabel("<html><u>Add card</u></html>");
        lblAddCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        lblAddCard.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
        lblAddCard.setBounds(351, 102, 61, 16);
        panel_1.add(lblAddCard);
        
        JLabel lblContinueToAdding = new JLabel("<html><u>Continue adding a train</u></html>");
        lblContinueToAdding.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblContinueToAdding.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        lblContinueToAdding.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
        lblContinueToAdding.setBounds(25, 140, 160, 20);
        panel_1.add(lblContinueToAdding);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(98, 165, 100, 29);
        panel_1.add(btnBack);
        
        JButton btnSumbit = new JButton("Sumbit");
        btnSumbit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSumbit.setBounds(362, 165, 100, 29);
        panel_1.add(btnSumbit);
        
        JScrollPane scrollPane;
        
        JButton button1 = new JButton("Remove");
        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "Departs From", "Arrives At", "Class"
                , "Price", "# of Baggages", "Passenger Name", "Remove"};
        Object[][] s1 = {//user for test
                {1,"Click" , 1, 1, 1, 1, 1, 1, "button"},
               
                {2, "test", 2, 2, 2, 2, 2, 2, "button"}
                
        };
        table = new JTable(s1, s);
//        table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
//        table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));
//        table.getColumn("button").setCellRenderer(new ButtonRenderer());
//        table.getColumn("button").setCellEditor(new ButtonEditor(new JTextField()));
//        table.getColumnModel().getColumn(1).setCellEditor(new MyRender());//设置编辑器
//        table.getColumnModel().getColumn(1).setCellRenderer(new MyRender() );
        
       
        //table.setCellEditor(new DefaultCellEditor(button1));
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(0,25 , 0,25));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
    }

}

class ButtonRenderer extends JButton implements TableCellRenderer{

    public ButtonRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
    
}

class ButtonEditor extends DefaultCellEditor{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    
    public ButtonEditor(JTextField txt){
        super(txt);
        btn = new JButton();
        btn.setOpaque(true);
   
    
        btn.addActionListener(new ActionListener(){
    
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                fireEditingStopped();
                
            }
            
        });
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected,
            int row, int col){
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        clicked = true;
        return btn;
    }
    
    @Override
    public Object getCellEditorValue(){
        if (clicked){
            System.out.println("test successful");
        }
        clicked = false;
        return new String(lbl);
    }
   
    @Override
    public boolean stopCellEditing(){
        clicked = false;
        return super.stopCellEditing();
    }
    
    @Override
    protected  void fireEditingStopped(){
        super.fireEditingStopped();
    }
}

class MyRender extends AbstractCellEditor implements TableCellRenderer,ActionListener, TableCellEditor{

    private static final long serialVersionUID = 1L;
    private JButton button =null;
    public MyRender(){
        button = new JButton("确定不？");
        button.addActionListener(this);
    }

@Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }

@Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "渲染器学期", "消息", JOptionPane.OK_OPTION);
        
    }

@Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
    
}
