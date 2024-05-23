package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam7 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Customer customer = new Customer("ID0005","Jin");//비영속상태
            em.persist(customer); // customer 객체가 영속상태(Managed)

            Customer foundCustomer = em.find(Customer.class, "ID0005");
            System.out.println(foundCustomer);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}
