package com.example.demo.mapper;

import com.example.demo.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    // SELECT * FROM member
    List<Member> selectAll();

    // SELECT * FROM WHERE id = #{id}
    Optional<Member> selectById(@Param("id") Long id);

    Optional<Member> selectByEmail(@Param("email") String email);

    List<Member> selectAllOrderByAgeASC();

    List<Member> selectAllOrderBy(@Param("order") String order, @Param("dir") String dir);

    List<Member> selectByNameLike(@Param("name") String name);

    int selectAllCount();

    int insert(@Param("member") Member member);

    int update(@Param("member") Member member);

    int delete(@Param("member") Member member);

    int deleteById(@Param("id") Long id);

    int deleteAll();
}
