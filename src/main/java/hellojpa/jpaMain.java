package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //persistence.xml에서 설정한 persistence-unit name

        EntityManager em = emf.createEntityManager(); //행동 단위마다 entity manager 생성

        //jpa의 모든 데이터 변경은 반드시 트랜잭션 단위 내에서 실행!!!
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

        try {
            //회원 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
//            tx.commit();

            //회원 수정
//            Member findMember = em.find(Member.class, 1L );
//            findMember.setName("HelloJPA");
//            tx.commit();

            //회원 삭제
//            Member findMember2 = em.find(Member.class, 1L);
//            em.remove(findMember2);
//            tx.commit();

            //회원 전체 조회
            //필요시 객체를 대상으로 하면서도 검색 등이 가능한 JPQL 사용(dialect 설정을 통해 데이터베이스에 상관없이 이용 가능) => 객체 지향 SQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).setFirstResult(5).setMaxResults(8).getResultList();
//            for (Member member : result) {
//                System.out.println("member = " + member.getName());
//            }
//            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }
}
