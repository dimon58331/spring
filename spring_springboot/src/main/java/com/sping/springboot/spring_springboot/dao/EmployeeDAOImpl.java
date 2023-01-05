package com.sping.springboot.spring_springboot.dao;


import com.sping.springboot.spring_springboot.entity.Employee;
import jakarta.persistence.EntityManager;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);
//
//        return session.createQuery("from Employee", Employee.class).getResultList();
        Query query = entityManager.createQuery("from Employee");
        return query.getResultList();

    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//
//        session.saveOrUpdate(employee);
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());

    }

    @Override
    public Employee getEmployeeById(int id) {
//        Session session = entityManager.unwrap(Session.class);
//
//        return session.get(Employee.class, id);
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployeeById(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
        Query query = entityManager.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
