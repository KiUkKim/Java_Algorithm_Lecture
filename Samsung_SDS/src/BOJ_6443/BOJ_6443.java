package BOJ_6443;

import java.util.*;
import java.io.*;

public class BOJ_6443 {

	static final int MAX = 100_001;

	static char[] arr;
	static int N;
	static Set<String> set;
	static StringBuilder sb;
	static int[] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		set = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2)
			{
				if(o1.length() == o2.length())
				{
					for(int i = 0; i < o1.length(); i++)
					{
						if(o1.charAt(i) != o2.charAt(i))
							return (int)o1.charAt(i) - (int)o2.charAt(i);
					}
					
					return 0;
				}
				
				else {
					return o1.length() - o2.length();
				}
				
			}
		});
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String tmp = st.nextToken();
			
			arr = tmp.toCharArray();
			
			visited = new int[26];
			
			sb = new StringBuilder();
			
			for(char c : arr)
			{
				visited[c - 'a']++;
			}
			
			dfs(tmp, 0);
		}
		
		for(String s : set)
		{
			System.out.println(s);
		}
		
	}
	
	static void dfs(String str, int cnt)
	{
		if(cnt == str.length())
		{
			set.add(sb.toString());
			
			return;
		}
		
		for(int i = 0; i < 26; i++)
		{
			if(visited[i] > 0)
			{
				visited[i]--;
				sb.append((char) (i + 'a'));
				dfs(str, cnt + 1);
				sb.deleteCharAt(sb.length() - 1);
				visited[i]++;
			}
		}
		
		return;
	}

}
