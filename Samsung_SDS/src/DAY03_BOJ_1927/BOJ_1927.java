package DAY03_BOJ_1927;

import java.io.*;
import java.util.*;

public class BOJ_1927 {

	static int idx;
	static int N;
	static int[] heapArr;
	
	public static void main(String[] args) throws IOException{

		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\DAY03_BOJ_1927\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		// 0은 최소 힙 출력후 제거
		// 나머지 정수 -> 힙에 넣음
		N = Integer.parseInt(st.nextToken());
		
		idx = 0; // 현재 노드가 들어가있는 마지막곳을 확인한다.
		
		while(N-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd_ = Integer.parseInt(st.nextToken());
			
			// 만약 출력해야되는 경우
			if(cmd_ == 0)
			{
				// 0번째에 들어가있으면 아무것도 없는 경우
				if(idx == 0)
				{
					sb.append(0 + "\n");
				}
				
				// 루트노드 삭제 연산을 행해준다.
				else {
					sb.append(deleteRoot() + "\n");
				}
			}
			
			// 최소힙의 조건을 만족하도록 삽입 연산을 시작한다.
			else {
				insertRoot(cmd_);
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void insertRoot(int x)
	{
		// 트리의 마지막에 계속해서 삽입해주어야한다.
		
		
		// 먼저 트리를 삽입해준다.
		
		// 아무것도 없을때
		if(idx == 0)
		{
			idx = 1;
			heapArr[idx] = x;
		}
		
		// 이미 한개이상 들어온경우
		else {
			
			// 마지막 루트에 넣어주는데, 해당 부모와 비교를 해주어야한다.
			heapArr[++idx] = x;
			
			int tmp_idx = idx;
			// root 노드를 만날때까지 확인해준다.
			while(tmp_idx > 0 && heapArr[tmp_idx] < heapArr[tmp_idx / 2])
			{
				int parent_node = heapArr[tmp_idx / 2];
				
				// 넣은 것보다 부모가 클 경우
				if(parent_node > heapArr[tmp_idx])
				{
					int tmp = heapArr[tmp_idx];
					heapArr[tmp_idx] = parent_node;
					heapArr[tmp_idx / 2] = tmp;
				}
				
				tmp_idx /= 2;
			}
		}
		
	}
	
	static int deleteRoot()
	{
		// 가장 먼저 루트의 노드를 뺀다.
		int min_heap_node = heapArr[1];
		
		// 루트 노드에 가장 마지막 노드를 넣어준다.
		heapArr[1] = heapArr[idx];
		
		heapArr[idx] = 0;
		
		System.out.println("delete");
		
		idx--;
		
		for(int i = 1; i <= idx; i++)
		{
			System.out.print(heapArr[i] + " ");
		}
		System.out.println();
		
		
		// 만약 idx가 1이상이라면 루트에 최소값이 남도록 해주어야한다.
		int tmp_idx = 1;
		
		// 루트노드에서 리프노드까지 이어져야한다.
		while(tmp_idx <= idx && idx > 0)
		{
			int left_node = heapArr[tmp_idx * 2];
			
			if(left_node == 0)
			{
				break;
			}
			
			int right_node = heapArr[tmp_idx * 2 + 1];
			
			// left_node가 더 작으므로 left_node로 탐색
			if(left_node < right_node)
			{
				if(left_node < heapArr[tmp_idx])
				{
					int tmp = heapArr[tmp_idx];
					heapArr[tmp_idx] = left_node;
					heapArr[tmp_idx * 2] = tmp;
				}
				
				tmp_idx = (tmp_idx * 2);
			}
			
			else {
				if(right_node < heapArr[tmp_idx])
				{
					int tmp = heapArr[tmp_idx];
					heapArr[tmp_idx] = left_node;
					heapArr[tmp_idx * 2] = tmp;
				}
				
				tmp_idx = (tmp_idx * 2) + 1;
			}
		}
		
		return min_heap_node;
	}

}
