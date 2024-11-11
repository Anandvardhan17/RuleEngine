package com.anandvardhan.rule_engine.utilities;

import java.util.Map;

import org.json.simple.JSONObject;


public class ConditionNode extends ASTNode {
    private String field;
    private String operator;
    private Object value;

    public ConditionNode(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean evaluate(Map<String, Object> data) {
        Object fieldValue = data.get(field); // Get field value from the input data
        
        // Compare the field value with the value in the condition
        if (fieldValue instanceof Integer && value instanceof Integer) {
            int intFieldValue = (Integer) fieldValue;
            int intValue = (Integer) value;
            switch (operator) {
                case ">":
                    return intFieldValue > intValue;
                case "<":
                    return intFieldValue < intValue;
                case "=":
                    return intFieldValue == intValue;
            }
        } else if (fieldValue instanceof String && value instanceof String) {
            String strFieldValue = (String) fieldValue;
            String strValue = (String) value;
            if (operator.equals("=")) {
                return strFieldValue.equals(strValue);
            }
        }
        throw new IllegalArgumentException("Unsupported condition or type");
    }

	@Override
	protected ASTNode optimize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject jsonParser() {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("field" ,field );
        jsonObject.put("operator" ,operator);
        jsonObject.put("conditionValue" ,value  );
        
        return jsonObject;
        
    }
}
