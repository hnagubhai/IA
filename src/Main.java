import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.SwingConstants;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Student[][] data = readDataFromFile("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
        TutoringManager manager = new TutoringManager(data);
        JFrame frame = new JFrame("Tutoring Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> {
            try {
                manager.saveDataToFile();
                JOptionPane.showMessageDialog(frame, "Data saved successfully.", "Save", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Save", JOptionPane.ERROR_MESSAGE);
            }
        });
        fileMenu.add(saveMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewDataMenuItem = new JMenuItem("View Data");
        viewDataMenuItem.addActionListener(e -> {
            ViewPanel viewPanel = new ViewPanel("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
            //viewPanel.refresh();
            JOptionPane.showMessageDialog(frame, viewPanel, "View Data", JOptionPane.PLAIN_MESSAGE);
        });
        viewMenu.add(viewDataMenuItem);
        menuBar.add(viewMenu);
        JMenu editMenu = new JMenu("Edit");
        JMenuItem editDataMenuItem = new JMenuItem("Edit Data");
        editDataMenuItem.addActionListener(e -> {
            EditPanel editPanel = new EditPanel("/Users/hnagubhai/IdeaProjects/IA/src/data.txt");
            JOptionPane.showMessageDialog(frame, editPanel, "Edit Data", JOptionPane.PLAIN_MESSAGE);
        });
        editMenu.add(editDataMenuItem);

        JMenuItem addStudentMenuItem = new JMenuItem("Add Student");
        addStudentMenuItem.addActionListener(e -> {
            AddStudentPanel addStudentPanel = new AddStudentPanel(manager);
            JOptionPane.showMessageDialog(frame, addStudentPanel, "Add Student", JOptionPane.PLAIN_MESSAGE);
        });
        editMenu.add(addStudentMenuItem);

        JMenuItem removeStudentMenuItem = new JMenuItem("Remove Student");
        removeStudentMenuItem.addActionListener(e -> {
            RemoveStudentPanel removeStudentPanel = new RemoveStudentPanel(manager);
            JOptionPane.showMessageDialog(frame, removeStudentPanel, "Remove Student", JOptionPane.PLAIN_MESSAGE);
        });
        editMenu.add(removeStudentMenuItem);

        menuBar.add(editMenu);
        JMenu sortMenu = new JMenu("Sort");
        JMenuItem sortDataMenuItem = new JMenuItem("Sort Data");
        sortDataMenuItem.addActionListener(e -> {
            SortPanel sortPanel = new SortPanel(manager);
            JOptionPane.showMessageDialog(frame, sortPanel, "Sort Data", JOptionPane.PLAIN_MESSAGE);
        });
        sortMenu.add(sortDataMenuItem);
        menuBar.add(sortMenu);
        JMenu invoiceMenu = new JMenu("Invoice");
        JMenuItem getInvoiceMenuItem = new JMenuItem("Get Invoice");
        getInvoiceMenuItem.addActionListener(e -> {
            InvoicePanel invoicePanel = new InvoicePanel(data);
            JOptionPane.showMessageDialog(frame, invoicePanel, "Invoice", JOptionPane.PLAIN_MESSAGE);
        });
        invoiceMenu.add(getInvoiceMenuItem);
        menuBar.add(invoiceMenu);
        frame.setJMenuBar(menuBar);
        frame.add(new JLabel("Welcome to the Tutoring Manager"), BorderLayout.NORTH);
        frame.pack();
        frame.pack();
        frame.setSize(new Dimension(800, 450));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Student[][] readDataFromFile(String fileName) {
        List<List<Student>> dataList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                List<Student> row = new ArrayList<>();
                for (int i = 0; i < tokens.length; i += 6) {
                    String name = tokens[i];
                    String subject = tokens[i+1];
                    int grade = Integer.parseInt(tokens[i+2]);
                    int month = Integer.parseInt(tokens[i+3]);
                    double rate = Double.parseDouble(tokens[i+4]);
                    int hours = Integer.parseInt(tokens[i+5]);
                    row.add(new Student(name, subject, grade, month, rate, hours));
                }
                dataList.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Student[][] data = new Student[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i).toArray(new Student[0]);
        }
        return data;
    }
}
