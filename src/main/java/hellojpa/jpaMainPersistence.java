package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistence {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {

            //비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 상태로 변경(객체를 저장한 상태) => 이 단계에서 DB에 쿼리 호출 X
            em.persist(member);
            //영속 상태 분리
            em.detach(member);
            //영속 상태 삭제
            em.remove(member);

            //커밋하는 단계에서 DB에 쿼리 호출
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }
}
