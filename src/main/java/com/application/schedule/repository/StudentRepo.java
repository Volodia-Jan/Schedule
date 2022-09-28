package com.application.schedule.repository;

import com.application.schedule.model.Group;
import com.application.schedule.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findAllByGroupId(Long group_id);

    @Transactional
    @Modifying
    @Query("UPDATE Student st SET st.firstName =:firstName WHERE st.id =:id")
    void updateFirstNameById(Long id, String firstName);

    @Transactional
    @Modifying
    @Query("UPDATE Student st SET st.lastName =:lastName WHERE st.id =:id")
    void updateLastNameById(Long id, String lastName);

    @Transactional
    @Modifying
    @Query("UPDATE Student st SET st.email =:email WHERE st.id =:id")
    void updateEmailById(Long id, String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student st SET st.age =:age WHERE st.id =:id")
    void updateAgeById(Long id, Integer age);

    @Transactional
    @Modifying
    @Query("UPDATE Student st SET st.group =:group WHERE st.id =:id")
    void updateGroupById(Long id, Group group);
}
