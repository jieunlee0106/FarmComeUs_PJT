package com.ssafy.farmcu.api.entity.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "store_image")
public class StoreImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long storeImageId;

    @Column
    private String originalName;

    @Column
    private String savedPath;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Store.class)
    @JoinColumn(name = "store_id", updatable = false)
    private Store store;

    @Builder
    public StoreImage(Long storeImageId, String originalName, String savedPath, Store store) {
        this.storeImageId = storeImageId;
        this.originalName = originalName;
        this.savedPath = savedPath;
        this.store = store;
    }

}
