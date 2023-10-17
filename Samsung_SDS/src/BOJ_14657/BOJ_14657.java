package BOJ_14657;

import java.util.*;
import java.io.*;

public class BOJ_14657 {

	static int N, M;
	static int[] indegree;
	static int[] dp;
	static List<Node> list;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		indegree = new int[N + 1];
		
		dp = new int[N + 1];
		
		for(int i = 0; i < M; i++)
		{
			int from, to;
			
			st = new StringTokenizer(br.readLine());
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			list.add(new Node(from ,to));
			
			indegree[to]++;
		}
		
		Collections.sort(list, (o1, o2) -> o1.from - o2.from);
		
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
			{
				dp[i] = 1;
			}
		}
		
		for(Node cur : list)
		{
			indegree[cur.to]--;
			
			dp[cur.to] = Math.max(dp[cur.to], dp[cur.from] + 1);
		}
		
//		for(int x : dp)
//		{
//			System.out.println(x + " ");
//		}
		
		for(int i = 1; i <= N; i++)
		{
			bw.write(dp[i] + " ");
		}
		bw.flush();
		bw.close();
		
	}

}

class Node{
	int from;
	int to;
	
	Node(int from, int to)
	{
		this.from = from;
		this.to = to;
	}
}
