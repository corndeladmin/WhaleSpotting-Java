package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
