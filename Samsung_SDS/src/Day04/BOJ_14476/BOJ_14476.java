package Day04.BOJ_14476;

import java.util.*;
import java.io.*;

public class BOJ_14476 {

	static int N;
	
	static int[] num;
	static int[] LR;
	static int[] RL;
	static int ans, maxN;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_14476\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		num = new int[N+2];
		
		for(int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// LR 
		LR = new int[N+2];
		
		for(int i=1; i<=N; i++) {
			LR[i] = gcd(num[i], LR[i-1]);
		}
		
		
		RL = new int[N+2];
		
		for(int i=N; i>0; i--) {
			
			RL[i] = gcd(num[i], RL[i+1]);
		}
		
		ans = -1;
		maxN = -1;
		
		for(int i = 1; i <= N; i++)
		{
			int cur = gcd(LR[i-1], RL[i+1]);
			
			if(cur > maxN)
			{
				if(num[i] % cur != 0)
				{
					maxN = cur;
					ans = num[i];
				}
			}
		}
		
		if(ans == -1)
		{
			System.out.println(-1);
		}
		else {
			System.out.println(maxN + " " + ans);
		}
		
	}
	
	static int gcd(int a, int b)
	{
		if(b == 0)
		{
			return a;
		}
		else {
			return gcd(b, a%b);
		}
	}

}
