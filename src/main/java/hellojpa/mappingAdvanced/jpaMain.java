package hellojpa.mappingAdvanced;

import hellojpa.mapping.MemberMapping;
import hellojpa.mapping.TeamMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //movie 등록
            Movie movie = new Movie();
            movie.setDirector("a");
            movie.setActor("b");
            movie.setName("트루먼쇼");
            movie.setPrice(10000);

            em.persist(movie);

            //movie 조회
            em.flush();
            em.clear(); //영속성 컨텍스트 비우기(1차캐시도 삭제)

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
