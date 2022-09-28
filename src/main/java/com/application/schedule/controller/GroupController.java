package com.application.schedule.controller;

import com.application.schedule.model.Group;
import com.application.schedule.service.GroupService;
import com.application.schedule.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;


    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @GetMapping(params = {"name"})
    public Group getGroupByName(@RequestParam String name) {
        return groupService.findByName(name);
    }

    @PutMapping("/{id}")
    public void updateName(@PathVariable Long id,
                           @RequestParam String name){
        groupService.updateName(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        groupService.deleteById(id);
    }

    @PostMapping("/add")
    public void addGroup(@RequestParam String name) {
        groupService.addGroup(new Group(name));
    }
}
