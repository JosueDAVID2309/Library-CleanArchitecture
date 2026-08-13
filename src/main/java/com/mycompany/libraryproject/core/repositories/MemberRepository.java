package com.mycompany.libraryproject.core.repositories;

import com.mycompany.libraryproject.core.entities.Member;

public interface MemberRepository {
    
    void addMember(Member member);
    
    boolean existsByEmail(String email);
}
