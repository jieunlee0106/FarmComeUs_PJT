package com.ssafy.farmcu.api.entity.store;

import com.ssafy.farmcu.api.entity.live.Live;
import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "store")
public class Store {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(length = 15, nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeDescription;

    @Column(nullable = false)
    private String storeImg;

    @Column(length = 50, nullable = false)
    private String storeStreetAddr;

    @Column(length = 50, nullable = false)
    private String storeDetailAddr;

    @Column(length = 10, nullable = false)
    private String storeZipcode;

    @Column(length = 15, nullable = false)
    private String storePhoneNumber;

    @Column
    private Integer storeDeliveryCost;

    @Column
    private Integer storeDeliveryFree;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "store")
    private List<StoreLike> storeLike = new ArrayList<>();

    //빌더
    @Builder
    public Store(Long storeId, String storeName, String storeDescription, String storeImg, String storeStreetAddr, String storeDetailAddr, String storeZipcode, String storePhoneNumber, Integer storeDeliveryCost, Integer storeDeliveryFree, Member member) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeDescription = storeDescription;
        this.storeImg = storeImg;
        this.storeStreetAddr = storeStreetAddr;
        this.storeDetailAddr = storeDetailAddr;
        this.storeZipcode = storeZipcode;
        this.storePhoneNumber = storePhoneNumber;
        this.storeDeliveryCost = storeDeliveryCost;
        this.storeDeliveryFree = storeDeliveryFree;
        this.member = member;
    }

    public void updateStoreImg(String savedPath){
        this.storeImg = savedPath;
    }
}