package BOJ_14497;

import java.util.*;
import java.io.*;

public class BOJ_11497 {

	static int TC;
	static int[] tree, makeTree;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		
		while(TC-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			tree = new int[N];
			makeTree = new int[N];
			
			for(int i = 0; i < N; i++)
			{
				tree[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(tree);
			
			makeTree[0] = tree[0];
			
			int left = 1;
			int right = N - 1;
			
			for(int i = 1; i< N; i++)
			{
				if(i % 2 == 1)
				{
					makeTree[right--] = tree[i]; 
				}
				else {
					makeTree[left++] = tree[i];
				}
			}

			int ans = Integer.MIN_VALUE;
			
			ans = Math.max(ans, Math.abs(makeTree[N-1] - makeTree[0]));
			
			for(int i = 0; i < N - 1; i++)
			{
				ans = Math.max(ans, Math.abs(makeTree[i+1]- makeTree[i]));
			}
			
			bw.write(ans + "\n");
		}
		
		bw.flush();
		bw.close();

	}

}
