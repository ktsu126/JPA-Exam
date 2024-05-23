package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam5 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Customer customer = new Customer("ID0003","HONG");//비영속상태
            em.persist(customer); // customer 객체가 영속상태(Managed)

            em.flush(); // DB와 EM 동기화 commit이 되지않으면 DB에 저장되지않음;

            Customer foundCustomer = em.find(Customer.class, "ID0003");
//            tx.commit(); //commit 실행 시 flush() 실행됨
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}
