package hellojpa.mapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct { //연결테이블

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberMapping member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private  int price;
    private LocalDateTime orderDateTime;
}
