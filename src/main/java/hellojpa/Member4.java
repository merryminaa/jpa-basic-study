package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //맵핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
        //allocationSize 기본값은 50
        //DB에서 미리 가져와서 메모리에 올려놓고 씀 => 네트워크 왔다갔다하는 성능 이슈 해결
public class Member4 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Integer보다 Long 사용 권장

    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

    public Member4() {}

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
}
