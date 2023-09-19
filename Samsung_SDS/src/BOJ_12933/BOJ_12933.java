package BOJ_12933;

import java.util.*;
import java.io.*;

public class BOJ_12933 {

	
	static boolean[] visited;
	static char[] check = {'q', 'u', 'a', 'c', 'k' };
	static List<Character> checkNum;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		
		if(str.charAt(0) != 'q' || str.length() %5 != 0)
		{
			System.out.println(-1);
			return;
		}
			
		visited = new boolean[str.length() + 1];
		
		for(int i = 0; i < str.length(); i++)
		{
			int idx = 0;
						
			checkNum = new ArrayList<>();
			
			for(int j = i; j < str.length(); j++)
			{
				if(!visited[j] && str.charAt(j) == check[idx])
				{
					idx++;
					visited[j] = true;
					checkNum.add(str.charAt(j));
					
					if(idx == 5)
					{
						idx = 0;
					}
				}
			}
			
			if(checkNum.size() != 0)
			{
				if(checkNum.get(checkNum.size() - 1) != 'k')
				{
					System.out.println(-1);
					return;
				}
				
				ans++;
			}

		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}

}
