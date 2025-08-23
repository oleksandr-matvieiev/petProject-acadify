package org.example.acadify.repository;

import org.example.acadify.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findGroupByName(String name);
    List<Group> findAllByNameIn(Collection<String> name);

    Group findGroupById(Long id);
}
