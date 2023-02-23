import java.util.Comparator;

public class SubjectComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getSubject().compareTo(s2.getSubject());
    }
}