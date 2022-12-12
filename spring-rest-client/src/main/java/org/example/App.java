package org.example;

import org.example.config.MyConfig;
import org.example.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

//        Employee employee = new Employee();
//        employee.setDepartment("IT");
//        employee.setName("Dmitry");
//        employee.setSurname("Kazlou");
//        employee.setSalary(1200);
//
//        communication.saveEmployee(employee);

        System.out.println(communication.getAllEmployees());

    }
}
