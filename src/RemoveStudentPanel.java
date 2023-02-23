import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class RemoveStudentPanel extends JPanel implements ActionListener {
    private JLabel label;
    private JComboBox<String> studentList;
    private JButton removeButton;

    public RemoveStudentPanel(TutoringManager manager) {
        setLayout(new GridLayout(2, 1));

        // Create label and add to panel
        label = new JLabel("Select student to remove:");
        add(label);

        // Create student list combo box and add to panel
        studentList = new JComboBox<>(getStudents());
        add(studentList);

        // Create remove button and add to panel
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        add(removeButton);
    }

    private String[] getStudents() {
        // Read students from data.txt file and add to array
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            java.util.List<String> students = new java.util.ArrayList<String>();
            while ((line = br.readLine()) != null) {
                students.add(line);
            }
            return students.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle remove button click
        if (e.getSource() == removeButton) {
            String selectedStudent = (String) studentList.getSelectedItem();
            removeStudent(selectedStudent);
            studentList.removeItem(selectedStudent);
        }
    }

    private void removeStudent(String student) {
        // Remove student from data.txt file
        try {
            File inputFile = new File("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
            File tempFile = new File("data_temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String lineToRemove = student;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
