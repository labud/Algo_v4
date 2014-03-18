package Sort;

public class Shell extends Sort{
	public static void sort(Comparable[] a){
		sort(a, 0, a.length - 1);
	}
		
	public static void sort(Comparable[] a, int lo, int hi){
		int N  = hi - lo +1;
		int h = 1;
		while(h < N/3) h *= 3;
		while(h>=1){
			for(int i = h + lo  ; i < hi; i++)
				for(int j = i ; j >= h && less(a[j],a[j-h]) ; j -= h)
					exch(a, j, j-h);
			h = h/3;
		}
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before ShellSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}