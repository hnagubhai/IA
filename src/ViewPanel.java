import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewPanel extends JPanel {
    private List<Student> students;

    public ViewPanel(String s) {
        students = readDataFromFile("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
        setLayout(new GridLayout(students.size() + 1, 6));
        add(new JLabel("Name"));
        add(new JLabel("Subject"));
        add(new JLabel("Grade Level"));
        add(new JLabel("Age"));
        add(new JLabel("Price Owed"));
        add(new JLabel("Attendance"));
        for (Student student : students) {
            add(new JLabel(student.getName()));
            add(new JLabel(student.getSubject()));
            add(new JLabel(String.valueOf(student.getGradeLevel())));
            add(new JLabel(String.valueOf(student.getAge())));
            add(new JLabel(String.format("%.2f", student.getPriceOwed())));
            add(new JLabel(String.valueOf(student.getAttendance())));
        }
    }

    public List<Student> readDataFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(" ");
                String name = fields[0];
                String subject = fields[1];
                int gradeLevel = Integer.parseInt(fields[2]);
                int age = Integer.parseInt(fields[3]);
                double priceOwed = Double.parseDouble(fields[4]);
                int attendance = Integer.parseInt(fields[5]);
                students.add(new Student(name, subject, gradeLevel, age, priceOwed, attendance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}


