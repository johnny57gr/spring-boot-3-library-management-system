package com.ypassas.springboot.librarymanagement.controller;

import com.ypassas.springboot.librarymanagement.model.Member;
import com.ypassas.springboot.librarymanagement.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService theMemberService) {
        memberService = theMemberService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listMembers(Model theModel) {

        // get members from db
        List<Member> theMembers = memberService.findAll();

        // add to the spring model
        theModel.addAttribute("members", theMembers);

        return "members/list-of-members";
    }

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Member theMember = new Member();

        theModel.addAttribute("member", theMember);

        return "members/member-form";
    }

    // add mapping for "/showFormForUpdate"
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("memberId") int theId, Model theModel) {
        // get the book from the service
        Member theMember = memberService.findById(theId);

        // set book in the model to prepopulate the form
        theModel.addAttribute("member", theMember);

        // send over our form
        return "members/member-form";
    }

    // add mapping for "/delete"
    @GetMapping("/delete")
    public String delete(@RequestParam("memberId") int theId) {
        // delete member
        memberService.deleteById(theId);

        // redirect to /members/list
        return "redirect:/members/list";
    }

    // add mapping for "/save"
    @PostMapping("/save")
    public String saveMember(@Valid @ModelAttribute("member") Member theMember,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "members/member-form";
        }

        try {
            // save new member
            memberService.save(theMember);
        }
        catch (IllegalArgumentException exception) {
            model.addAttribute("errorMessage", exception.getMessage());
            return "members/member-form";
        }

        // use a redirect to prevent duplicate submissions
        return "redirect:/members/list";
    }

}
