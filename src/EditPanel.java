import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel {

    private List<Student> data;

    public EditPanel(String filename) {
        data = new ArrayList<>();
        readDataFromFile(filename);
        setLayout(new GridLayout(data.size() + 1, 7));
        add(new JLabel("Name"));
        add(new JLabel("Subject"));
        add(new JLabel("Grade Level"));
        add(new JLabel("Age"));
        add(new JLabel("Price Owed"));
        add(new JLabel("Attendance"));
        add(new JLabel("Apply Changes"));
        for (int i = 0; i < data.size(); i++) {
            JTextField nameField = new JTextField(data.get(i).getName());
            add(nameField);
            JTextField subjectField = new JTextField(data.get(i).getSubject());
            add(subjectField);
            JTextField gradeLevelField = new JTextField(String.valueOf(data.get(i).getGradeLevel()));
            add(gradeLevelField);
            JTextField ageField = new JTextField(String.valueOf(data.get(i).getAge()));
            add(ageField);
            JTextField priceOwedField = new JTextField(String.format("%.2f", data.get(i).getPriceOwed()));
            add(priceOwedField);
            JTextField attendanceField = new JTextField(String.valueOf(data.get(i).getAttendance()));
            add(attendanceField);
            JButton updateButton = new JButton("Update");
            int finalI = i;
            updateButton.addActionListener(e -> {
                data.get(finalI).setName(nameField.getText());
                data.get(finalI).setSubject(subjectField.getText());
                data.get(finalI).setGradeLevel(Integer.parseInt(gradeLevelField.getText()));
                data.get(finalI).setAge(Integer.parseInt(ageField.getText()));
                data.get(finalI).setPriceOwed(Double.parseDouble(priceOwedField.getText()));
                data.get(finalI).setAttendance(Integer.parseInt(attendanceField.getText()));
                writeDataToFile("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
            });
            add(updateButton);
        }
    }

    private void readDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String subject = parts[1];
                int gradeLevel = Integer.parseInt(parts[2]);
                int age = Integer.parseInt(parts[3]);
                double priceOwed = Double.parseDouble(parts[4]);
                int attendance = Integer.parseInt(parts[5]);
                data.add(new Student(name, subject, gradeLevel, age, priceOwed, attendance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : data) {
                bw.write(student.getName() + " " +
                        student.getSubject() + " " +
                        student.getGradeLevel() + " " +
                        student.getAge() + " " +
                        student.getPriceOwed() + " " +
                        student.getAttendance() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
