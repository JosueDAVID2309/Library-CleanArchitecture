package com.mycompany.libraryproject.core.usecases.member;

import com.mycompany.libraryproject.core.dto.NewMemberDTO;
import com.mycompany.libraryproject.core.entities.Member;
import com.mycompany.libraryproject.core.exceptions.InvalidEmailException;
import com.mycompany.libraryproject.core.exceptions.MemberAlreadyRegisteredException;
import com.mycompany.libraryproject.core.repositories.MemberRepository;
import com.mycompany.libraryproject.core.validator.EmailValidator;

public class RegisterNewMemberUseCase {
    
    private final MemberRepository repository;
    

    public RegisterNewMemberUseCase(MemberRepository repository) {
        this.repository = repository;
    }
    
    public void execute(NewMemberDTO newMember){
        
        if(!EmailValidator.validate(newMember.getEmail())){
            throw new InvalidEmailException();
        }
        
        if(repository.existsByEmail(newMember.getEmail())){
            throw new MemberAlreadyRegisteredException();
        }
        
        Member member = new Member();
        member.setName(newMember.getName());
        member.setEmail(newMember.getEmail());
        
        repository.addMember(member);
        
    }
}
