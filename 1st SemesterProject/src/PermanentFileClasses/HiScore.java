/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PermanentFileClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Krongrah
 */
public class HiScore {
    
List<Score> HiScoreList=new ArrayList<>();
File file =new File("HiScore.xml");   
//Scanner input = new Scanner(file));

//while (input.hasNextLine())
//{
//   System.out.println(input.nextLine());
//}



private int maxPoints;


    
public void applyForHiScore(int points){
maxPoints=points;
}    
    
    
}
