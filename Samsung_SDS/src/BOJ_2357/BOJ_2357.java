package BOJ_2357;

import java.util.*;
import java.io.*;

public class BOJ_2357 {

	static int N, M;
	static List<Integer> arr;
	static int[] Mintree, Maxtree;
	static int S = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		while(S < N)
		{
			S *= 2;
		}
		
		Mintree = new int[S * 2 + 1];
		Maxtree = new int[S * 2 + 1];
		
		int minValue = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++)
		{
			minValue = Math.min(minValue, arr.get(i));
		}
		
		int maxValue = -1;
		
		for(int i = 0; i < N; i++)
		{
			maxValue = Math.max(maxValue, arr.get(i));
		}
		
				
		for(int i = 0; i < M; i++)
		{
			
			st = new StringTokenizer(br.readLine());
			
			int secA, secB;
			
			secA = Integer.parseInt(st.nextToken());
			
			secB = Integer.parseInt(st.nextToken());
			
			int tmp_min = Integer.MAX_VALUE;
			int tmp_max = -1;
			
			for(int k = secA; k <= secB; k++)
			{
				tmp_min = Math.min(tmp_min, arr.get(i-1));
				
				tmp_max = Math.max(tmp_max, arr.get(i-1));
			}

			
			int minNum = query(1, S, 1, secA, secB, Mintree, false);
			
			int maxNum = query(1, S, 1, secA, secB, Maxtree, true);
			
			bw.write(minNum + " " + maxNum + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
		
//		for(int x : Mintree)
//		{
//			System.out.print(x + " ");
//		}
//		
//		System.out.println();
//		
//		for(int x : Maxtree)
//		{
//			System.out.print(x + " ");
//		}
		
	}
	
	static int query(int left, int right, int node, int queryLeft, int queryRight, int[] tree, boolean flag)
	{
		if(right < queryLeft || left > queryRight)
		{
			return 0;
		}
		else if(queryLeft <= left && right <= queryRight)
		{
			return tree[node];
		}
		else {
			int mid = (left + right) / 2;
			
			if(flag == false)
			{
				int min_left = query(left, mid, node * 2, queryLeft, queryRight, tree, flag);

				if(min_left == 0)
					min_left = Integer.MAX_VALUE;
				int min_right = query(mid +1, right, node * 2 + 1, queryLeft, queryRight, tree, flag);
				
				if(min_right == 0)
					min_right = Integer.MAX_VALUE;
				
				return Math.min(min_left, min_right);
			}
			
			else
			{
				int max_left = query(left, mid, node * 2, queryLeft, queryRight, tree, flag);
				int max_right = query(mid +1, right, node * 2 + 1, queryLeft, queryRight, tree, flag);
				
				return Math.max(max_left, max_right);
			}
		}	
	}
	
	static void MinUpdate(int left, int right, int node, int[] Tree)
	{
		// 각 트리 구성해주기
		if(left == right)
		{
			Tree[node] = Tree[left];
			return;
		}
		else
		{	
			int mid = (left + right) / 2;
			
			MinUpdate(left, mid, node * 2, Tree);
			
			MinUpdate(mid + 1, right, node * 2 + 1, Tree);
			
			
		}
	}
	
	static void MaxUpdate(int left, int right, int node, int value, int[] Tree)
	{
		// 각 트리 구성해주기
		if(left == right)
		{
			Tree[node] = value;
			return;
		}
		else
		{
			Tree[node] = value;
			
			int mid = (left + right) / 2;
			
			int leftVal = Integer.MAX_VALUE;
			// 가장 왼쪽의 값으로 업데이트해줘야한다.
			if(left - 1 < arr.size())
				leftVal = arr.get(left - 1);
			update(left, mid, node * 2, leftVal, Tree);
			
			int rightVal = Integer.MAX_VALUE;
			if(mid < arr.size())
				rightVal = arr.get(mid);
			update(mid + 1, right, node * 2 + 1, rightVal, Tree);
		}
	}
}
