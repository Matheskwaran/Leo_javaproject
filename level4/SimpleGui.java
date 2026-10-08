
package level4;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleGui extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField t1, t2, t3;
    JButton b1, b2, b3, b4, b5;

    SimpleGui() {

        setLayout(null);
        setTitle("Leo Application");

        l1 = new JLabel("Enter RegisterNumber : ");
        l2 = new JLabel("Enter Student Name : ");
        l3 = new JLabel("Enter Mark : ");

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);

        b1 = new JButton("Search");
        b2 = new JButton("Insert");
        b3 = new JButton("Delete");
        b4 = new JButton("Update");
        b5 = new JButton("Clear");

        l1.setBounds(100, 100, 200, 30);
        l2.setBounds(100, 150, 200, 30);
        l3.setBounds(100, 200, 200, 30);

        t1.setBounds(300, 100, 200, 30);
        t2.setBounds(300, 150, 200, 30);
        t3.setBounds(300, 200, 200, 30);

        b1.setBounds(100, 270, 150, 40);
        b2.setBounds(270, 270, 150, 40);
        b3.setBounds(440, 270, 150, 40);
        b4.setBounds(190, 330, 150, 40);
        b5.setBounds(370, 330, 150, 40);

        add(l1); add(l2); add(l3);
        add(t1); add(t2); add(t3);
        add(b1); add(b2); add(b3); add(b4); add(b5);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        // SEARCH BUTTON
        if (e.getSource() == b1) {
            if (t1.getText().length() > 0) {
                try {
                    int rno = Integer.parseInt(t1.getText());
                    search(rno);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this, "Enter valid register number");
                }
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "Must enter the reg no");
            }
        }

        // INSERT BUTTON
        if (e.getSource() == b2) {
            try {
                int rno = Integer.parseInt(t1.getText());
                String sname = t2.getText();
                float mark = Float.parseFloat(t3.getText());

                insert(rno, sname, mark);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this, "Enter valid register number and mark");
            }
        }

        // DELETE BUTTON
        if (e.getSource() == b3) {
            if (t1.getText().length() > 0) {
                try {
                    int rno = Integer.parseInt(t1.getText());
                    delete(rno);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this, "Enter valid register number");
                }
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "Must enter the reg no");
            }
        }

        // UPDATE BUTTON
        if (e.getSource() == b4) {
            if (t1.getText().length() > 0) {
                try {
                    int rno = Integer.parseInt(t1.getText());
                    String sname = t2.getText();
                    float mark = Float.parseFloat(t3.getText());

                    update(rno, sname, mark);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this, "Enter valid register number and mark");
                }
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "Must enter the reg no");
            }
        }

        // CLEAR BUTTON
        if (e.getSource() == b5) {
            clear();
        }
    }

    // DATABASE CONNECTION
    Statement dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chettinad",
                "root",
                "Mathesh@1303"
            );

            Statement st = con.createStatement();
            return st;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
            return null;
        }
    }

    // SEARCH FUNCTION
    void search(int rno) {
        try {
            Statement st = dbConnection();

            ResultSet rs = st.executeQuery(
                "select * from student where regno=" + rno
            );

            if (rs.next()) {
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
            }
            else {
                JOptionPane.showMessageDialog(
                    this, rno + " is not found in DB");
            }

            rs.close();
            st.getConnection().close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    // INSERT FUNCTION
    void insert(int rno, String sname, float mark) {
        try {
            Statement st = dbConnection();

            int result = st.executeUpdate(
                "insert into student values(" +
                rno + ",'" + sname + "'," + mark + ")"
            );

            if (result > 0) {
                JOptionPane.showMessageDialog(
                    this, "Successfully inserted");
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "No records inserted");
            }

            st.getConnection().close();
            clear();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    // DELETE FUNCTION
    void delete(int rno) {
        try {
            Statement st = dbConnection();

            int result = st.executeUpdate(
                "delete from student where regno=" + rno
            );

            if (result > 0) {
                JOptionPane.showMessageDialog(
                    this, "Successfully deleted");
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "Record not found");
            }

            st.getConnection().close();
            clear();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    // UPDATE FUNCTION
    void update(int rno, String sname, float mark) {
        try {
            Statement st = dbConnection();

            int result = st.executeUpdate(
                "update student set sname='" + sname +
                "', mark=" + mark +
                " where regno=" + rno
            );

            if (result > 0) {
                JOptionPane.showMessageDialog(
                    this, "Successfully updated");
            }
            else {
                JOptionPane.showMessageDialog(
                    this, "Record not found");
            }

            st.getConnection().close();
            clear();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    // CLEAR FUNCTION
    void clear() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t1.requestFocus();
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SimpleGui f1 = new SimpleGui();

        f1.setSize(700, 500);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
    }
}
