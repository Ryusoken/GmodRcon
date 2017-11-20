package de.ryusoken.rcon;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame {

    JLabel hostlbl = new JLabel("Host:");
    JLabel portlbl = new JLabel("Port:");
    JLabel passwordlbl = new JLabel("Password:");
    JTextField host = new JTextField(1);
    JTextField port = new JTextField(1);
    JTextField password = new JTextField(1);
    JButton login = new JButton();

     public Login () {
         initialize();
         this.setTitle("Garry's Mod Rcon Client | Login");
         this.setSize(400,220);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);
         this.setLayout(null);
         this.setResizable(false);
         this.add(port);
         this.add(host);
         this.add(password);
         this.add(hostlbl);
         this.add(portlbl);
         this.add(passwordlbl);
         this.add(login);
         this.setVisible(true);
     }

     public void initialize() {
         hostlbl.setBounds(20,10,100,10);
         hostlbl.setVisible(true);
         host.setBounds(20,30,300,20);
         host.setVisible(true);
         portlbl.setBounds(20,55,100,10);
         portlbl.setVisible(true);
         port.setBounds(20,70,300,20);
         port.setVisible(true);
         passwordlbl.setBounds(20,95,100,10);
         passwordlbl.setVisible(true);
         password.setBounds(20,110,300,20);
         password.setVisible(true);
         login.setText("Login");
         login.setBounds(20,140,150,30);
         login.setVisible(true);
         onLogin();
     }

     public void onLogin() {
         login.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                Var.RconHost = host.getText();
                Var.RconPort = port.getText();
                Var.RconPassword = password.getText();
                 try {
                     Var.rcon = new Rcon(Var.RconHost, Integer.parseInt(Var.RconPort), Var.RconPassword.getBytes());
                     Var.rcon.command("say Connected!");
                     dispose();
                     Var.rcon.disconnect();
                     new Gui();
                 }
                 catch (AuthenticationException ex) {
                     System.out.println(ex.getCause());
                 }
                 catch (IOException ex){
                     System.out.println(ex.getCause());
                 }
                 catch (NumberFormatException ex) {
                     System.out.println(ex.getCause());
                 }
             }
         });
     }
}
