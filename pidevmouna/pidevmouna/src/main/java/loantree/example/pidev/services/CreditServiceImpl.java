package loantree.example.pidev.services;


import loantree.example.pidev.Entities.*;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    @Autowired

    CreditRepository RepCredit;

    RefundService refundService;

    UserRepository userRepository;


    @Override
    public Credit AddCredit(Credit c) {
        RepCredit.save(c);
        return c;
    }

    @Override
    public Credit updateCredit(Credit c) {
        RepCredit.save(c);
        return c;
    }



    @Override
    public void deleteAllCredits() {
        List<Credit> credits = RepCredit.findAll();
        for (Credit credit : credits) {
            RepCredit.delete(credit);
        }
    }


    @Override
    public void DeleteCredit(Integer idCredit) {
        RepCredit.deleteById(idCredit);

    }


    @Override
    public List<Credit> getALLCredit() {
        return  RepCredit.findAll();
    }




    @Override
    public Credit getCreditById(Integer idCredit) {
        Credit credit = RepCredit.findById(idCredit).orElse(null);
        return credit;
    }

    @Override
    public int scoring(Integer idCredit, Integer id) {
        Credit credit = RepCredit.findById(id).orElse(null);
        User user = userRepository.findById(id).orElse(null);
        int noteAge = 0;
        int notegender = 0;
        int noteduration = 0;
        int noteamount = 0;
        int notetypecredit = 0;
        int notesalary = 0;
        int noteKidsNumber = 0;
        credit.setUser(user);
        assert credit != null;
        int score = 0;
        Calendar cal = Calendar.getInstance();


        int Age = cal.getTime().getYear() - user.getBirthDate().getYear();
        if (Age < 20) noteAge = 1;
        if (Age >= 20 && Age < 30) {
            noteAge = 2;
        }
        if (Age >= 30 && Age < 50) {
            noteAge = 5;
        }
        if (Age >= 50 && Age < 60) {
            noteAge = 3;
        }
        if (Age >= 60) {
            noteAge = 1;
        }

        if (user.getSalary() < 400) {
            notesalary = 1;
        }
        if (user.getSalary() >= 400 && user.getSalary() < 700) {
            notesalary = 2;
        }
        if (user.getSalary() >= 700 && user.getSalary() < 1000) {
            notesalary = 3;
        }
        if (user.getSalary() >= 1000 && user.getSalary() < 1500) {
            notesalary = 4;
        }
        if (user.getSalary() >= 1500 && user.getSalary() < 2000) {
            notesalary = 5;
        }
        if (user.getSalary() >= 2000) {
            notesalary = 6;
        }

        if (user.getKidsNumber() >= 3) {
            noteKidsNumber = 1;
        }
        if (user.getKidsNumber() == 3) {
            noteKidsNumber = 3;
        }
        if (user.getKidsNumber() <= 3) {
            noteKidsNumber = 5;
        }

        if (user.getGender().equals("Male")) {
            notegender = 1;
        }
        if (user.getGender().equals("Female")) {
            notegender = 2;
        }

        if (credit.getDuration() < 6) {
            noteduration = 2;
        }
        if (credit.getDuration() >= 6 && credit.getDuration() < 12) {
            noteduration = 3;
        }
        if (credit.getDuration() >= 12 && credit.getDuration() < 18) {
            noteduration = 4;
        }
        if (credit.getDuration() >= 18 && credit.getDuration() < 24) {
            noteduration = 5;
        }
        if (credit.getDuration() >= 24) {
            noteduration = 6;
        }

        if (credit.getAmount() < 1000) {
            noteamount = 1;
        }
        if (credit.getAmount() >= 1000 && credit.getAmount() < 5000) {
            noteamount = 2;
        }
        if (credit.getAmount() >= 5000 && credit.getAmount() < 10000) {
            noteamount = 4;
        }
        if (credit.getAmount() >= 10000 && credit.getAmount() < 20000) {
            noteamount = 6;
        }

        if (credit.getType_credit().equals("SCOLAIRE")) {
            notetypecredit = 2;
        }
        if (credit.getType_credit().equals("AGRICOLE")) {
            notetypecredit = 4;
        }
        if (credit.getType_credit().equals("VOITURE") || credit.getType_credit().equals("HABITAT")) {
            notetypecredit = 6;
        }

        score = noteAge + noteduration + notetypecredit + noteamount + notegender + noteKidsNumber + notesalary;
        credit.setScore(score);
        RepCredit.saveAndFlush(credit);
        return score;
    }








}










