package com.cathaybk.member.rest.repo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cathaybk.member.rest.entity.Cars;
import com.cathaybk.member.rest.entity.CarsPK;


public interface CarsRepo extends JpaRepository<Cars, CarsPK> {

    // select * from CARS where TYPE=:TYPE
    List<Cars> findByType(String type);
    
    List<Cars> findByTypeAndPrice(String type,BigDecimal price);
    
  
    //List<Cars> findByTypeAndMinPrice(String type,BigDecimal price);
    List<Cars> findType1(String type);
    
    List<Cars> findByTypeOrPrice(String type,BigDecimal price);

    @Query(value = "SELECT c FROM Cars c where c.type=:TYPE and c.price=:PRICE")
    List<Cars> findType1(@Param("TYPE") String type,@Param("PRICE") BigDecimal price);
    
    @Query(value = "SELECT c FROM Cars c where c.type=?1 and c.price=?2")
    List<Cars> find( String type, BigDecimal price);
}
