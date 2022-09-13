package hellojpa.mapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class MemberMapping extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //임베디드타입 Period
    @Embedded
    private Period workPeriod;

    //임베디드타입 Address
    @Embedded
    private Address homeAddress;

    //값타입 컬렉션
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//값타입 컬렉션
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    private List<Address> addressHistory = new ArrayList<>();


    //값타입 컬렉션 => 엔티티 생성해서 일대다 관계로 변경
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true  )
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    //한 엔티티에 안에서 같은 값 타입을 사용하면 컬럼명이 중복되므로 @AttributeOverrides, @AttributeOverride로 컬럼명 속성 재정의
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE")),
    })
    private Address workAddress;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩 사용해서 프록시로 조회
    @JoinColumn(name = "TEAM_ID")
    private TeamMapping team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProductList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TeamMapping getTeam() {
        return team;
    }

    public void setTeam(TeamMapping team) {
        this.team = team;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    //    public void changeTeam(TeamMapping team) {
//        this.team = team;
//        team.getMembers().add(this); 
//        //연관관계 편의 메소드(일반 getter,setter와 구분되게 로직이 들어갔으므로 이름도 변경)
//        //생성 시점에 역방향에도 값을 입력
    //만약 연관관계 편의 메소드가 양쪽에 다 있을 경우 한쪽은 지우기
//    }

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
}
