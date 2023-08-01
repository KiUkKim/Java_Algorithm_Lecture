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
		
		// 0�� �ּ� �� ����� ����
		// ������ ���� -> ���� ����
		N = Integer.parseInt(st.nextToken());
		
		idx = 0; // ���� ��尡 ���ִ� ���������� Ȯ���Ѵ�.
		
		while(N-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd_ = Integer.parseInt(st.nextToken());
			
			// ���� ����ؾߵǴ� ���
			if(cmd_ == 0)
			{
				// 0��°�� �������� �ƹ��͵� ���� ���
				if(idx == 0)
				{
					sb.append(0 + "\n");
				}
				
				// ��Ʈ��� ���� ������ �����ش�.
				else {
					sb.append(deleteRoot() + "\n");
				}
			}
			
			// �ּ����� ������ �����ϵ��� ���� ������ �����Ѵ�.
			else {
				insertRoot(cmd_);
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void insertRoot(int x)
	{
		// Ʈ���� �������� ����ؼ� �������־���Ѵ�.
		
		
		// ���� Ʈ���� �������ش�.
		
		// �ƹ��͵� ������
		if(idx == 0)
		{
			idx = 1;
			heapArr[idx] = x;
		}
		
		// �̹� �Ѱ��̻� ���°��
		else {
			
			// ������ ��Ʈ�� �־��ִµ�, �ش� �θ�� �񱳸� ���־���Ѵ�.
			heapArr[++idx] = x;
			
			int tmp_idx = idx;
			// root ��带 ���������� Ȯ�����ش�.
			while(tmp_idx > 0 && heapArr[tmp_idx] < heapArr[tmp_idx / 2])
			{
				int parent_node = heapArr[tmp_idx / 2];
				
				// ���� �ͺ��� �θ� Ŭ ���
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
		// ���� ���� ��Ʈ�� ��带 ����.
		int min_heap_node = heapArr[1];
		
		// ��Ʈ ��忡 ���� ������ ��带 �־��ش�.
		heapArr[1] = heapArr[idx];
		
		heapArr[idx] = 0;
		
		System.out.println("delete");
		
		idx--;
		
		for(int i = 1; i <= idx; i++)
		{
			System.out.print(heapArr[i] + " ");
		}
		System.out.println();
		
		
		// ���� idx�� 1�̻��̶�� ��Ʈ�� �ּҰ��� ������ ���־���Ѵ�.
		int tmp_idx = 1;
		
		// ��Ʈ��忡�� ���������� �̾������Ѵ�.
		while(tmp_idx <= idx && idx > 0)
		{
			int left_node = heapArr[tmp_idx * 2];
			
			if(left_node == 0)
			{
				break;
			}
			
			int right_node = heapArr[tmp_idx * 2 + 1];
			
			// left_node�� �� �����Ƿ� left_node�� Ž��
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
