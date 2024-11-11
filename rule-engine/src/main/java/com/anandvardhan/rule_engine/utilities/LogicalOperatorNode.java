package com.anandvardhan.rule_engine.utilities;

import java.util.Map;

import org.json.simple.JSONObject;


public class LogicalOperatorNode extends ASTNode {
   
	String operator;
	ASTNode left ; 
	ASTNode right ;
	
	public LogicalOperatorNode(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(Map<String, Object> data) {
        switch (operator) {
            case "AND":
                return left.evaluate(data) && right.evaluate(data);
            case "OR":
                return left.evaluate(data) || right.evaluate(data);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
    
    public JSONObject jsonParser() {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("leftcondition" ,left.jsonParser() );
        jsonObject.put("rightcondition" ,right.jsonParser());
        jsonObject.put("operator" ,operator  );
        
        return jsonObject;
        
    }
    public ASTNode optimize() {
        // Recursively optimize left and right children
        left = left.optimize();
        right = right.optimize();

        // Apply constant folding
        if (left instanceof ConstantNode && right instanceof ConstantNode) {
            boolean leftValue = ((ConstantNode) left).getValue();
            boolean rightValue = ((ConstantNode) right).getValue();

            if (operator.equals("AND")) {
                return new ConstantNode(leftValue && rightValue);
            } else if (operator.equals("OR")) {
                return new ConstantNode(leftValue || rightValue);
            }
        }

        // Apply short-circuiting
        if (operator.equals("AND")) {
            if (left instanceof ConstantNode && !((ConstantNode) left).getValue()) {
                return new ConstantNode(false); // false AND anything = false
            }
            if (right instanceof ConstantNode && !((ConstantNode) right).getValue()) {
                return new ConstantNode(false); // anything AND false = false
            }
            if (left instanceof ConstantNode && ((ConstantNode) left).getValue()) {
                return right; // true AND right = right
            }
            if (right instanceof ConstantNode && ((ConstantNode) right).getValue()) {
                return left; // left AND true = left
            }
        } else if (operator.equals("OR")) {
            if (left instanceof ConstantNode && ((ConstantNode) left).getValue()) {
                return new ConstantNode(true); // true OR anything = true
            }
            if (right instanceof ConstantNode && ((ConstantNode) right).getValue()) {
                return new ConstantNode(true); // anything OR true = true
            }
            if (left instanceof ConstantNode && !((ConstantNode) left).getValue()) {
                return right; // false OR right = right
            }
            if (right instanceof ConstantNode && !((ConstantNode) right).getValue()) {
                return left; // left OR false = left
            }
        }

        return this; // No optimization possible, return the current node
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }
}
