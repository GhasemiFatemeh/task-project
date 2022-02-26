package model.service;

import model.entity.Roles;
import model.repository.RolesDataAccess;

import java.util.List;

public class RolesService {
    private RolesService(){}
    private static RolesService rolesService= new RolesService();

    public static RolesService getInstance() {
        return rolesService;
    }

    public void register(Roles person) throws Exception{
        try (RolesDataAccess rolesDataAccess= new RolesDataAccess()){
            rolesDataAccess.insert(person);
            rolesDataAccess.commit();
        }
    }

    public void update(Roles person) throws Exception{
        try(RolesDataAccess rolesDataAccess= new RolesDataAccess()){
            rolesDataAccess.update(person);
            rolesDataAccess.commit();
        }
    }
    public void remove(Roles roles) throws Exception{
        try(RolesDataAccess rolesDataAccess= new RolesDataAccess()){
            rolesDataAccess.delete(roles);
            rolesDataAccess.commit();
        }
    }

    public List<Roles> findAll() throws Exception{
        try(RolesDataAccess rolesDataAccess=new RolesDataAccess()){
            return rolesDataAccess.selectAll();
        }
    }
}
