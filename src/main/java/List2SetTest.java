

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class List2SetTest {

    public static void main(String[] args) {
        List<String[]> list=new ArrayList<String[]>();
        String[] list1=new String[3];
        list1[0]="1";list1[2]="2";list1[2]="3";

        String[] list2=new String[3];
        list2[0]="1";list2[2]="2";list2[2]="3";

        list.add(list1);
                list.add(list2);

        HashSet<String[]> set = new HashSet<String[]>(list);
        System.out.println(set);
    }
}
