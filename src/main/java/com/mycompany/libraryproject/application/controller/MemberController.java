/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.application.controller;

import com.mycompany.libraryproject.core.dto.NewMemberDTO;
import com.mycompany.libraryproject.core.usecases.member.RegisterNewMemberUseCase;

/**
 *
 * @author Josue
 */
public class MemberController {
    private final RegisterNewMemberUseCase registerNewMember;

    public MemberController(RegisterNewMemberUseCase registerNewMember) {
        this.registerNewMember = registerNewMember;
    }
    
    public void addMember(NewMemberDTO newMember){
        registerNewMember.execute(newMember);
    }
}
