/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.usecases;

import com.mycompany.libraryproject.core.dto.NewMemberDTO;
import com.mycompany.libraryproject.core.exceptions.InvalidEmailException;
import com.mycompany.libraryproject.core.exceptions.MemberAlreadyRegisteredException;
import com.mycompany.libraryproject.core.repositories.MemberRepository;
import com.mycompany.libraryproject.core.usecases.member.RegisterNewMemberUseCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeMemberRepository;

/**
 *
 * @author Josue
 */
public class RegisterNewMemberUseCaseTest {

    private MemberRepository memberRepository;
    private RegisterNewMemberUseCase registerNewMember;

    @BeforeEach
    void setUp() {
        memberRepository = new FakeMemberRepository();
        registerNewMember = new RegisterNewMemberUseCase(memberRepository);
    }

    @Test
    void shouldRegisterNewMember() {

        NewMemberDTO newMember = new NewMemberDTO();
        
        newMember.setName("New Member");
        newMember.setEmail("new.member@example.com");

        registerNewMember.execute(newMember);

        assertTrue(
            memberRepository.existsByEmail("new.member@example.com")
        );
    }

    @Test
    void shouldThrowExceptionWhenMemberAlreadyExists() {

        NewMemberDTO newMember = new NewMemberDTO();
        
        newMember.setName("Robert C. Martin");
        newMember.setEmail("robert.martin@example.com");

        assertThrows(
            MemberAlreadyRegisteredException.class,
            () -> registerNewMember.execute(newMember)
        );
    }
    
    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {

        NewMemberDTO newMember = new NewMemberDTO();
        
        newMember.setName("Robert C. Martin");
        newMember.setEmail("robert.martinexamplecom");

        assertThrows(
            InvalidEmailException.class,
            () -> registerNewMember.execute(newMember)
        );
    }
}
