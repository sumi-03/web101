package com.example.domain.mapping;

import com.example.domain.Category;
import com.example.domain.Member;
import com.example.domain.base.BaseEntity;
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
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberPrefers().remove(this);
        }
        this.member = member;
        if (this.member != null && !member.getMemberPrefers().contains(this)) {
            member.getMemberPrefers().add(this);
        }
    }
}