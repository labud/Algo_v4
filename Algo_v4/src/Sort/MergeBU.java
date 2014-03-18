package Sort;
public class MergeBU extends Sort{
	private static Comparable[] aux;
	private static int threshold = 28;
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(hi - lo <= threshold){
			Insertion.sort(a, lo, hi);
			return ;
		}
		int N = hi - lo + 1;
		for(int sz = 1; sz < N ; sz += sz)
			for(int i = lo; i < N - sz ; i += sz + sz)
				merge(a, i, i + sz - 1, Math.min(i + sz + sz - 1, hi));
	}
	
	protected static void merge(Comparable[] a, int lo, int mid, int hi){
		int i = lo, j = mid +1;
		
		for(int k  = lo ; k <= hi ; k++)
			aux[k] = a[k];
		
		for(int  k = lo ;  k <= hi ; k++)
			if		(i > mid) 				a[k] = aux[j++];
			else if	( j > hi)				a[k] = aux[i++];
			else if (less(aux[j],aux[i]))	a[k] = aux[j++];
			else							a[k] = aux[i++];
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before MergeBUSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}