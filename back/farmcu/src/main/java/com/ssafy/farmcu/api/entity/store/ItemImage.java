package com.ssafy.farmcu.api.entity.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "item_image")
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long itemImageId;

    @Column
    private String originalName;

    @Column
    private String savedPath;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Item.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", updatable = false)
    private Item item;

    @Builder
    public ItemImage(Long itemImageId, String originalName, String savedPath, Item item) {
        this.itemImageId = itemImageId;
        this.originalName = originalName;
        this.savedPath = savedPath;
        this.item = item;
    }

}
