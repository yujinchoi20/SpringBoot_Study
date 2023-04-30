package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.Domain.*;
import org.example.Domain.Items.Book;
import org.example.Domain.Items.Movie;

import java.util.Date;

public class JpaMain {
    public static void main (String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Movie movie = new Movie();
            movie.setName("드림");
            movie.setActor("아이유");
            movie.setDirector("이병헌");
            movie.setPrice(15000);

            em.persist(movie);

            Book book = new Book();
            book.setName("JPA");
            book.setArttist("KIM");
            book.setPrice(43000);

            em.persist(book);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
