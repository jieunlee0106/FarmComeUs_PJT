package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.live.Live;
import com.ssafy.farmcu.api.entity.store.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LiveRepository extends JpaRepository<Live, Long> {

    Slice<Live> findByStore(Store store, Pageable pageable); //스토어별 라이브 목록
    Slice<Live> findByLiveTitleLikeAndLiveStartGreaterThan(String liveTitle, LocalDateTime localDateTime, Pageable pageable); //라이브 예정 목록
    Slice<Live> findByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual(String liveTitle, LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable); //라이브 중인 목록
    Optional<Live> findByLiveId(Long liveId);
    void deleteByLiveId(Long liveId);

}
