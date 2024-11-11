package com.anandvardhan.rule_engine.service;

import java.util.List;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anandvardhan.rule_engine.common_utilities.CommomUtilities;
import com.anandvardhan.rule_engine.dto.EmployeeDto;
import com.anandvardhan.rule_engine.utilities.ASTNode;
import com.anandvardhan.rule_engine.utilities.ConditionNode;
import com.anandvardhan.rule_engine.utilities.ConstantNode;
import com.anandvardhan.rule_engine.utilities.LogicalOperatorNode;
//code

@Service
public class EngineService {
	
	@Autowired
	private CommomUtilities Commonutilities;
 
	public  ASTNode constructAST(String expression) {
        Stack<ASTNode> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = Commonutilities.splitString(expression).toArray(new String[0]);

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    ASTNode right = values.pop();
                    ASTNode left = values.pop();
                    String op = operators.pop();
                    values.push(new LogicalOperatorNode(op, left, right));
                }
                operators.pop(); // Remove '('
            } else if (token.equals("AND") || token.equals("OR")) {
                while (!operators.isEmpty() && operators.peek().equals("AND") || operators.peek().equals("OR")) {
                    ASTNode right = values.pop();
                    ASTNode left = values.pop();
                    String op = operators.pop();
                    values.push(new LogicalOperatorNode(op, left, right));
                }
                operators.push(token);
            } else if (token.contains(">") || token.contains("<") || token.contains("=")) {
                // This is a condition node
                String field = tokens[i-1];
                String operator = token;  // The operator (>, <, =)
                String value = tokens[i+1];     // The value (e.g., 30, 'Sales')

                // Remove surrounding quotes if value is a string
                if (value.startsWith("'") && value.endsWith("'")) {
                    value = value.substring(1, value.length() - 1);
                }

                // Convert value to Integer if possible
                Object conditionValue;
                try {
                    conditionValue = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    conditionValue = value; // Treat it as a String
                }

                values.push(new ConditionNode(field, operator, conditionValue));
            }
        }

        while (!operators.isEmpty()) {
            ASTNode right = values.pop();
            ASTNode left = values.pop();
            String op = operators.pop();
            values.push(new LogicalOperatorNode(op, left, right));
        }

        return values.pop(); // The final AST root node
    }
	
	public  ASTNode combineASTs(List<String> ruleStrings, String logicalOperator) {
        if (ruleStrings.isEmpty()) {
            return new ConstantNode(true); // Return a trivial "true" node for an empty rule list
        }

        // Parse the first rule string into an AST
        ASTNode combinedAST = constructAST(ruleStrings.get(0));

        // Combine the rest of the rules into the combined AST
        for (int i = 1; i < ruleStrings.size(); i++) {
            ASTNode nextAST = constructAST(ruleStrings.get(i));
            combinedAST = new LogicalOperatorNode(logicalOperator, combinedAST, nextAST);
        }

        // Optimize the final combined AST
        return combinedAST;
    }
	
	public boolean evaluateRule(EmployeeDto data , String rule) {
		
		
		return false;		
	}
}
