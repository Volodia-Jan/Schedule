package com.application.schedule.controller;

import com.application.schedule.model.Group;
import com.application.schedule.model.Student;
import com.application.schedule.service.GroupService;
import com.application.schedule.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.findAllStudent();
    }

    @PostMapping("/add")
    public void addStudent(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam Integer age,
                           @RequestParam Long groupId) {
        Group group = groupService.findById(groupId);
        if (group != null)
            studentService.addStudent(new Student(firstName,
                    lastName,
                    email,
                    age,
                    group));
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @GetMapping(params = {"email"})
    public Student getStudentByEmail(@RequestParam(value = "email") String email) {
        return studentService.findStudentByEmail(email);
    }

    @GetMapping(params = {"groupId"})
    public List<Student> getStudentsByGroupId(@RequestParam(value = "groupId") Long groupId) {
        return studentService.findAllByGroupId(groupId);
    }

    @PutMapping(path = "/{id}")
    public void updateStudentInfo(@PathVariable Long id,
                                  @RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) Integer age) {
        Student student = studentService.findStudentById(id);
        studentService.updateStudent(student, firstName, lastName, email, age);
    }

    @PutMapping(path = "/{id}",
            params = {"groupId"})
    public void changeGroup(@PathVariable(name = "id") Long studentId,
                            @RequestParam Long groupId) {
        studentService.changeGroup(studentId, groupId);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
