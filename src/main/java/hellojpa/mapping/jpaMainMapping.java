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
            TeamMapping team = new TeamMapping();
            team.setName("teamA");
            em.persist(team);

            MemberMapping member1 = new MemberMapping();
            member1.setUsername("hello");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            MemberMapping m = em.find(MemberMapping.class, member1.getId()); //영속성 컨텍스트에 올라감
            System.out.println("m = " + m.getTeam().getClass()); //proxy
            System.out.println("==============");
            m.getTeam().getName(); //이 시점에 team 조회 쿼리
            System.out.println("==============");

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
