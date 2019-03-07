package com.maslke.spring.ioc;

import java.beans.PropertyEditorSupport;

/**
 * @author:maslke
 * @date:3/7/2019
 * @version:0.0.1
 */
public class CustomCarEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || !text.contains(";")) {
            throw new IllegalArgumentException("illegal format");
        }
        String[] data = text.split(";");
        Car car = new Car();
        car.setBrand(data[0]);
        car.setColor(data[1]);
        car.setMaxSpeed(Integer.parseInt(data[2]));

        setValue(car);
    }
}
