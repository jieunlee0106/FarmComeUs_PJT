package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

    Optional<ItemImage> findTopByItem(Item item);
    Optional<ItemImage> findByItemAndItemImageId(Item item, Long itemImageId);
    List<ItemImage> findAllByItem(Item item);
    void deleteByItemImageId(Long itemImageId);

}
