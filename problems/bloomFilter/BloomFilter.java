package bloomFilter;

import java.util.BitSet;

public class BloomFilter {
    private static final int SIZE = 1<<24;
    BitSet bitSet=new BitSet(SIZE);
    Hash[] hashs=new Hash[8];
    private static final int seeds[]=new int[]{3,5,7,9,11,13,17,19};

    public BloomFilter(){
        for (int i = 0; i < seeds.length; i++) {
            hashs[i]=new Hash(seeds[i]);
        }
    }
    public void add(String string){
        for(Hash hash:hashs){
            bitSet.set(hash.getHash(string),true);
        }
    }
    public boolean contains(String string){
        boolean have=true;
        for(Hash hash:hashs){
            have&=bitSet.get(hash.getHash(string));
            if (!have) return have;
        }
        return have;
    }
    class Hash{
        private int seed = 0;
        public Hash(int seed){
            this.seed=seed;
        }
        public int getHash(String string){
            int val=0;
            int len=string.length();
            for (int i = 0; i < len; i++) {
                val=val*seed+string.charAt(i);
            }
            return val&(SIZE-1);
        }
    }


    public static void main(String[] args) {
        String email="wskqing@qq.com";
        BloomFilter bloomDemo=new BloomFilter();
        System.out.println(email+"是否在列表中： "+bloomDemo.contains(email));
        bloomDemo.add(email);
        System.out.println(email+"是否在列表中： "+bloomDemo.contains(email));
        email="wskqing@qq.com";
        System.out.println(email+"是否在列表中： "+bloomDemo.contains(email));
    }
}
