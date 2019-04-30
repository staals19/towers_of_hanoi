import java.util.*;
public class main{
    static Scanner input = new Scanner(System.in);
    static tower left = new tower("left");
    static tower middle = new tower("middle");
    static tower right = new tower("right");
    static int moves = 0;
    static boolean valid = true;
    
    public static void main(String[] args){
        System.out.println("How many disks?");
        int num = Integer.parseInt(input.next());
        for(int i=num; i>0; i--){
            disk d = new disk(i);
            left.add(d);
        }
        
        
        if(num % 2 == 0){
            solveE(num, valid);
        }
        else{
            solveO(num, valid);
        }
        System.out.println("Moves: " + moves);
    }
    
    public static void solveE(int num, boolean valid){
        while(right.disks.size() != num && valid == true){
          move2(left);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
            
          intMove();
          printTowers();
          
          move1(middle,right);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
            
          intMove();
          printTowers();
          
          move1(right,left);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
          
          intMove();
        }
    }
    
    public static void solveO(int num, boolean valid){
        while(right.disks.size() != num && valid == true){
          move1(left,right);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
            
          intMove();
          printTowers();
          
          move2(right);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
            
          intMove();
          printTowers();
          
          move1(middle,left);
          printTowers();
          if(right.disks.size() == num){
              break;
            }
          
          intMove();
        }
    }
    
    public static void intMove(){
        tower tower1 = new tower("");
        tower tower2 = new tower("");
        if(left.disks.size() > 0 && left.disks.get(0).value == 1){
            tower1 = middle;
            tower2 = right;
        }
        else if(middle.disks.size() > 0 && middle.disks.get(0).value == 1){
            tower1 = left;
            tower2 = right;
        }
        else{
            tower1 = middle;
            tower2 = left;
        }
        tower big = new tower("");
        tower small = new tower("");
        if(tower1.disks.size() == 0 || tower2.disks.size() == 0){
            if(tower1.disks.size() == 0){
                small = tower1;
                big = tower2;
            }
            else{
                small = tower2;
                big = tower1;
            }
            move(big,small);
        }
        else{
            if(tower1.disks.get(0).value > tower2.disks.get(0).value){
            big = tower1;
            small = tower2;
         }
         else{
            big = tower2;
            small = tower1;
         } 
         move(small, big);
         } 
    }
    
    
    public static void move1(tower start, tower end){ //getting top three to end
        tower mid = new tower("");
        if(start.position.equals("middle") && end.position.equals("right")){
            mid = left;
        }
        else if(start.position.equals("middle") && end.position.equals("left")){
            mid = right;
        }
        else{
            mid = middle;
        }
  
        move(start, end);
        move(start, mid);
        move(end, mid);
        move(start, end);
        move(mid, start);
        move(mid, end);
        move(start, end);
    }
    
    public static void move2(tower start){ //getting top three to middle
        tower end = new tower("");
        if(start.position.equals("left")){
            end = right;
        }
        else{
            end = left;
        }
        
        move(start, middle);
        move(start, end);
        move(middle, end);
        move(start, middle);
        move(end, start);
        move(end, middle);
        move(start, middle);
    }
    
    public static boolean odd(tower t){
        if(t.disks.size() % 2 == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    public static void move(tower o, tower n){
        //System.out.println(o.position + ", " + n.position);
        n.add(o.disks.get(0));
        o.delete(0);
        //System.out.println(n.check());
        moves++;
        if(n.check() == false){
            System.out.println("INVALID MOVE");
            valid = false;
        }
    }
    
    public static void printTowers(){
        left.print();
        middle.print();
        right.print();
        System.out.println();
    }
}
