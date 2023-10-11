package BOJ_9342;

import java.util.*;
import java.io.*;

public class BOJ_9342 {

	static int N;
	static char[] Check = {'A', 'F', 'C', 'D', 'E', 'B'};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String tmp = st.nextToken();
			
			boolean flag = true;
			
			int check_idx = 0;
			
			for(int j = 0; j < tmp.length(); j++)
			{
				if(check_idx >= 3)
				{
					if(j <= tmp.length() - 2)
						flag = false;
					
					for(int k = 0; k < Check.length; k++)
					{
						if(tmp.charAt(j) == Check[k])
						{
							break;
						}
					}
					
					flag = false;
					
					break;
				}
				
				if(Check[check_idx] != tmp.charAt(j))
				{
					if(j == 0)
						continue;
					
					flag = false;
					break;
				}
				
				else {
					// 만약 다음이 끝이라면
					if(j + 1 == tmp.length())
					{
						break;
					}
					
					if(Check[check_idx] != tmp.charAt(j + 1))
						check_idx++;
				}
			}
			
			if(flag)
			{
				bw.write("Infected!\n");
			}
			
			else {
				bw.write("Good\n");
			}
		}
		bw.flush();
		bw.close();

	}

}
