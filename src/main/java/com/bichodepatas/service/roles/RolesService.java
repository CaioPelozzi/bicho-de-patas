package com.bichodepatas.service.roles;


import com.bichodepatas.entities.roles.Roles;
import com.bichodepatas.entities.roles.RolesRequest;
import com.bichodepatas.entities.roles.RolesResponse;
import com.bichodepatas.repository.roles.RolesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private RolesRepository repository;

    public RolesService(RolesRepository repository) {
        this.repository = repository;
    }

    public RolesResponse create(RolesRequest request){

        Roles role = new Roles(request.name());
        Roles savedRole = repository.save(role);

        return new RolesResponse(savedRole.getId(), savedRole.getName());
    }

    public RolesResponse getById(Long id){
        System.out.println(id);
        Roles role = repository.getReferenceById(id);

        return new RolesResponse(role.getId(), role.getName());
    }

    public List<RolesResponse> getAll(){

        List<Roles> roles = repository.findAll();
        return roles.stream().map( role -> new RolesResponse(
                role.getId(),
                role.getName())).toList();

    }

    public RolesResponse update(Long id, RolesRequest request){

        Roles role = repository.getReferenceById(id);
        role.setName(request.name());
        repository.save(role);

        return new RolesResponse(role.getId(), role.getName());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}


