package easy;

public class No13_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs==null || strs.length==0) return "";
        String head=strs[0];
        for(int i=0;i<head.length();i++){
            Character a=head.charAt(i);
            for(int j=0;j<strs.length;j++){
                if(i< strs[j].length()){
                    if(strs[j].charAt(i)!=a)return head.substring(0,i);
                }else return head.substring(0,i);
            }
        }
        return strs[0];
    }


    /**
     * 分治算法
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {

    }

    /**
     * 二分算法
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {

    }
    public static void main(String[] args) {
        No13_LongestCommonPrefix test=new No13_LongestCommonPrefix();
        String[] strs={"flower","flow","fight"};
        System.out.println(test.longestCommonPrefix(strs));
    }
}
