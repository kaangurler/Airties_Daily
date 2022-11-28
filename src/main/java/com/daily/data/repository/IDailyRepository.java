package com.daily.data.repository;

import com.daily.data.entity.DailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyRepository extends JpaRepository<DailyEntity,Long> {

    //Kendi Sorgumuzu yazdık
    DailyEntity findByEmail(String email);
}
