import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.io.PrintWriter;
import java.io.IOException;

public class TutoringManager {
    private Student[][] data;
    private Student[][] studentData;
    private String fileName;

    public TutoringManager(String fileName) throws IOException {
        this.fileName = fileName;
        this.data = readDataFromFile();
    }

    public TutoringManager(Student[][] data) {
        //note: this line is the one that finally made writing to file work!
        fileName = "/Users/hnagubhai/IdeaProjects/IA/src/data.txt";
        //
        this.data = data;
    }

    public Student[][] readDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int numRows = 0;
        int numCols = 0;
        while ((line = reader.readLine()) != null) {
            numRows++;
            String[] tokens = line.split(" ");
            numCols = Math.max(numCols, tokens.length);
        }
        reader.close();
        Student[][] data = new Student[numRows][numCols];
        reader = new BufferedReader(new FileReader(fileName));
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            for (int col = 0; col < tokens.length; col++) {
                String[] fields = tokens[col].split(" ");
                String name = fields[0];
                String subject = fields[1];
                int grade = Integer.parseInt(fields[2]);
                int attendance = Integer.parseInt(fields[3]);
                double gradeValue = Double.parseDouble(fields[4]);
                int numHours = Integer.parseInt(fields[5]);
                data[row][col] = new Student(name, subject, grade, attendance, gradeValue, numHours);
            }
            row++;
        }
        reader.close();
        return data;
    }

    public Student[][] getStudentData() {
        return studentData;
    }

    public Student[][] getData() {
        return data;
    }

    public void setData(Student[][] data) {
        this.data = data;
    }

    public void sortData(Comparator<Student> comparator) {
        for (Student[] row : data) {
            Arrays.sort(row, comparator);
        }
    }

    public double getInvoice(int row, int col) {
        double price = 0.0;
        if (data[row][col].getSubject().equals("math")) {
            price = data[row][col].getAttendance() * 20.0;
        } else if (data[row][col].getSubject().equals("physics")) {
            price = data[row][col].getAttendance() * 30.0;
        }
        data[row][col].setPriceOwed(price);
        return price;
    }

    public void saveDataToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        for (Student[] row : data) {
            for (Student student : row) {
                pw.print(student.getName() + " ");
                pw.print(student.getSubject() + " ");
                pw.print(student.getGradeLevel() + " ");
                pw.print(student.getAge() + " ");
                pw.print(student.getPriceOwed() + " ");
                pw.print(student.getAttendance() + "");
            }
            pw.println();
        }
        pw.flush();
        pw.close();
    }


    public void addStudent(String name, String subject, int grade, int age, double priceOwed, int attendance) {

        int temp = data.length;

            int tempData = temp + 1;
            Student[][] newData = new Student[tempData][];
            System.arraycopy(data, 0, newData, 0, temp);
            newData[tempData - 1] = new Student[1];
            newData[tempData - 1][0] = new Student(name, subject, grade, age, priceOwed, attendance);
            data = newData;



    }





}
