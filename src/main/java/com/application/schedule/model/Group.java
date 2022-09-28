package com.application.schedule.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Group")
@Table(
        name = "group",
        uniqueConstraints = {
                @UniqueConstraint(name = "group_name_unique", columnNames = "name")
        }
)
public class Group {
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            initialValue = 100,
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 30,
            columnDefinition = "TEXT"
    )
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(Long id,
                 String name,
                 Set<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonManagedReference
    public Set<Student> getStudents() {
        return students;
    }


    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int result = (id == null ? 0 : id.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Group group = (Group) obj;
        return id.equals(group.id) &&
                name.equals(group.name);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id:")
                .append(id)
                .append(" ")
                .append(name)
                .append("\n");
        if (!students.isEmpty())
            for (Student student : students)
                result.append(student.getFirstName())
                        .append(" ")
                        .append(student.getLastName())
                        .append(" ")
                        .append(student.getEmail())
                        .append("\n");
        return result.toString();
    }
}
