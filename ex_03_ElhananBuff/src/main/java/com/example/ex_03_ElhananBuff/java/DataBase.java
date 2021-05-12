package com.example.ex_03_ElhananBuff.java;


import java.util.HashMap;



public class DataBase {
    private final HashMap<String, CheckUrl>  myMap ;

   public DataBase(){
        this.myMap = new HashMap<>();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void setNewThread(String key,CheckUrl value){
        this.myMap.put(key,value);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public synchronized void setNumOfImg(String id ,int numImg){
       myMap.get(id).setImg(numImg);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public synchronized int getNumOfImg(String id ){

       return  myMap.get(id).getImg();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public synchronized String isDone(String id){
        if(myMap.get(id).Done())
            return "<p>still crawling <a href=\"ShowResultServlet\">reload this page </a>for final result</p>";
        return "<p>Done !</p>";
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public synchronized void setDone(String id,boolean light){
        if(light)
            myMap.get(id).setDoneTrue();
        else
            myMap.get(id).setDoneFalse();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public synchronized String url(String id){

        return myMap.get(id).getUrl();
    }


}
