package com.ssafy.farmcu.api.entity.order;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.store.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", unique = true)
    private Long cartId;

    private int cartItemCount;

    @ManyToOne
    @JoinColumn(name="memberId", nullable=false)
    private Member member;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Item.class)
    @JoinColumn(name = "item_id", updatable = false)
    private Item item;

    @Builder
    public Cart(Long cartId,Item item, int cartItemCount, Member member) {

        this.cartId = cartId;
        this.item = item;
        this.member = member;
        this.cartItemCount = cartItemCount;

    }

    public int getTotalPrice(){
        return item.getItemPrice()*(100-item.getItemDiscount())/100*cartItemCount;
    }

    public static Cart createCart(Member member, Item item, int cartItemCount){
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cart.setCartItemCount(cartItemCount);

        return cart;
    }


}
