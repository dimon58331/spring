//package com.crud.hibernate_and_spring_data_jpa.dao;
//
//import com.crud.hibernate_and_spring_data_jpa.entity.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public Person findById(int id){
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public List<Person> findAll(){
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("from Person", Person.class).getResultList();
//    }
//
//    @Transactional
//    public void save(Person person){
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(Person person){
//        Session session = sessionFactory.getCurrentSession();
//        session.update(person);
//    }
//
//    @Transactional
//    public void delete(Person person){
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(person);
//    }
//}
