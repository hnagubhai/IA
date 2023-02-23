import javax.swing.*;
import java.awt.*;

public class InvoicePanel extends JPanel {
    private Student[][] data;

    public InvoicePanel(Student[][] data) {
        this.data = data;
        setLayout(new GridLayout(data.length + 1, 3));
        add(new JLabel("Name"));
        //add(new JLabel("Attendance"));
        add(new JLabel("Price Owed"));
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                add(new JLabel(data[i][j].getName()));
                //add(new JLabel(String.valueOf(data[i][j].getAttendance())));
                double price;
                if (data[i][j].getSubject().equals("Math")) {
                    price = data[i][j].getAttendance() * 20.0;
                } else {
                    price = data[i][j].getAttendance() * 30.0;
                }
                add(new JLabel(String.format("%.2f", price)));
            }
        }
    }
}
