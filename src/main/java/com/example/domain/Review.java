package com.example.domain;

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
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer star;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getReviews().remove(this);
        }
        this.store = store;
        if (store != null && store.getReviews().contains(this)) {
            store.getReviews().add(this);
        }
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        if (member != null && !member.getReviews().contains(this)) {
            member.getReviews().add(this);
        }
    }
}