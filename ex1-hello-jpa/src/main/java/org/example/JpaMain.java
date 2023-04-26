package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main (String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //insert
            /*Member member = new Member();
            member.setId(1L);
            member.setName("Hello!");
            em.persist(member);*/

            /*//select
            Member findMember = em.find(Member.class, 1L);
            //update
            findMember.setName("HelloA");
            em.persist(findMember);*/

            //remove
            /*Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);*/

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
