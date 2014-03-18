package Sort;

public class Selection extends Sort{
	public static void sort(Comparable[] a){
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo ,int hi){
		for(int i = lo ; i <= hi; i++){
			int min = i;
			for(int j = i + 1 ; j <= hi ; j++)
				if(less(a[j], a[min]))
						min = j;
			exch(a,i,min);
		}
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before SelectionSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}