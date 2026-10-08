package level4;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Leologinform extends JFrame implements ActionListener {
    JLabel tit, l1, l2;
    JTextField t1;
    JPasswordField p1;
    JButton b1, b2;

    Font myfont = new Font("arial", Font.BOLD, 35);

    Leologinform() {

        setTitle("leo Login Form");
        setLayout(null);

        tit = new JLabel("Login Form");
        l1 = new JLabel("Enter Username : ");
        l2 = new JLabel("Enter Password : ");

        t1 = new JTextField();
        p1 = new JPasswordField();

        b1 = new JButton("Login/SignIn");
        b2 = new JButton("Reset");

        tit.setBounds(100, 100, 250, 50);
        l1.setBounds(100, 200, 150, 30);
        l2.setBounds(100, 250, 150, 30);

        t1.setBounds(250, 200, 150, 30);
        p1.setBounds(250, 250, 150, 30);

        b1.setBounds(250, 300, 130, 30);
        b2.setBounds(450, 300, 100, 30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);

        tit.setFont(myfont);

        add(tit); add(l1); add(l2); add(t1); add(p1); add(b1); add(b2);
        
    }

    public static void main(String[] args) {

        JFrame f1 = new Leologinform();

        f1.setSize(700, 550);
        f1.setVisible(true);
        f1.setEnabled(true);
    }
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == b1) {
    		if(t1.getText().equals("leo") && p1.getText().equals("leo@123")) {
    			JOptionPane.showMessageDialog(this, "Login Successful");
    			this.setVisible(false);
    			JFrame obj = new SimpleGui();
                obj.setSize(700, 500);
                obj.setVisible(true);
    		}
    		else {
    			JOptionPane.showMessageDialog(this, "Invalid User!!!");
    		}
    	}
    	else {
    		t1.setText("");
    		p1.setText("");;
    		t1.requestFocus();
    	}

    }
}