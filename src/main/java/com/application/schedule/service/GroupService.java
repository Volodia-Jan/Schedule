package com.application.schedule.service;

import com.application.schedule.exception.GroupAlreadyExistException;
import com.application.schedule.exception.GroupNotFoundException;
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

    public void addGroup(Group group) throws GroupAlreadyExistException{
        try {
            Group byName = findByName(group.getName());
            throw new GroupAlreadyExistException("Group already exist with name: '" + byName.getName() + "'");
        } catch (GroupNotFoundException e) {
            groupRepo.save(group);
        }
    }

    public void updateName(Long groupId, String newName) throws GroupAlreadyExistException{
        Group group = findById(groupId);
        try {
            Group byName = findByName(newName);
            throw new GroupAlreadyExistException("Group already exist with name: '" + byName.getName() + "'");
        } catch (GroupNotFoundException e) {
            groupRepo.updateNameById(group.getId(), newName);
        }
    }

    public void deleteById(Long groupId){
        Group group = findById(groupId);
        if (group.getStudents().isEmpty())
            groupRepo.deleteById(groupId);
    }

    public Group findByName(String groupName) throws GroupNotFoundException{
        return groupRepo.findByName(groupName).orElseThrow(() ->
                new GroupNotFoundException("Group not found by name: " + groupName));
    }

    public Group findById(Long groupId) throws GroupNotFoundException{
        return groupRepo.findById(groupId).orElseThrow(() ->
                new GroupNotFoundException("Group not found by id: " + groupId));
    }
}
