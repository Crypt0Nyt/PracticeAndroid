package com.example.helloworld.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
        public String name;             //name of course
        public boolean isChecked;       //checkBox
                                        // false by default so only name passed in constructor

        public Item(String name) {
            this.name = name;
        }


        public static List<Item> listFromCoursesStrings(List<String> coursesStrings){
            List<Item> courses = new ArrayList<>();

            for (String courseName : coursesStrings){
                courses.add(new Item(courseName));
            }

            return courses;
        }
}
