package BOJ_13997;

import java.util.*;
import java.io.*;

public class BOJ_13997 {

	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		arr = new int[N + 1];
		
		int left = 0;
		int right = 0;
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			
			right = Math.max(right, arr[i]);
		}
		
		int answer = right;
		
		while(left <= right)
		{
			int mid = (left + right ) / 2;
			
			// 가능하면 값을 줄여본다.
			if(isValid(mid))
			{
				answer = Math.min(answer, mid);
				right = mid - 1;
			}
			
			else {
				left = mid + 1;
			}
		}
		
		bw.write(left + " ");
		bw.flush();
		bw.close();
		
	}

	static boolean isValid(int mid)
	{
		int cnt = 1;
		int min = arr[0];
		int max = arr[0];
		
		for(int i = 1; i < N; i++)
		{
			if(min > arr[i])
			{
				min = arr[i];
			}
			
			if(max < arr[i])
			{
				max = arr[i];
			}
			
			if(max - min > mid)
			{
				cnt++;
				min = arr[i];
				max = arr[i];
			}
		}
		
		return cnt <= M;
	}
}
