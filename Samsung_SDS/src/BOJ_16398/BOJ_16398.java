package BOJ_16398;

import java.util.*;
import java.io.*;

public class BOJ_16398 {

	static int N;
	static List<City> city;
	static int[] parent;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		
		parent = new int[N + 1];
		
		Arrays.fill(parent, -1);
		
		city = new ArrayList<>();
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++)
			{
				int value = Integer.parseInt(st.nextToken());
				
				if(i == j)
					continue;
				
				city.add(new City(i, j, value));
			}
		}
		
		Collections.sort(city, new Comparator<City>() {
			@Override
			public int compare(City o1, City o2)
			{
				return o1.weight - o2.weight;
			}
		});
		
		for(int i = 0; i < city.size(); i++)
		{
			City cur = city.get(i);
			
			if(find(cur.from) == find(cur.to))
				continue;
			
			union(cur.from, cur.to);
			
			ans += (long)cur.weight;
			
			
			if(parent[find(cur.to)] == -N)
				break;
		}
		
		bw.write(ans + " ");
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
		{
			return a;
		}
		
		return parent[a] = find(parent[a]);
	}
}

class City{
	int from;
	int to;
	int weight;
	
	City(int from, int to, int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

}
