package com.example.demo.repository;

import com.example.demo.model.Member;
import com.example.demo.model.MemberStats;
import com.example.demo.model.MemberStatsNative;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이름으로 회원 조회
    List<Member> findAllByName(String name);

    List<Member> findByName(String name);

    List<Member> findByNameIs(String name);

    List<Member> findByNameEquals(String name);

    // find 이외에옫 get, query, read, search 사용 가능
    List<Member> getByName(String name);

    List<Member> queryByName(String name);

    List<Member> readByName(String name);

    List<Member> searchByName(String name);

    // 이름과 이메일을 AND 조건으로 회원 조회
    List<Member> findByNameAndEmail(String name, String email);

    // 이름과 이메일을 OR 조건으로 회원 조회
    List<Member> findByNameOrEmail(String name, String email);

    // 이름의 시작으로 회원 조회
    List<Member> findByNameStartingWith(String name);

    // 이름의 마지막으로 회원 조회
    List<Member> findByNameEndingWith(String name);

    // 이름을 포함하는 회원 조회
    List<Member> findByNameContaining(String name);

    // 이름을 포함하는 회원 조회로 LIKE 검색을 위한 와일드 카드 &를 매개변수에 포함
    // 예) findByNameLike(%윤%)
    List<Member> findByNameLike(String name);

    // 나이 정보가 존재하지 않는 회원 조회
    List<Member> findByAgeIsNull();

    // 나이 정보가 존재하는 회원 조회
    List<Member> findByAgeIsNotNull();

    // 매개변수로 전달된 나이로 회원 조회
    List<Member> findByAgeIs(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 많은 회원 조회
    List<Member> findByAgeGreaterThan(Integer age);

    List<Member> findByAgeAfter(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 많거나 같은 회원 조회
    List<Member> findByAgeGreaterThanEqual(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 적은 회원 조회
    List<Member> findByAgeLessThan(Integer age);

    List<Member> findByAgeBefore(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 적거나 같은 회원 조회
    List<Member> findByAgeLessThanEqual(Integer age);

    // 매개변수로 전달된 나이를 포함해 그 사이 나이의 회원 조회
    List<Member> findByAgeBetween(Integer min, Integer max);

    // SELECT * FROM MEMBER WHERE name = '...' AND email = '...' OR age > ...
    List<Member> findByNameIsAndEmailIsOrAgeIsGreaterThan(String name, String email, Integer age);

    List<Member> queryByNameIsAndEmailIsOrAgeIsGreaterThan(String name, String email, Integer age);

    List<Member> searchByNameIsAndEmailIsOrAgeIsGreaterThan(String name, String email, Integer age);

    // 이름순으로 조회
    List<Member> findByOrderByNameAsc();

    // 이름의 역순으로 조회
    List<Member> findByOrderByNameDesc();

    // 이름순으로 조회하고 이름이 같은 경우에는 나이의 역순으로 조회
    List<Member> findByOrderByNameAscAgeDesc();

    // 이름의 일부로 검색하고 그 결과는 이름순으로 조회
    // 조건과 정렬 방법 등이 함께 이름에 사용되면 이름이 길어져 가독성이 떨어진다.
    List<Member> findByNameContainingOrderByNameAsc(String name);

    // 나이순으로 정렬하고 나이가 가장 적은 한 명을 조회
    Member findFirstByOrderByAgeAsc();

    Member findTopByOrderByAgeAsc();

    // 나이순으로 정렬하고 나이가 가장 적은 두 명을 조회
    List<Member> findFirst2ByOrderByAgeAsc();

    List<Member> findTop2ByOrderByAgeAsc();

    // 이름순으로 정렬하고 이름이 같으면 나이의 역순으로 정렬하는 Sort 객체
    Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"));

    // 이름순으로 한 페이지당 20개씩 포함하도록 페이지를 나누고, 그중 첫 번째 페이지에 해당하는 목록을 조회하기 위한 Pageable 객체
    Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.asc("name")));

    // 이름의 일부분으로 검색하고 Sort 객체의 정보를 기반으로 정렬
    List<Member> findByNameContaining(String name, Sort sort);

    // 이름의 일부분으로 검색하고 Pageable 객체의 정보를 기반으로 페이지 조회
    Page<Member> findByNameContaining(String name, Pageable pageable);

    // 이메일을 사용해 회원 삭제
    @Transactional
    int deleteByEmail(String email);

    // 이름을 사용해 회원 삭제
    @Transactional
    int deleteByName(String name);

    @Query("SELECT m FROM Member m WHERE m.name = :name")
    List<Member> findMember(@Param("name") String name);

    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.email = :email")
    List<Member> findMember(@Param("name") String name, @Param("email") String email);

    @Query("""
            SELECT m.name, m.email, COUNT(a.id) AS count
            FROM Member m LEFT JOIN Article a ON a.member = m
            GROUP BY m ORDER BY count DESC
            """)
    List<Object[]> getMemberStatsObject();

    @Query("""
            SELECT new com.example.demo.model.MemberStats(m.name, m.email, COUNT(a.id) AS count)
            FROM Member m LEFT JOIN Article a ON a.member = m
            GROUP BY m ORDER BY count DESC
            """)
    List<MemberStats> getMemberStats();

    @Modifying
    @Query("UPDATE Member m SET m.age = :age")
    @Transactional
    int setMemberAge(Integer age);

    @Query(value = """
            SELECT m.name, m.email, COUNT(a.id) AS count
            FROM Member m LEFT JOIN Article a ON m.id = a.member_id
            GROUP BY m.id ORDER BY count DESC
            """, nativeQuery = true)
    List<Object[]> getMemberStatsNativeObjects();

    @Query(value = """
            SELECT m.name, m.email, COUNT(a.id) AS count
            FROM Member m LEFT JOIN Article a ON m.id = a.member_id
            GROUP BY m.id ORDER BY count DESC
            """, nativeQuery = true)
    List<MemberStatsNative> getMemberStatsNative();
}
