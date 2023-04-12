package com.ssafy.farmcu.api.entity.order;

import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "delivery_info")
public class DeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", unique = true, nullable = false)
    private Long deliveryId;

    @Column(name = "delivery_name", length = 10, nullable = false)
    private String deliveryName;

    @Column(name = "delivery_addr",length = 255, nullable = false)
    private String deliveryAddr;

    @Column(name = "delivery_phone_number",length = 15, nullable = false)
    private String deliveryPhoneNumber;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Column
    private String deliveryMethod;

    @Builder
    public DeliveryInfo(Order order, Member member, Long deliveryId, String deliveryName, String deliveryAddr, String deliveryPhoneNumber, String deliveryMethod) {
        this.order = order;
        this.member = member;
        this.deliveryId = deliveryId;
        this.deliveryName = deliveryName;
        this.deliveryAddr = deliveryAddr;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.deliveryMethod = deliveryMethod;
    }
}