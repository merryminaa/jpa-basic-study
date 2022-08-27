package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistence2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {
            //영속
            Member member1 = em.find(Member.class, 101L); //DB 쿼리 호출
            Member member2 = em.find(Member.class, 101L); //1차 캐시 사용(영속성 컨텍스트)

            System.out.println("result = " + (member1 == member2)); //true
            //영속 엔티티의 동일성 보장(같은 트랜잭션 안에서)

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }
}
