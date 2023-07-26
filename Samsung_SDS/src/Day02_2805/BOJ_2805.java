package Day02_2805;

import java.util.*;
import java.io.*;

public class BOJ_2805 {

	static int N, M, ans;
	static int[] tree;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("C:\\\\Users\\\\KIUK\\\\eclipse-workspace\\\\Samsung_SDS\\\\src\\\\Day02_2805\\\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());

		int start = 0;
		int end = 0;
		
		for(int i = 0; i < N; i++)
		{
			tree[i] = Integer.parseInt(st.nextToken());
			
			end = Math.max(end, tree[i]);
		}
		
		
		while(start <= end)
		{
			int mid = (start + end) / 2;
			long sum = 0;
			
			for(int i = 0; i < N; i++)
			{
				if(tree[i] >= mid)
				{
					sum += tree[i] - mid;	
				}
			}
			
			if(sum < M)
			{
				end = mid - 1;
			}
			
			else if(sum > M)
			{
				ans = mid;
				start = mid + 1;
			}
			
			else {
				ans = mid;
				break;
			}
		}
		
		System.out.println(ans);
	}

}
