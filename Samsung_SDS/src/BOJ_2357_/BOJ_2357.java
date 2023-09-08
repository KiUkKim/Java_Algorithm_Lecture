package BOJ_2357_;

import java.util.*;
import java.io.*;

public class BOJ_2357 {

	static int N, M;
	static int S = 1;
	static int[] tree, lazy;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		while(S < N)
		{
			S *= 2;
		}
		
		tree = new int[S * 2 + 1];
		lazy = new int[S * 2 + 1];
		
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd, a, b;
			
			cmd = Integer.parseInt(st.nextToken());
			
			a = Integer.parseInt(st.nextToken());
			
			b = Integer.parseInt(st.nextToken());
			
			if(cmd == 0)
			{
				update(1, S, 1, a, b);
			}
			
			else {
				bw.write(query(1, S, 1, a, b) + " ");
				bw.newLine();
			}
			
//			System.out.println();
//			
//			for(int x : tree)
//			{
//				System.out.print(x + " ");
//			}
//			
//			System.out.println();
//			
//			for(int x : lazy)
//			{
//				System.out.print(x + " ");
//			}
//			
//			System.out.println();
		}
		
		bw.flush();
		bw.close();
	}
	
	static void lazyUpdate(int left, int right, int node)
	{
		if(lazy[node] % 2 == 1)
		{
			if(left != right)
			{
				lazy[node * 2 + 1] += lazy[node];
				
				lazy[node * 2] += lazy[node];
			}
			
			tree[node] = (right - left + 1) - tree[node];
			
			lazy[node] = 0;
		}
	}
	
	static void update(int left, int right, int node, int targetLeft, int targetRight)
	{
		lazyUpdate(left, right, node);
		
		if(right < targetLeft || targetRight < left)
			return;
		
		else if(targetLeft <= left && right <= targetRight)
		{
			lazy[node] = 1;
			
			lazyUpdate(left, right, node);
			
			return;
		}
		
		int mid = (left + right ) / 2;
		
		update(left, mid, node * 2, targetLeft, targetRight);
		
		update(mid + 1, right, node * 2 + 1, targetLeft, targetRight);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static int query(int left, int right, int node, int queryLeft, int queryRight)
	{
		lazyUpdate(left, right, node);
		
		if(right < queryLeft || queryRight < left)
			return 0;
		else if(queryLeft <= left && right <= queryRight)
		{
			return tree[node];
		}
		else {
			int mid = (left + right) / 2;
			
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
