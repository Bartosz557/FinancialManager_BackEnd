package com.example.FinancialManager.database.transactions;

import com.example.FinancialManager.database.Repositories.ExpenseCategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    ExpenseCategoriesRepository expenseCategoriesRepository;
    public List<String> getAllCategories() {
        List<String>  categoryNames = new ArrayList<>();
        String name;
        List<ExpenseCategories> categories= expenseCategoriesRepository.findAll();
        for( ExpenseCategories category : categories){
            name=category.getCategoryName();
            if(!name.equalsIgnoreCase("recurringExpense") && !name.equalsIgnoreCase("piggyBank")) {
                categoryNames.add(name);
            }
        }
        categoryNames.sort((a, b) -> {
            if (a.equalsIgnoreCase("other")) return 1;
            else if (b.equalsIgnoreCase("other")) return -1;
            else return a.compareTo(b);
        });
        return categoryNames;
    }
}
