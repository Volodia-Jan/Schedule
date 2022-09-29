package com.application.schedule.service;

import com.application.schedule.exception.EmailAlreadyExistException;
import com.application.schedule.exception.StudentNotFoundException;
import com.application.schedule.model.Group;
import com.application.schedule.model.Student;
import com.application.schedule.repository.GroupRepo;
import com.application.schedule.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final GroupService groupService;

    public StudentService(StudentRepo studentRepo, GroupService groupService) {
        this.studentRepo = studentRepo;
        this.groupService = groupService;
    }

    public List<Student> findAllStudent() {
        return studentRepo.findAll();
    }

    public void addStudent(Student student) throws EmailAlreadyExistException{
        try {
            Student studentDB = findStudentByEmail(student.getEmail());
            throw new EmailAlreadyExistException("Email already exist: " + student.getEmail());
        } catch (StudentNotFoundException e) {
            studentRepo.save(student);
        }
    }

    public List<Student> findAllByGroupId(Long groupId) {
        return studentRepo.findAllByGroupId(groupId);
    }

    public void updateStudent(Student student,
                              String firstName,
                              String lastName,
                              String email,
                              Integer age) {
        if (student != null) {
            Long id = student.getId();
            if (firstName != null) studentRepo.updateFirstNameById(id, firstName);
            if (lastName != null) studentRepo.updateLastNameById(id, lastName);
            if (email != null) {
                try {
                    Student otherStudent = findStudentByEmail(email);
                } catch (StudentNotFoundException e) {
                    studentRepo.updateEmailById(id, email);
                }
            }
            if (age != null) {
                if (age <= 70 && age >= 16)
                    studentRepo.updateAgeById(id, age);
            }
        }
    }

    public void changeGroup(Long studentId, Long groupId){
        Group group = groupService.findById(groupId);
        Student student = findStudentById(studentId);
        if (!group.getStudents().contains(student))
            studentRepo.updateGroupById(studentId, group);
    }

    public void deleteStudentById(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    public Student findStudentById(Long studentId) throws StudentNotFoundException{
        return studentRepo.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student not found by id: " + studentId));
    }

    public Student findStudentByEmail(String email) throws StudentNotFoundException{
        return studentRepo.findByEmail(email).orElseThrow(() ->
                new StudentNotFoundException("Student not found by email: " + email));
    }
}
