package Day05.BOJ_11050;

import java.util.*;
import java.io.*;

public class BOJ_11050_ {
	
	static int N, K;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		
		int N_fact = fact(N);
		
		int n_mi_fact = fact(N-K);
		
		int K_fact = fact(K);
		
		System.out.println(N_fact / (n_mi_fact * K_fact));
	}
	
	static int fact(int n)
	{
		if(n == 0)
			return 1;
		
		return n * fact(n - 1);
	}

}
