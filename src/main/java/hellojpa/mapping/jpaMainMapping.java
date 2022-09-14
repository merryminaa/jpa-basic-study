package hellojpa.mapping;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class jpaMainMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //1) JPQL 사용 예시
            //List<MemberMapping> result = em.createQuery("select m from MemberMapping m where m.username like '%kim%'", MemberMapping.class).getResultList();

            //2) Criteria 사용 예시
            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MemberMapping> query = cb.createQuery(MemberMapping.class);

            //루트 클래스 (조회를 시작할 클래스)
            Root<MemberMapping> m = query.from(MemberMapping.class);

            //쿼리 생성
            CriteriaQuery<MemberMapping> cq = query.select(m);

            //동적쿼리 생성에 편리
            String username = "test";
            if (username != null) {
                cq = cq.where(cb.equal(m.get("username"),"kim"));
            }

            List<MemberMapping> resultList = em.createQuery(cq).getResultList();

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
