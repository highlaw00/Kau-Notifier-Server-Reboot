package kauproject.kaunotifier.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id")
    private Source source;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    // == 생성 메서드 == //
    protected Subscription() {}

    public static Subscription createSubscription(Member member, Source source) {
        Subscription subscription = new Subscription();
        subscription.member = member;
        subscription.source = source;

        return subscription;
    }
}
