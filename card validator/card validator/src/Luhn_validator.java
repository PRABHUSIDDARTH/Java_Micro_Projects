import java.util.*;
public class Luhn_validator{
    public static boolean Check_Valid(long card_num){
        String s = Long.toString(card_num);
        int[] arr = new int[s.length()];
        for(int i=0;i<s.length();i++){
            arr[i]=s.charAt(i)-'0';
        }
        if(s.length()!=16){
            return false;
        }
        int sum=0;
        for(int i=s.length()-1;i>=0;i--){
            if((s.length()-i)%2==0){
                int double_digit = arr[i]*2;
                if(double_digit>9){
                    double_digit = double_digit - 9;
                }
                sum+=double_digit;
            }
            else{
                sum+=arr[i];
            }
        }
        return sum % 10 == 0;

    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Card number: ");
        long card = sc.nextLong();
        System.out.println();


        if(Luhn_validator.Check_Valid(card)){
            System.out.println("Valid card! Go ahead and use it.");
        }
        else{
            System.out.println("Error in card!!. Check the digits!");
        }
        sc.close();

    }
}