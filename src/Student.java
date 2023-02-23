public class Student {
    private String name;
    private String subject;
    private int gradeLevel;
    private int age;
    private double priceOwed;
    private int attendance;

    public Student(String name, String subject, int gradeLevel, int age, double priceOwed, int attendance) {
        this.name = name;
        this.subject = subject;
        this.gradeLevel = gradeLevel;
        this.age = age;
        this.priceOwed = priceOwed;
        this.attendance = attendance;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPriceOwed() {
        return priceOwed;
    }

    public void setPriceOwed(double priceOwed) {
        this.priceOwed = priceOwed;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return name + " " + subject + " " + gradeLevel + " " + age + " " + priceOwed + " " + attendance;
    }

}
