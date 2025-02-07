package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer point;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();

    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
        if (memberMission.getMission() != this) {
            memberMission.setMission(this);
        }
    }
    public void removeMemberMission(MemberMission memberMission) {
        this.memberMissions.remove(memberMission);
        if (memberMission.getMission() == this) {
            memberMission.setMission(null);
        }
    }
}