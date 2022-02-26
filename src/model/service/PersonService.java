package model.service;


import model.entity.Person;
import model.repository.PersonDataAccess;

import java.util.List;

public class PersonService {
    private PersonService(){}

    private static PersonService personService= new PersonService();

    public static PersonService getInstance() {
        return personService;
    }

    public void register(Person person) throws Exception{
        try (PersonDataAccess personDataAccess= new PersonDataAccess()){
            personDataAccess.insert(person);
            personDataAccess.commit();
        }
    }

    public void update(Person person) throws Exception{
        try(PersonDataAccess personDataAccess= new PersonDataAccess()){
            personDataAccess.update(person);
            personDataAccess.commit();
        }
    }
    public void remove(long id) throws Exception{
        try(PersonDataAccess personDataAccess= new PersonDataAccess()){
            personDataAccess.delete(id);
            personDataAccess.commit();
        }

    }

    public List<Person> findAll() throws Exception{
        try(PersonDataAccess personDataAccess=new PersonDataAccess()){
           return personDataAccess.selectAll();
        }
    }
}
