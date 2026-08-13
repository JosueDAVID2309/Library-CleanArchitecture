/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.repositories;

import com.mycompany.libraryproject.core.entities.Member;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeMemberRepository;

/**
 *
 * @author Josue
 */
public class FakeMemberRepositoryTest {

    private FakeMemberRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FakeMemberRepository();
    }

    @Test
    void shouldReturnTrueWhenMemberExistsByEmail() {
        assertTrue(
            repository.existsByEmail("robert.martin@example.com")
        );
    }

    @Test
    void shouldReturnFalseWhenMemberDoesNotExistByEmail() {
        assertFalse(
            repository.existsByEmail("unknown@example.com")
        );
    }

    @Test
    void shouldAddNewMember() {
        Member member = new Member();
        member.setName("New Member");
        member.setEmail("new.member@example.com");

        repository.addMember(member);

        assertTrue(
            repository.existsByEmail("new.member@example.com")
        );
    }
}
