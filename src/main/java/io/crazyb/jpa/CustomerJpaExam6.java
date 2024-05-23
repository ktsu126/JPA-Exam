package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam6 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Customer customer = new Customer("ID0004","Lee");//비영속상태
            em.persist(customer); // customer 객체가 영속상태(Managed)

            //em.flush(); JPQL 실행 시 flush(); 가 실행된다.
            Query query = em.createQuery("select c from Customer c");
            List<Customer> customers = query.getResultList();
            System.out.println(customers);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}
