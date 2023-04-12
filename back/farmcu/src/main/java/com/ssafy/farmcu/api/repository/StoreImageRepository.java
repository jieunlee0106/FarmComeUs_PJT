package com.ssafy.farmcu.api.repository;


import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.entity.store.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    Optional<StoreImage> findByStoreAndStoreImageId(Store store, Long storeImageId);

    @Query("select si from StoreImage si where si.store.storeId = :storeId")
    Optional<StoreImage> findByStoreId(Long storeId);
    List<StoreImage> findAllByStore(Store store);
    void deleteByStoreImageId(Long storeImageId);

}
