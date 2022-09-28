package com.application.schedule.service;

import com.application.schedule.model.Group;
import com.application.schedule.repository.GroupRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepo groupRepo;

    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> findAllGroups(){
        return groupRepo.findAll();
    }

    public void addGroup(Group group){
        if (findByName(group.getName()) == null)
            groupRepo.save(group);
    }

    public void updateName(Long groupId, String newName){
        if (findByName(newName) == null)
            groupRepo.updateNameById(groupId, newName);
    }

    public void deleteById(Long groupId){
        Group group = findById(groupId);
        if (group != null && group.getStudents().isEmpty())
            groupRepo.deleteById(groupId);
    }

    public Group findByName(String groupName){
        return groupRepo.findByName(groupName).orElse(null);
    }

    public Group findById(Long groupId){
        return groupRepo.findById(groupId).orElse(null);
    }
}
