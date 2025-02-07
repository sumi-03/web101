package com.example.domain;

import com.example.domain.base.BaseEntity;
import com.example.domain.mapping.TermsAgreement;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    @Column(nullable = false)
    private boolean mandatory;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<TermsAgreement> termsAgreements = new ArrayList<>();

    public void addTermsAgreement(TermsAgreement termsAgreement) {
        termsAgreements.add(termsAgreement);
        if (termsAgreement.getTerms() != this) {
            termsAgreement.setTerms(this);
        }
    }
    public void removeTermsAgreement(TermsAgreement termsAgreement) {
        termsAgreements.remove(termsAgreement);
        if (termsAgreement.getTerms() == this) {
            termsAgreement.setTerms(null);
        }
    }
}