package org.sda.springboot.beans;

import org.springframework.stereotype.Service;

@Service
public class Bean2 {

    public Bean2() {
        System.out.println(getClass().getSimpleName() + " created");

    }
}
