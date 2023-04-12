package com.ssafy.farmcu.api.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.farmcu.api.entity.store.Item;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_item")
public class OrderItem {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oitem_id", unique = true, nullable = false)
    private Long oitemId;

    private int oitemCount;

    private LocalDateTime oitemCreatedAt;

    private int oitemPrice;

    private Long storeNum;

    // 연결
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id" )
    private Order order;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String tid;

    @Builder
    public OrderItem(Order order, Item item, Long oitemId,Long storeNum,String tid ,int oitemCount, LocalDateTime oitemCreatedAt, int oitemPrice ) {
        this.order = order;
        this.item = item;
        this.oitemId = oitemId;
        this.storeNum = storeNum;
        this.oitemCount = oitemCount;
        this.oitemCreatedAt = oitemCreatedAt;
        this.oitemPrice = oitemPrice;
        this.tid = tid;
    }


    public static OrderItem createOrderItem( Item item, int oitemCount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOitemCount(oitemCount);
        orderItem.setOitemPrice( oitemCount * item.getItemPrice() * (100 - item.getItemDiscount()) / 100);
        orderItem.setOitemCreatedAt(LocalDateTime.now());
        orderItem.setStoreNum(item.getStore().getStoreId());
        item.removeStock(oitemCount);
        return orderItem;
    }

    public  void addOrderNum(Order order){
        this.order = order;
    }

    public int getTotalPrice(){
        return oitemPrice;
    }


    public void cancel() { this.getItem().addStock(oitemCount); }

}