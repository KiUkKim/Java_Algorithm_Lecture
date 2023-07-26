package Day02_BOJ_1806;

import java.util.*;
import java.io.*;
import java.io.*;

public class BOJ_1806 {

	static int N, S;
	static int[] arr;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{

		System.setIn(new FileInputStream("C:\\\\Users\\\\KIUK\\\\eclipse-workspace\\\\Samsung_SDS\\\\src\\\\Day02_BOJ_1806\\\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = arr[0];
		
		while(true)
		{
			if(sum < S)
			{
				sum += arr[++end];
			}
			
			else if(sum >= S){
				ans = Math.min(ans,  (end - start + 1));
				
				sum -= arr[start++];
			}
			
			if(end >= N)
			{
				break;
			}
		}
		
		if(ans == Integer.MAX_VALUE)
		{
			System.out.println(0);
		}
		
		else {
			System.out.println(ans);
		}
	}

}
