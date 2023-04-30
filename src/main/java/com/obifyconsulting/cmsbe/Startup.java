package com.obifyconsulting.cmsbe;

import com.obifyconsulting.cmsbe.entity.ERole;
import com.obifyconsulting.cmsbe.entity.Role;
import com.obifyconsulting.cmsbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Startup implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

       Optional<Role> optRoleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
       if(optRoleAdmin.isEmpty()){
           Role role = new Role();
           role.setName(ERole.ROLE_ADMIN);
           roleRepository.save(role);
       }

        Optional<Role> optRoleMod = roleRepository.findByName(ERole.ROLE_MODERATOR);
        if(optRoleMod.isEmpty()){
            Role role = new Role();
            role.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(role);
        }
        Optional<Role> optRoleIns = roleRepository.findByName(ERole.ROLE_INSTRUCTOR);
        if(optRoleIns.isEmpty()){
            Role role = new Role();
            role.setName(ERole.ROLE_INSTRUCTOR);
            roleRepository.save(role);
        }
        Optional<Role> optRoleUser = roleRepository.findByName(ERole.ROLE_USER);
        if(optRoleUser.isEmpty()){
            Role role = new Role();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }
    }
}
