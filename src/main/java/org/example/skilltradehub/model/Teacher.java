package org.example.skilltradehub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends User{

    @OneToMany(mappedBy = "teacher")
    private List<Task> tasks;

}
