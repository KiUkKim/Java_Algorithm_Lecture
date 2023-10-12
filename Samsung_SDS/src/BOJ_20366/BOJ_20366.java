package BOJ_20366;

import java.util.*;
import java.io.*;

public class BOJ_20366 {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++)
		{
			for(int j = i + 1; j < N; j++)
			{
				int an = arr[i] + arr[j];
				
				int s = i + 1;
				int e = N - 1;
				
				while(true)
				{
					if(s == j)
					{
						s++;
						continue;
					}
					
					if(e == j)
					{
						e--;
						continue;
					}
					
					if(s == N -1 || s >= e)
					{
						break;
					}
					
					if(Math.abs(arr[s] + arr[e] - an) < ans)
					{
						ans = Math.abs(arr[s] + arr[e] - an);
					}
					
					if(arr[s]+arr[e] == an)
					{
						ans = 0;
						break;
					}
					
					else if(arr[s] + arr[e] < an)
						s++;
					
					else if(arr[s] + arr[e] > an)
					{
						e--;
					}
				}
			}
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}

}
