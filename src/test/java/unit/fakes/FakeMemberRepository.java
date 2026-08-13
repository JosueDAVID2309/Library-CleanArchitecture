/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.fakes;

import com.mycompany.libraryproject.core.entities.Member;
import com.mycompany.libraryproject.core.repositories.MemberRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Josue
 */
public class FakeMemberRepository implements MemberRepository {

    private final Map<Integer, Member> members = new HashMap<>();
    private int nextId = 1;

    public FakeMemberRepository() {
        addInitialMembers();
    }

    @Override
    public boolean existsByEmail(String email) {
        return members.values()
                .stream()
                .anyMatch(member -> member.getEmail().equals(email));
    }

    @Override
    public void addMember(Member member) {
        member.setId(nextId++);
        members.put(member.getId(), member);
    }

    private void addInitialMembers() {
        addMember(createMember("James Gosling", "james.gosling@example.com"));
        addMember(createMember("Robert C. Martin", "robert.martin@example.com"));
        addMember(createMember("Martin Fowler", "martin.fowler@example.com"));
        addMember(createMember("Eric Evans", "eric.evans@example.com"));
    }

    private Member createMember(String name, String email) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        return member;
    }
}
