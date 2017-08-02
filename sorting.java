import java.util.Random;
public class sorting{
	private static Random rnd;
	
	public static void main(String[] args){
		System.out.println("Time Measured in Nanoseconds, UNSORTED Arrays:\n");
		rnd=new Random();
		int[] x,y,z,z2,z3;
		int size;
		long start;
		for(int n=1;n<=16;n++){
			size=(int)(Math.pow(2, n));
			x=new int[size];
			y=new int[size];
			z=new int[size];
			z2=new int[size];
			z3=new int[size];
			for(int i=0;i<size;i++){
				x[i]=rnd.nextInt(size);
				y[i]=x[i];
				z[i]=x[i];
				z2[i]=x[i];
				z3[i]=x[i];
			}
			
			System.out.println("For n="+size);
		
			System.out.print("InsertionSort: ");
			start=System.nanoTime();
			insertionSort(x,0,x.length-1);
			System.out.print((System.nanoTime()-start)+" ");
		
			System.out.print("MergeSort: ");
			start=System.nanoTime();
			mergeSort(y,0,y.length-1);
			System.out.print((System.nanoTime()-start)+" ");
		
			System.out.print("QuickSort1: ");
			start=System.nanoTime();
			quickSort1(z,0,z.length-1);
			System.out.print((System.nanoTime()-start)+" ");
			
			System.out.print("QuickSort2: ");
			start=System.nanoTime();
			quickSort2(z2,0,z2.length-1);
			System.out.print((System.nanoTime()-start)+" ");
			
			System.out.print("QuickSort3: ");
			start=System.nanoTime();
			quickSort3(z3,0,z3.length-1);
			System.out.println(System.nanoTime()-start);
		}
		System.out.println("\n\n\n");
		System.out.println("Time Measured in Nanoseconds, SORTED Arrays:\n");
		for(int n=1;n<=13;n++){
			size=(int)(Math.pow(2, n));
			x=new int[size];
			y=new int[size];
			z=new int[size];
			z2=new int[size];
			z3=new int[size];
			for(int i=0;i<size;i++){
				x[i]=i;
				y[i]=x[i];
				z[i]=x[i];
				z2[i]=x[i];
				z3[i]=x[i];
			}
			
			System.out.println("For n="+size);
		
			System.out.print("InsertionSort: ");
			start=System.nanoTime();
			insertionSort(x,0,x.length-1);
			System.out.print((System.nanoTime()-start)+" ");
		
			System.out.print("MergeSort: ");
			start=System.nanoTime();
			mergeSort(y,0,y.length-1);
			System.out.print((System.nanoTime()-start)+" ");
		
			System.out.print("QuickSort1: ");
			start=System.nanoTime();
			quickSort1(z,0,z.length-1);
			System.out.print((System.nanoTime()-start)+" ");
			
			System.out.print("QuickSort2: ");
			start=System.nanoTime();
			quickSort2(z2,0,z2.length-1);
			System.out.print((System.nanoTime()-start)+" ");
			
			System.out.print("QuickSort3: ");
			start=System.nanoTime();
			quickSort3(z3,0,z3.length-1);
			System.out.println(System.nanoTime()-start);
		}
	}
	private static void insertionSort(int[] x, int p, int q){
		int temp;
		//run through array
		for(int i=p; i<=q; i++){
			temp = x[i];
			int j=i-1;
			//sorts x[i] within array of integers preceding it
			while(j>=0 && temp<x[j]){
				x[j+1]=x[j];
				j--;	
			}
			x[j+1]=temp;
		}
	}
	/* ITERATIVE MERGESORT
	private static void mergeSort(int[] x){
		for(int size=1;size<=x.length-1;size*=2){
			for(int first=0;first<x.length-1;first+=(size*2)){
				int middle = Math.min(first + size - 1,x.length-1);
				int last = Math.min(first+(2*size)-1,x.length-1);
				merge(x, first, middle, last);
			}
		}
	}*/
	//RECURSIVE MERGESORT
	private static void mergeSort(int[] x,int p,int q){
		int mid;
		if(p<q){
			mid=(p+q)/2;
			mergeSort(x,p,mid);
			mergeSort(x,mid+1,q);
			merge(x,p,mid,q);
		}
	}
	private static void merge(int[] x, int first, int middle, int last){
		int[] lowerHalf=new int[middle-first+1];
		int[] upperHalf=new int[last-middle];
		int k=first;
		int i=0;
		int j=0;
		while(i<lowerHalf.length && j<upperHalf.length){
			if(x[i]<x[j]){
				x[k]=x[i];
				i++;
			}
			else{
				x[k]=x[j];
				j++;
			}
			k++;
		}
		while(i<lowerHalf.length){
			x[k]=x[i];
			k++;
			i++;
		}
		while(j<upperHalf.length){
			x[k]=x[j];
			k++;
			j++;
		}
	}
	private static void quickSort1(int[] x, int p, int q){
		int pivotPosition;
		if(p<q){
			pivotPosition=partition(x,p,q);
			quickSort1(x,p,pivotPosition-1);
			quickSort1(x,pivotPosition+1,q);
		}
	}
	private static int partition(int[] x, int p, int q){
		int pivot=x[p];
		int checklt=p+1;
		int checkgt=q;
		while(true){
			while(checklt<=q && x[checklt]<=pivot){
				checklt++;
			}
			while(checkgt>p && x[checkgt]>=pivot){
				checkgt--;
			}
			if(checklt<checkgt){
				int temp=x[checklt];
				x[checklt]=x[checkgt];
				x[checkgt]=temp;
			}else{
				break;
			}
		}
		x[p]=x[checkgt];
		x[checkgt]=pivot;
		return checkgt;
	}
	private static void quickSort2(int[] x, int p, int q){
		if(q-p+1<=16){
			insertionSort(x, p, q);
		}else{
			int pivotPosition;
			if(p<q){
				pivotPosition=partition(x,p,q);
				quickSort2(x,p,pivotPosition-1);
				quickSort2(x,pivotPosition+1,q);
			}
		}
	}
	private static void quickSort3(int[] x, int p, int q){
		int pivotPosition;
		if(p<q){
			if(q-p+1>16){
				int tempInt=x[p];
				int tempPos=p+rnd.nextInt(100)%(q-p+1);
				x[p]=x[tempPos];
				x[tempPos]=tempInt;
			}
			pivotPosition=partition(x,p,q);
			quickSort3(x,p,pivotPosition-1);
			quickSort3(x,pivotPosition+1,q);
		}
	}
}