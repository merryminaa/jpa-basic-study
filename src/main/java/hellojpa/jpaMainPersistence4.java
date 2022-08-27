package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistence4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("change name");
//            em.persist(member);
            //데이터 변경이 있다고해서 persist를 또 할 필요가 있을까?
            //jpa는 변경 감지 기능(Dirty Checking)이 제공 => 엔티티 스냅샷을 통해 커밋 시점에 변경내용이 있을 경우 업데이트 쿼리

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
