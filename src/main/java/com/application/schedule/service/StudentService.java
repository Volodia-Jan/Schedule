package com.application.schedule.service;

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
    private final GroupRepo groupRepo;

    public StudentService(StudentRepo studentRepo, GroupRepo groupRepo) {
        this.studentRepo = studentRepo;
        this.groupRepo = groupRepo;
    }

    public List<Student> findAllStudent() {
        return studentRepo.findAll();
    }

    public void addStudent(Student student) {
        if (findStudentByEmail(student.getEmail()) == null)
            studentRepo.save(student);
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
                Student otherStudent = findStudentByEmail(email);
                if (otherStudent == null)
                    studentRepo.updateEmailById(id, email);
            }
            if (age != null) {
                if (age <= 70 && age >= 16)
                    studentRepo.updateAgeById(id, age);
            }
        }
    }

    public void changeGroup(Long studentId, Long groupId){
        Optional<Group> group = groupRepo.findById(groupId);
        Student student = findStudentById(studentId);
        if (group.isPresent() && !group.get().getStudents().contains(student))
            studentRepo.updateGroupById(studentId, group.get());
    }

    public void deleteStudentById(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    public Student findStudentById(Long studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    public Student findStudentByEmail(String email) {
        return studentRepo.findByEmail(email).orElse(null);
    }
}
