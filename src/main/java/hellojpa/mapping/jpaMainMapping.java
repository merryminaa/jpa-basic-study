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

            Address address = new Address("Seoul", "star-ro","1000");

            MemberMapping member = new MemberMapping();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);


            //값만 복사하여 새로 생성
            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            MemberMapping member2 = new MemberMapping();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

//            member.getHomeAddress().setCity("newCity");
            //불변객체로 만들었으므로 setter 사용 불가
            //만약 변경하고 싶다면? 새로 생성하여 세팅
            Address newAddress = new Address("new city", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);

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
