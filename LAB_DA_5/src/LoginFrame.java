import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.security.NoSuchAlgorithmException;

//import finalhybrid.AES;

public class LoginFrame extends JFrame implements ActionListener {
	
	AES AES = new AES();
	String EncryptedMessage;
	//EncryptedMessage=AES.encrypt(passwordField.getText(),userTextField.getText()); 
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel emailLabel = new JLabel("ENTER EMAIL");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailTextField = new JTextField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    


    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        emailLabel.setBounds(30, 250, 100, 30);
        
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 200, 150, 30);
        emailTextField.setBounds(120, 250, 245, 60);
        
        loginButton.setBounds(50, 350, 100, 30);
        resetButton.setBounds(200, 350, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(emailLabel);
        container.add(emailTextField);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            EncryptedMessage=AES.encrypt(passwordField.getText(),userTextField.getText());
            if (userText.equalsIgnoreCase("18BIT0291") && pwdText.equalsIgnoreCase("sangramsinh")  ) {
                JOptionPane.showMessageDialog(this, " A new login is created for you 18BIT0291");
                try {
                	Class.forName("com.mysql.jdbc.Driver");
                	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DA5","root","");
                	PreparedStatement ps = conn.prepareStatement("insert into User(userTextField,passwordField,emailTextField,EncryptedMessage) values(?,?,?,?)");
                	ps.setString(1, userTextField.getText());
                	ps.setString(2, passwordField.getText());
                
                	ps.setString(3, emailTextField.getText());
                	ps.setString(4,EncryptedMessage.toString());
                	
                	int x=ps.executeUpdate();
                	if(x>0){
                		System.out.println("added succesfully with "+x+" number of rows");
                	}
                	else {
                		System.out.println("check connection with web server");
                	}
                	
                	
                }catch(Exception e1) {
                	System.out.println(e1);
                }
            } 
            
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
       
        
        
        
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

    }

}