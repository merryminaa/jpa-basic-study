package hellojpa.mapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMainMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //저장
            TeamMapping team = new TeamMapping();
            team.setName("teamA");
            em.persist(team);

            MemberMapping member = new MemberMapping();
            member.setUsername("member1");
            //jpa가 알아서 team의 PK를 꺼내서 member의 외래키에 세팅해줌
            //양방향 맵핑시 주의점: 반드시 연관관계의 주인쪽에 값을 입력
//            member.changeTeam (team); //** 주인쪽 값 입력 => 연관관계 편의 메소드

            //객체 관점에서 생각하면 역방향에도 값을 입력
            team.addMember(member);//** 역방향 값 입력 => 연관관계 편의 메소드
            em.persist(member);

            //아래 조회의 경우 이미 위에서 persist하면서 영속성 컨텍스트 안에 가지고 있기 때문에 DB 쿼리 미실행(1차캐시 사용)
            //만약 조회 쿼리도 확인하고 싶다면?
            em.flush(); //쌓여있던 쿼리 한꺼번에 실행
            em.clear(); //전체 초기화

            //조회
            MemberMapping findMember = em.find(MemberMapping.class, member.getId());
            List<MemberMapping> members = findMember.getTeam().getMembers();

            for (MemberMapping memberMapping : members) {
                System.out.println("m = " + member.getUsername());
            }

//            TeamMapping findTeam = findMember.getTeam();
//            System.out.println("findTeam =  " + findTeam.getName());

            //아래의 경우 연관관계가 없음(객체지향적인 방식이 아님)
            //데이터베이스에서의 외래키와 객체의 참조는 패러다임이 완전 다름
//            Long findTeamId = findMember.getTeamId();
//            TeamMapping findTeam = em.find(TeamMapping.class, findTeamId);

            //수정
//            TeamMapping newTeam = em.find(TeamMapping.class, 100L);
//            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
