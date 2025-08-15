package com.example.demo.repository;

import com.example.demo.model.Pharmacy;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PharmacyRepository extends MongoRepository<Pharmacy, String> {
    // point 기준으로 distance 거리 이내에 있는 약국 검색
    List<Pharmacy> findByLocationNear(Point point, Distance distance);

    // polygon 영역 이내에 있는 약국 검색
    List<Pharmacy> findByLocationWithin(Polygon polygon);
}
