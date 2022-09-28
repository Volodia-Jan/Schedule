package com.application.schedule.repository;

import com.application.schedule.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GroupRepo extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String groupName);

    @Transactional
    @Modifying
    @Query("UPDATE Group gr SET gr.name =:name WHERE gr.id =:id")
    void updateNameById(Long id, String name);
}
