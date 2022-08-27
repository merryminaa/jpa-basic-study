package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistence1 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {
            //비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속 상태로 변경(객체를 저장한 상태) => 이 단계에서 DB에 쿼리 호출 X
            em.persist(member);

            //영속성 컨텍스트 안에 존재 => 1차 캐시에서 결과를 가져옴(DB에 쿼리 호출 X)
            Member findMember = em.find(Member.class, 101L);

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
