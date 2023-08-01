package Day05.BOJ_11050;

import java.util.*;
import java.io.*;

public class BOJ_11050_2 {

	static int N, K;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(combi(N, K));
	}
	
	static int combi(int n, int k)
	{
		if(k == 0 || n == k)
			return 1;
		
		return combi(n-1, k-1) + combi(n-1, k);
	}

}
