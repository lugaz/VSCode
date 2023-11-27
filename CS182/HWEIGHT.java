package CS182;

import javax.naming.spi.DirStateFactory.Result;

public class HWEIGHT{
public static void main(String[] args){
    int result = 0;
    int n = 5;
    for (int j =1; j<=n; j++){
        result = result + FunRec(j);
    }
    System.out.print(result);
}

public static int FunRec(int k){
    if (k==0){
        return 0;
    }else{
        return (FunRec(k-1)+k);
    }
}
}