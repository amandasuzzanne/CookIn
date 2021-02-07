package com.amanda;

/**
 * The student class.
 */
public class Student {

    /**
     * The student name.
     */
    private String name;

    /**
     * The student registration number.
     */
    private String regNo;

    /**
     * the constructor.
     * @param name The student name.
     * @param regNo The student registration number.
     */
    
    public Student(final String name, final String regNo){
        this.name = name;
        this.regNo = regNo;
    }
    
    public String getName() {
        return this.name;
    }

    public String getRegNo() {
        return this.regNo;
    }

}