package Day10.BOJ_7578;

import java.util.*;
import java.io.*;

public class BOJ_7578 {

	static int N;
	static int[] machine;
	static Map<Integer, Integer> machineBNum; // B의 머신이 어딨는지 관리하기 위함
	static int S = 1;
	static int[] tree;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		machineBNum = new HashMap<>();
		
		while(S < N)
		{
			S *= 2;
		}

		machine = new int[N + 1];
		
		tree = new int[S * 2 + 1];
				
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i ++)
		{
			int a = Integer.parseInt(st.nextToken());
			
			machine[i] = a;
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			int a = Integer.parseInt(st.nextToken());
			
			// 이 번호를 통해서 machine에 넣을것이다.
			machineBNum.put(a, i);
		}
		
		// tree에 넣을떄는 machineBNum[machineNum] + S가 될것이다.
		
		// 이제 해당 정보를 업데이트와 쿼리를 통해서 어느정도 있는지 봐야한다.
		for(int i = 0; i < N; i++)
		{
			// 이 번호가 B에서 현재 어느정도 꼬여있는지 파악할 수 있다.
			int machineNum = machineBNum.get(machine[i]) + 1;
			
			// 업데이트를 시켜준다.
			update(1, S, 1, machineNum, 1);
			
			// 우리는 이제 해당 index + 1 ~ 끝까지 찾아야한다. 
			ans += query(1, S, 1, machineNum + 1, S);
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}
	
	static void update(int left, int right, int node, int target, int diff)
	{
		// 해당 사항 없는 구간
		if(right < target ||  target < left)
		{
			return;
		}
		else {
			// 해당 트리를 업데이트 시켜준다.
			tree[node] += diff;
			
			// 만약 업데이트 할 영역이 남아있다면
			if(left != right)
			{
				int mid = (left + right) / 2;
				
				// 왼쪽자식 업데이트
				update(left, mid, node * 2, target, diff);
				
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	// 최대는 10^5 * 10^5을 가진다.
	static long query(int left, int right, int node, int queryLeft, int queryRight)
	{
		if(queryRight < left || right < queryLeft)
		{
			return 0;
		}
		else if(queryLeft <= left && queryRight >= right)
		{
			return tree[node];
		}
		
		else {
			// 이 경우 유효하지 않은 구간이 있다.
			// 확인해주자
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
