package Day02_BOJ_2003;

import java.io.FileInputStream;
import java.util.*;
import java.io.*;

public class BOJ_2003 {

	static int N, M;
	static int ans = 0;

	static int[] arr;
	
	public static void main(String[] args) throws IOException{

		System.setIn(new FileInputStream("C:\\\\Users\\\\KIUK\\\\eclipse-workspace\\\\Samsung_SDS\\\\src\\\\Day02_BOJ_2003\\\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = arr[start];
		
		while(start <= end && end < N)
		{
			// 원하는 값보다 작을 경우
			if(sum < M)
			{	
				end++;
				
				sum += arr[end];					
			}
			
			else if(sum > M)
			{
				sum -= arr[start];
				
				start++;
			}
			
			else if(sum == M)
			{
				ans++;
				
				end++;
				
				sum += arr[end];		
			}
		}
		
		System.out.println(ans);
	}

}
