package org.example.acadify.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers=new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students=new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Task> tasks=new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "subject_groups",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups=new ArrayList<>();
}
