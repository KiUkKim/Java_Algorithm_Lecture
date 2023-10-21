package BOJ_6588;

import java.io.*;
import java.util.*;

public class BOJ_6588 {

	static final int MAX = 1_000_000;
	static int sum;
	static List<Integer> prime;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		prime = new ArrayList<>();
		
		check = new boolean[MAX + 1];
		
		check_prime();
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0)
				break;
			
			int max_sum = -Integer.MAX_VALUE;
			
			int ans_a = -1;
			int ans_b = -1;
			
			for(int i = 0; i < prime.size(); i++)
			{
				int sum = 0;
				
				int a = prime.get(i);
				int b = num - a;
				
				if(a % 2 == 0 || a >= num)
					continue;
				
				// 범위를 넘지않고, 해당 뒤의 숫자 b가 소수일 경우
				if(b >= 0 && !check[b])
				{
					if(max_sum < (b - a) )
					{
						max_sum = (b-a);
						
						ans_a = a;
						ans_b = b;
						
						break;
					}
				}
			}
			
			if(ans_a != -1 && ans_b != -1)
			{
				System.out.println(num + " = " + ans_a + " + " + ans_b);
			}
			
			else {
				System.out.println("Goldbach's conjecture is wrong.");
			}
		}
		
		
	}

	static void check_prime()
	{
		check[0] = check[1] = true;
		
		for(int i = 2; i * i <= MAX; i++)
		{
			if(!check[i])
			{
				prime.add(i);
				for(int j = i * i; j <= MAX; j += i)
				{
					check[j] = true;
				}
			}
		}
	}
}
