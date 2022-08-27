package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistenceFlush {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {
            //영속
            Member member = new Member(200L, "member200");
            em.persist(member);
            em.flush(); //강제 호출
            //쓰기 지연 SQL 저장소에 있던 쿼리가 DB에 반영하여 동기화(1차캐시가 없어지는건 아님)

            System.out.println("====================");
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }
}
