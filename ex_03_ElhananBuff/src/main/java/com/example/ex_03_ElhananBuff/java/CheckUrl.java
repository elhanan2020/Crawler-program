package com.example.ex_03_ElhananBuff.java;

/**
 * its the class that store all data that i want to save about any threads
 */
public class CheckUrl {
    /**
     * the url that i want to run over a crawler code
     */
    private final String url;
    /**
     * the number of image that i get on all sub links
     */
    private int numImg ;
    /**
     * variable that mark if the thread yet run or died
     */
    private boolean isDone ;

    /**
     * constractor
     * @param url the url to check
     */
    public CheckUrl(String url){
        this.url = url;
        isDone = false;
        numImg = 0;
    }

    /**
     * this function update the local variable "numImage"
     * @param number the number of image of a some sub links
     */
    public void setImg(int number){  numImg += number; }

    /**
     * this function return the current number of image
     * @return number of image
     */
    public int getImg(){
       return numImg;
    }

    /**
     *  this function set the state of the thread to runing     *
     */
      public void setDoneTrue(){isDone = true;}

    /**
     *  this function set  the state of the thread to died     *
     */
    public void setDoneFalse(){isDone = false;}
    /**
     * this function return the state of the thread died or runing
     * @return the url of an some thread
     */
    public boolean Done(){return isDone;}

    /**
     * this function return the current url
     * @return the url of an some thread
     */
    public String getUrl(){return url;}


}
