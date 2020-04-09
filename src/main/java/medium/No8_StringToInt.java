package medium;

public class No8_StringToInt {

    public static void main(String[] args) {
        No8_StringToInt no = new No8_StringToInt();
        no.test();
    }

    public void test() {
//        System.out.println((1 << 31) - 1);
//        System.out.println(-1 << 31);
//        System.out.println(0x7fffffff);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(0x80000000);
//
//        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi(" -42"));
        System.out.println(myAtoi("3.154"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("+1"));
        System.out.println(myAtoi("+-1"));
        System.out.println(myAtoi("  -0012a42"));
        System.out.println(myAtoi("12 345"));
        System.out.println(myAtoi(""));
        System.out.println(myAtoi("0-1"));
        System.out.println(myAtoi("0-1"));
        System.out.println(myAtoi("-2147483648"));
        System.out.println(myAtoi("2147483646"));
    }

    public int myAtoi(String str) {
        int len=str.length();
        int index=0;
        while(index <len &&str.charAt(index)==' ')index+=1;
        if(index ==len) return 0;

        int isInf=1;
        String newStr=str.substring(index);
        if(newStr.charAt(0)=='-' || newStr.charAt(0)=='+'){
            if(newStr.charAt(0)=='-') isInf=-1;
            newStr=newStr.substring(1);
        }
        Long result=0L;
        for(int i=0;i<newStr.length();i++){
            if(Character.isDigit(newStr.charAt(i))){
                result=result*10+(int)newStr.charAt(i) -48;
                if(isInf==1 &&result >= (1<<31)-1) return (1<<31)-1;
                if(isInf==-1&&-result <= (-1<<31)) return -1<<31;
            }else{
                return result.intValue()*isInf;
            }
        }
        return result.intValue()*isInf;


    }
}
