package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.enums.Gender;
import com.example.domain.enums.MemberStatus;
import com.example.domain.enums.SocialType;
import com.example.domain.mapping.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;
    private Integer age;
    @Column(nullable = false, length = 40)
    private String address;
    @Column(nullable = false, length = 40)
    private String specAddress;
    @Column(nullable = false, length = 50)
    private String email;
    private Integer point;
    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(15) default 'ACTIVE'")
    private MemberStatus status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TermsAgreement> termsAgreements = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPrefers = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();

    public void addTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.add(termsAgreement);
        if (termsAgreement.getMember() != this) {
            termsAgreement.setMember(this);
        }
    }
    public void removeTermsAgreement(TermsAgreement termsAgreement) {
        this.termsAgreements.remove(termsAgreement);
        if (termsAgreement.getMember() == this) {
            termsAgreement.setMember(null);
        }
    }

    public void addMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.add(memberPrefer);
        if (memberPrefer.getMember() != this) {
            memberPrefer.setMember(this);
        }
    }
    public void removeMemberPrefer(MemberPrefer memberPrefer) {
        this.memberPrefers.remove(memberPrefer);
        if (memberPrefer.getMember() == this) {
            memberPrefer.setMember(null);
        }
    }

    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
        if (memberMission.getMember() != this) {
            memberMission.setMember(this);
        }
    }
    public void removeMemberMission(MemberMission memberMission) {
        this.memberMissions.remove(memberMission);
        if (memberMission.getMember() == this) {
            memberMission.setMember(null);
        }
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        if (review.getMember() != this) {
            review.setMember(this);
        }
    }
    public void removeReview(Review review) {
        this.reviews.remove(review);
        if (review.getMember() == this) {
            review.setMember(null);
        }
    }
}