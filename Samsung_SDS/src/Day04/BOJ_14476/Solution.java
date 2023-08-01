package Day04.BOJ_14476;

import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[] nums;
	static int[] gcdLtoR;
	static int[] gcdRtoL;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_14476\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		gcdLtoR = new int[N];
		gcdRtoL = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		gcdLtoR[0] = nums[0];
		for(int i = 1; i < N; i++)
		{
			gcdLtoR[i] = gcd(gcdLtoR[i-1], nums[i]);
		}
		
		gcdRtoL[N-1] = nums[N-1];
		for(int i = N - 2; i >= 0; i--)
		{
			gcdRtoL[i] = gcd(gcdRtoL[i+1], nums[i]);
		}
		
		int max = 0;
		int maxIndex = 0;
		
		for(int k = 0; k < N; k++)
		{
			int gcd = 0;
			
			if(k == 0)
			{
				gcd = gcdRtoL[1];
			}
			else if(k == N - 1)
			{
				gcd = gcdLtoR[N - 2];
			}
			
			else {
				gcd = gcd(gcdLtoR[k - 1], gcdRtoL[k + 1]);
			}
			
			if(max < gcd && nums[k] % gcd != 0)
			{
				max = gcd;
				maxIndex = nums[k] n;
			}
		}
		
		if(max == 0)
		{
			System.out.println(-1);
		}
		else {
			System.out.println(max + " " + maxIndex);
		}
	}

	static int gcd(int a, int b)
	{
		while(b != 0)
		{
			int r = a % b;
			a = b;
			b = r;
		}
		
		return a;
	}
}
