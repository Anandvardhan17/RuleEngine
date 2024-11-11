package com.anandvardhan.rule_engine.utilities;

import java.util.Map;

import org.json.simple.JSONObject;


public abstract class ASTNode {
    public abstract boolean evaluate(Map<String, Object> data); // Accept data for evaluation

	protected abstract ASTNode optimize();
	protected abstract  JSONObject jsonParser();
}
