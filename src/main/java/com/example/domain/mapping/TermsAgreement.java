package com.example.domain.mapping;

import com.example.domain.*;
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
public class TermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isAgreed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getTermsAgreements().remove(this);
        }
        this.member = member;
        if (member != null && !member.getTermsAgreements().contains(this)) {
            member.getTermsAgreements().add(this);
        }
    }

    public void setTerms(Terms terms) {
        if (this.terms != null) {
            this.terms.getTermsAgreements().remove(this);
        }
        this.terms = terms;
        if (terms != null && !terms.getTermsAgreements().contains(this)) {
            terms.getTermsAgreements().add(this);
        }
    }
}