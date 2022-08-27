package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainBasicKey {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Indentity 전략의 특징
//        try {
//            Member4 member = new Member4();
//            member.setUsername("hello world");
//
//            System.out.println("====================");
//            em.persist(member);
//            //영속성 컨텍스트에 엔티티를 관리하려면 PK가 필요한데 데이터베이스에 insert가 되어야만 PK가 생성
//            //@GeneratedValue(strategy = GenerationType.IDENTITY)인 경우에만
//            //persist하는 시점에 insert 쿼리(보통은 commit하는 시점)
//            //따라서 지연 쓰기가 불가능
//            System.out.println("member.username = " + member.getUsername());
//            System.out.println("====================");
//
//            tx.commit();
//
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }

        //Sequence 전략의 특징
        try {
            Member4 member = new Member4();
            member.setUsername("hello world");

            System.out.println("====================");
            em.persist(member);
            //영속성 컨텍스트에 엔티티를 관리하려면 PK가 필요
            //@GeneratedValue(strategy = GenerationType.SEQUENCE)인 경우에는
            //persist하는 시점에 seq 조회 쿼리 후 영속성 컨텍스트에 넣어둠
            //데이터베이스에 저장하지는 않으며 지연 쓰기 가능
            System.out.println("member.username = " + member.getUsername());
            System.out.println("====================");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
