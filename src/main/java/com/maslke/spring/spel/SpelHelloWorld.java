package com.maslke.spring.spel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


public class SpelHelloWorld {

    public static void main(String[] args) {

        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, SpelHelloWorld.class.getClassLoader());
        ExpressionParser expressionParser = new SpelExpressionParser(configuration);
        Expression expression = expressionParser.parseExpression("'Hello'+'world'");
        String message = expression.getValue(String.class);
        System.out.println(message);
        String exp = expression.getExpressionString();
        System.out.println(exp);

        expression = expressionParser.parseExpression("('Hello'+'word').concat('!')");
        System.out.println(expression.getValue(String.class));

        expression = expressionParser.parseExpression("'Hello'.bytes.length");
        System.out.println(expression.getValue(Integer.class));

        System.out.println(expressionParser.parseExpression("'Hello'.length()").getValue(Integer.class));

        System.out.println("int class:" + expressionParser.parseExpression("T(int).toString()").getValue(String.class));


        User user = new User("maslke", 10000);
        user.getHobbies().add("Football");
        EvaluationContext context = new StandardEvaluationContext(user);
        String userName = expressionParser.parseExpression("name").getValue(context, String.class);
        System.out.println(userName);

        expressionParser.parseExpression("name").setValue(context, "feng");
        String name = expressionParser.parseExpression("name").getValue(context, String.class);
        System.out.println(name);
        int credits = (Integer) expressionParser.parseExpression("credits").getValue(context);
        System.out.println(credits);

        String hobby = expressionParser.parseExpression("hobbies[0]").getValue(context, String.class);
        System.out.println(hobby);
        expressionParser.parseExpression("hobbies[0]").setValue(context, "Basketball");
        hobby = expressionParser.parseExpression("hobbies[0]").getValue(context, String.class);
        System.out.println(hobby);

        boolean gender = expressionParser.parseExpression("gender").getValue(context, Boolean.class);
        System.out.println(gender);

        expressionParser.parseExpression("gender").setValue(context, "0");
        gender = expressionParser.parseExpression("gender").getValue(context, Boolean.class);
        System.out.println(gender);

        int[] numbers = (int[])expressionParser.parseExpression("new int[]{1,2,3}").getValue(context);
        List<Integer> nums = (List<Integer>) expressionParser.parseExpression("{1,2,3,4}").getValue(context);
        List<List<Integer>> num2 = (List<List<Integer>>) expressionParser.parseExpression("{{1,2},{3,4}}").getValue(context);
        Map<String, Object> map = (Map<String, Object>) expressionParser.parseExpression("{'name': 'maslke', 'age':30}").getValue(context);
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }
}
