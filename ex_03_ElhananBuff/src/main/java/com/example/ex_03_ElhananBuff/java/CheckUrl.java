package com.example.ex_03_ElhananBuff.java;

public class CheckUrl {
    private String url;
    private int numImg ;
    private boolean isDone ;
    public CheckUrl(String url){
        this.url = url;
        isDone = false;
        numImg = 0;
    }
    public void setImg(int number){  numImg += number; }

    public int getImg(){
       return numImg;
    }

    public void resetNum(){numImg = 0;}

    public void setDoneTrue(){isDone = true;}

    public void setDoneFalse(){isDone = false;}

    public boolean Done(){return isDone;}

    public String getUrl(){return url;}


}
