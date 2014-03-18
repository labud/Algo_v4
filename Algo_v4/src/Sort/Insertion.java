package Sort;

public class Insertion extends Sort{
	public static void sort(Comparable[] a){
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		for(int i = lo ; i <= hi; i++){
			for(int j = i ; j > 0 && less(a[j],a[j-1]); j--)
				exch(a,j,j-1);
		}
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before InsertionSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}