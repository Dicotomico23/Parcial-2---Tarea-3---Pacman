import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Actor
{
    private final int DIRECTION_RIGHT = 0;
    private final int DIRECTION_LEFT = 1;
    private final int DIRECTION_DOWN = 2;
    private final int DIRECTION_UP = 3;
    private final int COUNTDOWN_MOUTH_START_VALUE = 20, COUNTDOWN_POINTS_START_VALUE = 40;
    private final int offsetX = 5, offsetY = 5;
    private int mouthDelay = COUNTDOWN_MOUTH_START_VALUE, PointsDelay = COUNTDOWN_POINTS_START_VALUE;
    private int imageIndex = 0, directionIndex = 0, direction = 0;
    private int score = 0, x, y, triggerPointsDelay;
    private String[][] images;
    public Pacman(){
        setImages();
    }
    public void act()
    {
        CheckKeyPressed();
        setDirection();
        ChangeImage();
        hasEatenCherry();
        UpdateScore();
    }
    private void setImages(){
        images = new String[2][4];
        images[0][DIRECTION_RIGHT] = "images/pacman-closed-right.png";
        images[1][DIRECTION_RIGHT] = "images/pacman-open-right.png";
        images[0][DIRECTION_LEFT] = "images/pacman-closed-left.png";
        images[1][DIRECTION_LEFT] = "images/pacman-open-left.png";
        images[0][DIRECTION_DOWN] = "images/pacman-closed-down.png";
        images[1][DIRECTION_DOWN] = "images/pacman-open-down.png";
        images[0][DIRECTION_UP] = "images/pacman-closed-up.png";
        images[1][DIRECTION_UP] = "images/pacman-open-up.png";
        setImage(images[0][0]);
    }
    private void ChangeImage(){
        mouthDelay--;
        if(mouthDelay == 0){
            imageIndex = (imageIndex + 1) % images.length;
             setImage(images[imageIndex][direction]);
             mouthDelay=COUNTDOWN_MOUTH_START_VALUE;
        }
    }
    private void setDirection(){
        switch(direction){
            case DIRECTION_RIGHT:
                setLocation(getX()+offsetX,getY());
                break;
            case DIRECTION_LEFT:
                setLocation(getX()-offsetX,getY());
                break;
            case DIRECTION_DOWN:
                setLocation(getX(),getY()+offsetY);
                break;
            case DIRECTION_UP:
                setLocation(getX(),getY()-offsetY);
                break;
        }
    }
    private void CheckKeyPressed(){
        if(Greenfoot.isKeyDown("left")){
            direction = DIRECTION_LEFT;
        }else if(Greenfoot.isKeyDown("right")){
            direction = DIRECTION_RIGHT;
        }else if(Greenfoot.isKeyDown("down")){
            direction = DIRECTION_DOWN;
        }else if(Greenfoot.isKeyDown("up")){
            direction = DIRECTION_UP;
        }
    }
    private void hasEatenCherry(){
        Cherry cherry = (Cherry)this.getOneIntersectingObject(Cherry.class);
        if(cherry!=null){
            score+=10;
            x=getX();
            y=getY();
            getWorld().showText("+10pts", x, y);
            triggerPointsDelay = 1;
            this.getWorld().removeObject(cherry);
        }
        if(triggerPointsDelay==1){
            StopPointsText();
        }
    }
    private void UpdateScore(){
        this.getWorld().showText("Score:"+ score, getWorld().getWidth()-100,50);
    }
    private void StopPointsText(){
        PointsDelay--;
        if(PointsDelay==0){
            this.getWorld().showText(null,x,y);
            triggerPointsDelay = 0;
            PointsDelay = COUNTDOWN_POINTS_START_VALUE;
        }
    }
}
