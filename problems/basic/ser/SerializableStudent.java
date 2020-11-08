package basic.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableStudent implements Serializable{
  private transient Student student;

  public SerializableStudent(Student student){
    this.student= student;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    out.writeObject(student.getName());
    out.writeObject(student.getAge());
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    String name = (String) in.readObject();
    Integer age = (Integer) in.readObject();
    student = new Student(name, age);
  }


}
