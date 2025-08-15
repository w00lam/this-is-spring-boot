package com.example.demo;

import com.example.demo.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PharmacyApplication implements ApplicationRunner {
    private final PharmacyRepository pharmacyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 서울 시청을 기준으로 2킬로미터 이내의 약국을 가까운 순으로 검색
        // 기준점 지도: https://www.google.com/maps/@37.5637072,126.9776214,16z

        var point = new Point(126.9776214, 37.5637072);
        var distance = new Distance(2.0, Metrics.KILOMETERS);
        var pharmacies = pharmacyRepository.findByLocationNear(point, distance);
        log.info("find {} pharmacies near", pharmacies.size());
        log.info("{}", pharmacies);

        // 대략적인 서울 영역
        //           37.715133
        // 126.734086	←↕→	127.269311
        //           37.413294

        pharmacies = pharmacyRepository.findByLocationWithin(new Polygon(
                new Point(126.734086, 37.715133),
                new Point(127.269311, 37.715133),
                new Point(127.269311, 37.413294),
                new Point(126.734086, 37.413294)));
        log.info("find {} pharmacies within", pharmacies.size());
        log.info("{}", pharmacies);
    }
}