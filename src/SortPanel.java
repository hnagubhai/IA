import java.util.Comparator;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SortPanel extends JPanel {
    private Student[][] studentData;
    private String[] columnNames = {"Subject", "Grade Level", "Age", "Price Owed", "Attendance"};
    private JTable table;
    private JScrollPane scrollPane;
    private JButton bubbleSortButton;
    private JButton selectionSortButton;
    private JButton insertionSortButton;
    private JComboBox<String> comboBox;
    private JLabel label;

    public SortPanel(TutoringManager manager) {
        this.studentData = manager.getStudentData();
        setLayout(new BorderLayout());

        // Create table and add it to scroll pane
        table = new JTable(studentData, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons and add action listeners
        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comparator<Student> comparator = null;
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        comparator = new SubjectComparator();
                        break;
                    case 1:
                        comparator = new AgeComparator();
                        break;
                    case 2:
                        comparator = new GradeLevelComparator();
                        break;
                }
                bubbleSort(studentData[0], comparator);
                updateTable();
            }
        });

        selectionSortButton = new JButton("Selection Sort");
        selectionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comparator<Student> comparator = null;
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        comparator = new SubjectComparator();
                        break;
                    case 1:
                        comparator = new AgeComparator();
                        break;
                    case 2:
                        comparator = new GradeLevelComparator();
                        break;
                }
                selectionSort(studentData[0], comparator);
                updateTable();
            }
        });

        insertionSortButton = new JButton("Insertion Sort");
        insertionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comparator<Student> comparator = null;
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        comparator = new SubjectComparator();
                        break;
                    case 1:
                        comparator = new AgeComparator();
                        break;
                    case 2:
                        comparator = new GradeLevelComparator();
                        break;
                }
                insertionSort(studentData[0], comparator);
                updateTable();
            }
        });

        // Create combo box and label
        String[] options = {"Sort by Name", "Sort by Age", "Sort by Grade Level"};
        comboBox = new JComboBox<>(options);
        label = new JLabel("Sort By:");

        // Create panel for buttons, combo box, and label
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(selectionSortButton);
        buttonPanel.add(insertionSortButton);
        buttonPanel.add(label);
        buttonPanel.add(comboBox);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Updates the table with the new data
    public void updateTable() {
        table = new JTable(studentData, columnNames);
        scrollPane.setViewportView(table);
    }

    // Bubble sort implementation
    public static void bubbleSort(Student[] arr, Comparator<Student> comparator) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    public static void selectionSort(Student[] arr, Comparator<Student> comparator) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(arr[j], arr[min_idx]) < 0) {
                    min_idx = j;
                }
            }
            swap(arr, min_idx, i);
        }
    }

    public static void swap(Student[] students, int i, int j) {
        Student temp = students[i];
        students[i] = students[j];
        students[j] = temp;
    }


    public static void insertionSort(Student[] arr, Comparator<Student> comparator) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}

