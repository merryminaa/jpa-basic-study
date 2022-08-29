package hellojpa.mapping;

import javax.persistence.*;

@Entity
public class MemberMapping {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne 디폴트값은 FetchType.EAGER(한꺼번에 쿼리)
    @ManyToOne(fetch = FetchType.LAZY) //쿼리 분리
    @JoinColumn(name = "TEAM_ID")
    private TeamMapping team;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

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
//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
}
