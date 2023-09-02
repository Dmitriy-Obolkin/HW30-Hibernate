package ua.ithillel.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message){
        super(message);
    }

    public StudentNotFoundException(int studentId){
        super("Student with ID " + studentId + " not found.");
    }
}
