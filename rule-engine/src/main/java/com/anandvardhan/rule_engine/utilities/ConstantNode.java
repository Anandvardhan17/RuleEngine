package com.anandvardhan.rule_engine.utilities;

import java.util.Map;

import org.json.simple.JSONObject;


public class ConstantNode extends ASTNode {
    private boolean value;

    public ConstantNode(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean evaluate(Map<String, Object> data) {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

	@Override
	protected ASTNode optimize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSONObject jsonParser() {
		// TODO Auto-generated method stub
		return null;
	}
}
