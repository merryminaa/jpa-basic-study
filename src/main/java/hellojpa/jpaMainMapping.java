package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMainMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member3 member = new Member3();
            member.setId(3L);
            member.setUsername("C");
            member.setRoleType(RoleType.USER);

            em.persist(member);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //자원 사용 종료 후에는 entity manager 클로즈
        }

        emf.close();
    }
}
