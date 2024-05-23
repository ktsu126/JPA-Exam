package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();


        try{
            Customer customer = new Customer("ID0002", "Kim");
//            em.persist(customer);//insert가 발생하지 않는다.
//            em.persist(customer);//여러번 해도 insert 중복발생 X

        /*
          foundCustomer instance는 em안(1차 캐시)에 존재하기 때문에,
          select문이 발생하지 않는다.(트랜젝션(tx) 내)
        */
            Customer foundCustomer = em.find(Customer.class, "ID0002");
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
