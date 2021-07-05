import java.util.Random;

public class ParallelArr {

	public static void main (String[] args) {

		Random random = new Random();
		int  [] arr = new int[200000000];
		int sum = 0;



		//put random numbers in array
		for (int i =0; i<200000000 ; i++) {
			arr[i] = random.nextInt(10);
		}
		long startTime = System.currentTimeMillis();
		for ( int i =0; i<200000000 ; i++) {
			sum += arr[i];
		}
		long stopTime =System.currentTimeMillis();
		long time = stopTime - startTime;
		System.out.println("time without parallel array "+time+ " Milliseconds");
		System.out.println("Sum without parallel array equals "+ sum);

		startTime = System.currentTimeMillis();
		AddPart a1 = new AddPart(arr,0,100000000);
		AddPart a2 = new AddPart(arr,100000001,200000000-1);

		Thread t1 =new Thread(a1);
		Thread t2 =new Thread(a2);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		long total = a1.sum + a2.sum;
		stopTime = System.currentTimeMillis();
		time = stopTime - startTime;
		System.out.println("The time for parallel array is " + time+ " Milliseconds");
		System.out.println("The sum for parallel array is " + total);
	}


}
class AddPart implements Runnable{
	int P_arr [];
	int start,end;
	public AddPart(int arr[], int start, int end) {
		P_arr = arr;
		this.start = start;
		this.end = end;


	}
	long sum =0;
	@Override
	public void run() {

		for(int i = start; i <=end; i++)	{
			sum += P_arr[i];

		}

	}

}

