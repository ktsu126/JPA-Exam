package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /*
        EntityManager가 결과값을 객체로 만들때는 default Constructor가 필요하다.
            */
        try{
            Customer foundCustomer  =  em.find(Customer.class, "ID0001");
            System.out.println(foundCustomer.toString());
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}
