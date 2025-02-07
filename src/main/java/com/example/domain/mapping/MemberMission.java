package com.example.domain.mapping;

import com.example.domain.*;
import com.example.domain.base.BaseEntity;
import com.example.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15)")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberMissions().remove(this);
        }
        this.member = member;
        if (member != null && !member.getMemberMissions().contains(this)) {
            member.getMemberMissions().add(this);
        }
    }

    public void setMission(Mission mission) {
        if (this.mission != null) {
            this.mission.getMemberMissions().remove(this);
        }
        this.mission = mission;
        if (mission != null && !mission.getMemberMissions().contains(this)) {
            mission.getMemberMissions().add(this);
        }
    }
}