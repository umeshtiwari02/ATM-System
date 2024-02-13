import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");

        setLayout(null);

        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 380, 200);
        add(mini);

        JLabel bank = new JLabel("Sunrise Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel balance = new JLabel();
        add(balance);

        try {
            Conn conn = new Conn();
            ResultSet result = conn.s.executeQuery("select * from login where pin = '" + pinnumber+"'");

            while (result.next()) {
                card.setText("Card Number: " + result.getString("cardnumber").substring(0, 4) + "xxxxxxxx" + result.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            int bal = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current balance is Rs " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        balance.setBounds(20, 400, 400, 200);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinnumber+"'");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        setSize(450, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
