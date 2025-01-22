package org.example.acadify.service;

import org.example.acadify.DTOs.GroupDTO;
import org.example.acadify.mapper.GroupMapper;
import org.example.acadify.model.Group;
import org.example.acadify.repository.GroupRepository;
import org.springframework.stereotype.Service;


@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public GroupDTO createGroup(String groupName) {
        Group group = new Group();
        group.setName(groupName);
        groupRepository.save(group);
        return groupMapper.toDTO(group);
    }
}

