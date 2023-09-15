package BOJ_14621;

import java.util.*;
import java.io.*;

public class BOJ_14621 {

	static int N, M;
	static int ans;
	static List<School> list;
	static char[] SchoolGender;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		SchoolGender = new char[N + 1];
		
		list = new ArrayList<>();
		
		parent = new int[N + 1];
		
		Arrays.fill(parent, -1);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++)
		{
			SchoolGender[i] = st.nextToken().charAt(0);
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			
			int to = Integer.parseInt(st.nextToken());
			
			int weight = Integer.parseInt(st.nextToken());
			
			list.add(new School(from, to, weight));
		}
		
		Collections.sort(list, (o1, o2) -> o1.weight - o2.weight);
		
		boolean flag = false;
		
		for(School cur : list)
		{
			if(SchoolGender[cur.from] == SchoolGender[cur.to])
				continue;
			
			int fa = find(cur.from);
			
			int fb = find(cur.to);
			
			if(fa != fb)
			{
				union(cur.from, cur.to);
				
				ans += cur.weight;
				
				if(parent[fb] == -N)
				{
					flag = true;
					break;
				}
			}
		}
				
		if(!flag)
			ans = -1;
		
		bw.write(ans + " ");
		bw.newLine();
		bw.flush();
		bw.close();
		
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		if(a != b)
		{
			parent[b] += parent[a];
			
			parent[a] = b;
		}
	}
	
	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}

}

class School{
	int from;
	int to;
	int weight;
	
	School(int from, int to, int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}