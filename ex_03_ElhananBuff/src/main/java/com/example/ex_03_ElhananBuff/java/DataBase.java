package com.example.ex_03_ElhananBuff.java;


import java.util.HashMap;


/**
 * this class store in a map all data of any thread
 */
public class DataBase {
    private final HashMap<String, CheckUrl>  myMap ;

   public DataBase(){
        this.myMap = new HashMap<>();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * this function initialize the map for any thread
     * @param key the key we wat to enter in the map
     * @param value the class that contain all data about this thread
     */
    public void setNewThread(String key,CheckUrl value){
        this.myMap.put(key,value);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * this function update the number of image
     * * @param id the id of this thread
     * @param numImg current number
     */
    public synchronized void setNumOfImg(String id ,int numImg){
       myMap.get(id).setImg(numImg);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * this function return the number of  image that this thread fund
     * @param id the id of this thread
     * @return the number of image found
     */
    public synchronized int getNumOfImg(String id ){

       return  myMap.get(id).getImg();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /**
     *   this function get from the data base about an specified thread
     *   if is dead and in function of the response return an string
     * @param id the id of this thread
     */
    public synchronized String isDone(String id){
        if(myMap.get(id).Done())
            return "<p>still crawling <a href=\"ShowResultServlet\">reload this page </a>for final result</p>";
        return "<p>Done !</p>";
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * this function update a boolean variable that mark if the thread yet running
     * @param id the id of this thread-
     * @param light light mark if the thread yet running
     * @return string
     */

    public synchronized void setDone(String id,boolean light){
        if(light)
            myMap.get(id).setDoneTrue();
        else
            myMap.get(id).setDoneFalse();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * this function extract from the data base the current url
     * @param id the id of this thread
     * @return a string
     */
    public synchronized String url(String id){

        return myMap.get(id).getUrl();
    }


}
