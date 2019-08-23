package com.juaracoding.absensi.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyDataFactory {


    public List<String> createDataSimpleList(){

        List<String> list = new ArrayList<String>();

        list.add("Maulana");
        list.add("Christian");
        list.add("Aldi");
        list.add("Jon");
        list.add("Jun");
        list.add("Bagus");
        list.add("Usmoyo");
        list.add("Stevan");

        return list;

    }


    public List<User> createUser (){

        List<User> todo = new ArrayList<User>();

        todo.add(new User(1,"user001","12345","Dewa","brata","","Laki-Laki",new Date(),"","","dewabrata","",""));
        todo.add(new User(2,"user001","12345","Dewi","brata","","Laki-Laki",new Date(),"","","dewabrata","",""));
        todo.add( new User(3,"user001","12345","Dewo","brata","","Laki-Laki",new Date(),"","","dewabrata","",""));


      return todo;
    }


}
