package com.example.should_i_wash_hair_today.repository;

import com.example.should_i_wash_hair_today.models.LineUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LineUserRepository extends CrudRepository<LineUser, Long> {

    LineUser findByLineId(String lineId);

    @Transactional
    @Modifying
    @Query(value = "update line_user set follow = :follow where id= :id", nativeQuery = true)
    public void updateFollow(@Param("follow") boolean follow,
                      @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update line_user set last_wash_date = :lastWashDate where id= :id", nativeQuery = true)
    public void updateLastWashDate(@Param("lastWashDate") String lastWashDate,
                                   @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update line_user set frequency = :frequency where id= :id", nativeQuery = true)
    public void updateFrequency(@Param("frequency") int frequency,
                                   @Param("id") Long id);

    @Query(value = "select * from line_user where follow = true", nativeQuery = true)
    List<LineUser> getByFollowTrue();
}
