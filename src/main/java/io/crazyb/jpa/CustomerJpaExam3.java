package io.crazyb.jpa;

import io.crazyb.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em =emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /*
            EntityManager의 관리하 (영속성 컨텍스)에 있는 데이터를 변경시 db값을 변경해준다.
        */
        try{
            Customer foundCustomer  =  em.find(Customer.class, "ID0001");
            foundCustomer.setName("Park");
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
