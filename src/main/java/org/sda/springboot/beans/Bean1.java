package org.sda.springboot.beans;


import org.springframework.stereotype.Component;

@Component
public class Bean1 {
    public Bean1() {
        System.out.println(getClass().getSimpleName()+" created");
    }
}
