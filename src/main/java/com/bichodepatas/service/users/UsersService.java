package com.bichodepatas.service.users;

import com.bichodepatas.entities.users.Users;
import com.bichodepatas.entities.users.UsersRequest;
import com.bichodepatas.entities.users.UsersResponse;
import com.bichodepatas.repository.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UsersResponse create(UsersRequest request){
        Users user = new Users(request.name().trim(), request.email().trim(), 
                request.password().trim(), request.telephone().trim());
        Users savedUsers = repository.save(user);

        return new UsersResponse(savedUsers.getId(), savedUsers.getName(), savedUsers.getEmail(), savedUsers.getTelephone());
    }

    public UsersResponse getById(Long id){
        Users user = repository.getReferenceById(id);
        return new UsersResponse(user.getId(), user.getName(), user.getEmail(), user.getTelephone());
    }

    public List<UsersResponse> getAll(){
        List<Users> users =  repository.findAll();
        return users.stream().map( u -> new UsersResponse(u.getId(), u.getName(), u.getEmail(), u.getTelephone())).toList();
    }

    @Transactional
    public UsersResponse update(Long id, UsersRequest request){
        Users user = repository.getReferenceById(id);

        if(request.name() != null && !request.name().isEmpty()){
            user.setName(request.name().trim());
        }
        if(request.email() != null && !request.email().isEmpty()){
            user.setEmail(request.email().trim());
        }
        if(request.password() != null && !request.password().isEmpty()){
            user.setPassword(request.password().trim());
        }
        if(request.telephone() != null && !request.telephone().isEmpty()){
            user.setTelephone(request.telephone().trim());
        }

        repository.save(user);
        return new UsersResponse(user.getId(), user.getName(), user.getEmail(), user.getTelephone());
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
