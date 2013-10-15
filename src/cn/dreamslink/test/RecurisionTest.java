package cn.dreamslink.test;

import java.math.BigDecimal;
import java.util.*;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 10/12/13
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecurisionTest {
    private int[] counter;
    private int counterIndex;

    @Test
    public void addResults() throws Exception{
        //
        List<String> legs = new ArrayList<String>();
        legs.add("legs1");
        legs.add("legs2");
        legs.add("legs3");

        //each leg's corresponding cabins
        List<String> legs1Cabin = new ArrayList<String>();
        legs1Cabin.add("lowFare");
        legs1Cabin.add("first");
        List<String> legs2Cabins = new ArrayList<String>();
        legs2Cabins.add("first");
        legs2Cabins.add("bedroom");
        List<String> legs3Cabins = new ArrayList<String>();
        legs3Cabins.add("lowFare");
        legs3Cabins.add("first");
        legs3Cabins.add("bedroom");

        //map.get(0)====list.get(0)
        Map<Integer, List<String>> legtoclass=new HashMap<Integer, List<String>>();
        legtoclass.put(0,legs1Cabin);
        legtoclass.put(1,legs2Cabins);
        legtoclass.put(2,legs3Cabins);

        counter = new int[legtoclass.size()];
        counterIndex = legtoclass.size()-1;
        BigDecimal totalCombines = new BigDecimal("1");
        int i=0;
        //totalCombines
        while (i<legtoclass.size()) {
            totalCombines = totalCombines.multiply(BigDecimal.valueOf(legtoclass.get(i).size()));
            i++;
        }

        for (int j=0;j<totalCombines.toBigInteger().intValue();j++){
            for (int k=0;k<legtoclass.size();k++){
                legs.get(k);
                legtoclass.get(k).get(counter[k]);
                System.out.print(legs.get(k) + "    " + legtoclass.get(k).get(counter[k]) + "        ");
            }
            System.out.println();
            handle(legtoclass);

        }
    }

    public void handle(Map<Integer, List<String>> legtoclass) {
        counter[counterIndex]++;
        if (counter[counterIndex] >= legtoclass.get(counterIndex).size()) {
            counter[counterIndex] = 0;
            counterIndex--;
            if (counterIndex >= 0) {
                handle(legtoclass);
            }
            counterIndex = legtoclass.size() - 1;
        }
    }

    @Test
    public void dikaert(){
        List<String> l1 = Arrays.asList(new String[]{"a", "b", "c"});
        List<String> l2 = Arrays.asList(new String[]{"1","2","3"});
        List<String> l3 = Arrays.asList(new String[]{"x","y","z"});

        List<List<String>> all = new ArrayList<List<String>>();
        all.add(l1);
        all.add(l2);
        all.add(l3);

        List<String> result = tailRecursiveDikaert(all.get(0),1,all);
        for(String ss : result){
            System.err.print(ss+"  ");
        }

    }

    private List<String> tailRecursiveDikaert(List<String> result,int index, List<List<String>> all){
        List<String> finallResult = new ArrayList<String>() ;
        List<String> next = all.get(index);
        for(int i = 0; i< result.size(); i ++){
            for(int j = 0 ; j < next.size(); j++){
                finallResult.add(result.get(i) + next.get(j));
            }
        }
        if(index < all.size() -1 ){
            return tailRecursiveDikaert(finallResult, index + 1, all);
        }else{
            return finallResult;
        }
    }
}
