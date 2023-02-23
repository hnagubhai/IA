import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudentPanel extends JPanel implements ActionListener {
    private JTextField nameField;
    private JTextField subjectField;
    private JTextField gradeField;
    private JTextField ageField;
    private JTextField priceOwedField;
    private JTextField attendanceField;
    private TutoringManager manager;

    public AddStudentPanel(TutoringManager manager) {
        this.manager = manager;

        setLayout(new GridLayout(7, 2));
        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);
        add(new JLabel("Subject:"));
        subjectField = new JTextField(20);
        add(subjectField);
        add(new JLabel("Grade:"));
        gradeField = new JTextField(20);
        add(gradeField);
        add(new JLabel("Age:"));
        ageField = new JTextField(20);
        add(ageField);
        add(new JLabel("Price Owed:"));
        priceOwedField = new JTextField(20);
        add(priceOwedField);
        add(new JLabel("Attendance:"));
        attendanceField = new JTextField(20);
        add(attendanceField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String subject = subjectField.getText();
        int grade = Integer.parseInt(gradeField.getText());
        int age = Integer.parseInt(ageField.getText());
        double priceOwed = Double.parseDouble(priceOwedField.getText());
        int attendance = Integer.parseInt(attendanceField.getText());

        // create student object
        Student student = new Student(name, subject, grade, age, priceOwed, attendance);

        // save to file
        try {
            PrintWriter out = new PrintWriter(new FileWriter("/Users/hnagubhai/IdeaProjects/IA/src/data.txt", true));
            out.println(student.toString());
            out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving student data to file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // add student to manager
        manager.addStudent(name, subject, grade, age, priceOwed, attendance);
        manager.setData(manager.getData());

        // show success message
        JOptionPane.showMessageDialog(this, "Student added successfully.", "Add Student", JOptionPane.INFORMATION_MESSAGE);
    }
}
