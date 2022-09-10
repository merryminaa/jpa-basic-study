package hellojpa.mapping;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class jpaMainMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            MemberMapping member1 = new MemberMapping();
            member1.setUsername("hello");
            em.persist(member1);

            em.flush();
            em.clear();

            MemberMapping refMember = em.getReference(MemberMapping.class, member1.getId()); //영속성 컨텍스트에 올라감
            System.out.println("refMember = " + refMember.getClass()); //proxy

            System.out.println("isLoaded1 = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //false
//            refMember.getUsername(); //사실상 강제 초기화
            Hibernate.initialize(refMember); //하이버네이트가 제공하는 강제 초기화 메소드
            System.out.println("isLoaded2 = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //true

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
