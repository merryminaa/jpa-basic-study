package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USER") //클래스이름과 테이블명이 다를 경우 직접 테이블명 지정도 가능
public class Member {


    @Id
    private Long id;

//    @Column(name = "username") //DB 컬럼명 지정도 가능
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
