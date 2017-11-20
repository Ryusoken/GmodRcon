package de.ryusoken.rcon;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JFrame {

    JPanel panel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JLabel commandlbl = new JLabel("Command");
    JButton commandbtn = new JButton();
    JTextField command = new JTextField(1);
    JLabel playername = new JLabel("Player Name: ");
    JTextField player = new JTextField(1);

    // Kill Command ULX Needed!
    JLabel killCommand = new JLabel("Kill a Player");
    JButton killButton = new JButton("Kill!");

    // Jail Command ULX Needed!
    JLabel jailCommand = new JLabel("Jail a Player");
    JButton jailButton = new JButton("Jail");

    public Gui() {
        initialize();
        this.setTitle("Garry's Mod Rcon Client");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(commandlbl);
        this.add(command);
        this.add(commandbtn);
        this.add(playername);
        this.add(player);
        this.setVisible(true);
    }

    public void initialize() {
        commandlbl.setText("Command: ");
        commandlbl.setBounds(5,5,150,20);
        commandlbl.setVisible(true);
        command.setBounds(100,5,400,20);
        command.setVisible(true);
        commandbtn.setText("Execute");
        commandbtn.setBounds(520,5,100,20);
        onExecute();
        commandbtn.setVisible(true);
        playername.setBounds(5,100,100,20);
        playername.setVisible(true);
        player.setBounds(100,100,400,20);
        player.setVisible(true);
    }

    public String execute(String command) {
        try {
            Var.rcon = new Rcon(Var.RconHost, Integer.parseInt(Var.RconPort), Var.RconPassword.getBytes());
            String result = Var.rcon.command(command);
            Var.rcon.disconnect();
            return result;
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
        return "Error";
    }

    public void onExecute() {
        commandbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute(command.getText());

            }
        });
    }
}