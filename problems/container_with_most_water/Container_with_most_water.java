package container_with_most_water;


public class Container_with_most_water {

    public static int maxArea(int[] height){
        int max_water=0;
        int start=0;
        int end=height.length-1;

        while(start<end){
            if (height[start]<=height[end]){
                max_water = Math.max(height[start] * (end - start),max_water);
                while(height[start+1]<=height[start] && start+1 <end) start++;
                start++;
            }else{
                max_water =  Math.max(height[end] * (end - start),max_water);
                while(height[end-1]<=height[end] && start <end-1) end--;
                end--;
            }
        }
        return max_water;
    }

    public static void main(String[] args) {
        int max_water = maxArea(new int[]{2, 4, 5, 7, 3, 9, 6, 5, 3});

        System.out.println(max_water);

    }

}
