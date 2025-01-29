package com.devops.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
    public Expense getExpenseById(String id) {
            return expenseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense Not Found"));
    }
    public Expense updateExpensePartial(String id, ExpenseUpdateDTO expenseUpdateDTO) {
        Optional<Expense> exp = expenseRepository.findById(id);
        if (exp.isPresent()) {
            Expense expense1 = exp.get();
            if(expenseUpdateDTO.getAmount()!=null) {
                expense1.setAmount(expenseUpdateDTO.getAmount());
            }
            if(expenseUpdateDTO.getDescription()!=null) {
                expense1.setDescription(expenseUpdateDTO.getDescription());
            }
            if(expenseUpdateDTO.getCategory()!=null) {
                expense1.setCategory(expenseUpdateDTO.getCategory());
            }
            if (expenseUpdateDTO.getTransactionType()!=null) {
                expense1.setTransactionType(expenseUpdateDTO.getTransactionType());
            }
            if(expenseUpdateDTO.getExpenseType()!=null) {
                expense1.setExpenseType(expenseUpdateDTO.getExpenseType());
            }
            return expenseRepository.save(expense1);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense Not Found");
        }
    }
}
