package hellojpa.mapping;

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

            TeamMapping team = new TeamMapping();
            team.setName("teamA");
            em.persist(team);

            MemberMapping member = new MemberMapping();
            member.setUsername("member1");
            member.setTeamId(team.getId()); //외래키 식별자를 직접 다룸
            em.persist(member);

            //조회 => 연관관계가 없음(객체지향적인 방식이 아님)
            //데이터베이스에서의 외래키와 객체의 참조는 패러다임이 완전 다름
            MemberMapping findMember = em.find(MemberMapping.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            TeamMapping findTeam = em.find(TeamMapping.class, findTeamId);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
