package hello.java.lang.oo;

import java.util.ArrayList;
import java.util.List;

class Level {
    public List<Student> students;
    public String Name;
    public int age;
}

class Student {
    public String Name;
    public int age;
}

public class ModifyA {

    public static void modify1(List<Level> levels) {
        for (int i = 0; i < levels.size(); i++) {
            Level level = levels.get(i);
            level.Name = "level" + Integer.toString(i+i);
            level.age = i + i + 1;
            List<Student> students = level.students;
            for (int j = 0; j < students.size(); j++) {
                Student student = students.get(j);
                student.Name = "student" + Integer.toString(j+j);
                student.age = j * j * j + 1;
            }
        }
    }

    public static void helloModify1() {
        List<Level> levels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Level level = new Level();
            level.Name = "level" + Integer.toString(i);
            level.age = i + 1;
            level.students = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Student student = new Student();
                student.Name = "student" + Integer.toString(j);
                student.age = j * j + 1;
                level.students.add(student);
            }
            levels.add(level);
        }

        System.out.println(levels);

        modify1(levels);

        System.out.println(levels);
    }

}
