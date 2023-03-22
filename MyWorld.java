import lang.stride.*;
import java.util.*;
import java.util.Random;
import greenfoot.*;

public class MyWorld extends World
{
    private int randBoundaryX = 450, randBoundaryY = 300;
    Random rand = new Random();
    public MyWorld()
    {
        super(600, 400, 1);
        addObject(new Pacman(),100, 100);
        for(int i=0;i<3;i++)
            addObject(new Cherry(),rand.nextInt(randBoundaryX),rand.nextInt(randBoundaryY));
    }
}
