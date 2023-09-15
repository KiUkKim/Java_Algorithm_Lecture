package BOJ_2512;

import java.util.*;
import java.io.*;

public class BOJ_2512 {

	static int N;
	static int MAX, sum, ans;
	static int tmp_sum;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		list = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i ++)
		{
			list.add(Integer.parseInt(st.nextToken()));
			
			MAX = Math.max(MAX, list.get(i));
		}
		
		st = new StringTokenizer(br.readLine());
		
		sum = Integer.parseInt(st.nextToken());

		int left = 1;
		int right = MAX;
		int mid = (left + right) / 2;
		
		while(left <= right)
		{
			mid = (left + right ) / 2;
			int tmp = 0;
			
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) <= mid)
				{
					tmp += list.get(i);
				}
				
				else {
					tmp += mid;
				}
			}
			
			
			if(tmp <= sum)
			{
				if(tmp > tmp_sum)
				{
					tmp_sum = tmp;
					ans = mid;
				}
				
				left = mid + 1;
			}
			
			else {
				right = mid - 1;
			}
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
		
	}

}
