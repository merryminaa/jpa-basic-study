package hellojpa.mapping;

import org.hibernate.Hibernate;

import javax.persistence.*;
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


            MemberMapping member = new MemberMapping();
            member.setUsername("member1");

            //1) 저장
            //값 타입 하나만 저장
            member.setHomeAddress(new Address("homeCity", "street1", "1111111"));

            //값 타입 복수 저장
            member.getFavoriteFoods().add("마라탕");
            member.getFavoriteFoods().add("양꼬치");
            member.getFavoriteFoods().add("쌀국수");

//            member.getAddressHistory().add(new Address("old1", "street", "1111111"));
//            member.getAddressHistory().add(new Address("old2", "street", "1111111"));\
            member.getAddressHistory().add(new AddressEntity("old1", "street", "1111111"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "1111111"));

            em.persist(member);
            //member 저장시 값타입 컬렉션도 함께 저장
            //값타입 컬렉션도 값타입처럼 해당 엔티티의 생명주기에 의존함(별도의 persist 등은 필요 X)

            em.flush();
            em.clear();

            //2) 조회
            //값타입 컬렉션은 기본값이 지연로딩 => 해당객체 조회시 쿼리 실행
            System.out.println("================= START =================");
            MemberMapping findMember = em.find(MemberMapping.class, member.getId());

            System.out.println("================= LAZY LOADING1 =================");
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }

            System.out.println("================= LAZY LOADING2 =================");
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            //3) 수정
            //3-1) 값타입 단일 수정
            //homeCity -> newCity
            //findMember.getHomeAddress().setCity("newCity");
            //에러 => 값타입은 immutable object이어야하므로 setter를 삭제 or private으로 설정
            //따라서 값타입은 객체 자체를 아예 교체해줘야 함
            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            //3-2) 값타입 컬렉션 수정
            //마라탕 -> 마라샹궈
            //마찬가지로 object를 찾아서 통째로 교체해줘야 함
            findMember.getFavoriteFoods().remove("마라탕");
            findMember.getFavoriteFoods().add("마라샹궈");

            //old1 -> new1
            //equals를 통해서 이전에 들어간 object를 찾아서 삭제 후 교체
            //쿼리상 member_id에 해당되는 컬렉션을 전부 삭제 후 old2, new1를 새로 저장
//            findMember.getAddressHistory().remove(new Address("old1", "street", "1111111"));
//            findMember.getAddressHistory().add(new Address("new1", "street", "1111111"));

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
