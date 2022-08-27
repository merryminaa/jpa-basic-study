package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainPersistenceDetach {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {

            //영속
            Member findMember1 = em.find(Member.class, 150L);
            findMember1.setName("update name"); //dirty checking

            //준영속
            em.detach(findMember1); //특정 엔티티만
            //em.clear() //영속성 컨텍스트 전체 초기화

            Member findMember2 = em.find(Member.class, 150L); //쿼리가 새로 날아감(1차 캐시에 없으므로)

            System.out.println("====================");
            tx.commit(); //업데이트 쿼리 호출X

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }


}
