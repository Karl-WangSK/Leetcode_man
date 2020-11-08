package basic.ser;

import java.io.*;

public class Test {
  public static void main(String[] args) throws Exception {
    Student student = new Student("stu", 5);
    SerializableStudent serStudent = new SerializableStudent(student);

    FileOutputStream outStream = new FileOutputStream("student.txt");
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);

    objectOutputStream.writeObject(serStudent);
    outStream.close();
    System.out.println("successful");

    FileInputStream inStream = new FileInputStream("student.txt");
    ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
    SerializableStudent deserStu= (SerializableStudent)objectInputStream.readObject();
    inStream.close();

    System.out.println(deserStu.getStudent().getAge());
  }
}
