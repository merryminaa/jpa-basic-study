package hellojpa.mapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamMapping extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberMapping> members = new ArrayList<>();

    public List<MemberMapping> getMembers() {
        return members;
    }

    public void setMembers(List<MemberMapping> members) {
        this.members = members;
    }

    public void addMember(MemberMapping member) {
        member.setTeam(this);
        members.add(member);
    }

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
