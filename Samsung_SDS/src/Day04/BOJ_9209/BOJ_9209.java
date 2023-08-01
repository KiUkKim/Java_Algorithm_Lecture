package Day04.BOJ_9209;

import java.util.*;
import java.io.*;

public class BOJ_9209 {

	static int W;
	static final int MAX = 300001;
	static Trie trie;
	
	static int dy[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int dx[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	static char[][] boggle;
	static StringBuilder sb;
	static int score, cnt;
	static String ans_tmp;
	static StringBuilder tmp;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_9209\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		
		// Ʈ���̸� ���������Ѵ�.
		// 0���� ��Ʈ ����̴�.
		trie = new Trie();
		
		for(int i = 0; i < W; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			trie.insert(st.nextToken());
		}
		
		boggle = new char[4][4];
		
		visited = new boolean[4][4];
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				Node findNode = trie.rootNode; // �ش� ��带 �����ؼ� ���
				
				char tmp_c = boggle[i][j];
				
				score = 0;
				cnt = 0;
				tmp = new StringBuilder();
				ans_tmp = "";
				
				// ���� �ش� ���ڸ� ã�� �� �ִٸ� ������
				if(findNode.child[tmp_c - 'A'] != null)
				{
					dfs(i, j, findNode, 1);
				}
			}
		}
	}
	
	static void dfs(int y, int x, Node findNode, int length)
	{
		// üũ��
		visited[y][x] = true;
		
		// Ž���Ϸ��� ���� ������Ѵ�.
		char tmp_c = boggle[y][x];
		
		// �� ���� ���� ����.
		findNode = findNode.child[tmp_c - 'A'];
		
		// �������ΰ�? // ���� tri���� ������� ���̶�� -> �����̴�.
		if(findNode != null && findNode.isLastWord)
		{
			score += get_score(length);
			
			cnt++;
			
			if(ans_tmp.length() < sb.toString().length())
			{
				ans_tmp = sb.toString();
			}
		}
		
		// ����Ȱ��� ����
		for(int i = 0; i < 8; i++)
		{
			int ny = y + dx[i];
			int nx = x + dx[i];
			
			// �� ����
			if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4)
				continue;
			
			// �湮 üũ
			if(visited[ny][nx])
				continue;
			
			// �������� ���� ������ ���ڸ� ������ �ִ°�?
			Node cur = findNode.child[tmp_c];
			
			char next_char = boggle[ny][nx];
			
			// �� �� �ִ°�
			// �� ���ڸ� ������ �ִ°�
			if(cur.child[next_char] != null)
			{
				// ����.
				dfs(ny, nx, cur, length + 1);
			}
		}
		
		// üũ�ƿ�
		visited[y][x] = false;
	}
	
	static int get_score(int length)
	{
		int score = 0;
		switch(length)
		{
			case 1:
			case 2:
				score = 0;
				break;
			case 3:
			case 4:
				score = 1;
				break;
			case 5:
				score = 2;
				break;
			case 6:
				score = 3;
				break;
			case 7:
				score = 5;
				break;
			case 8:
				score = 11;
				break;
		}
		
		return score;
	}
	
}

class Trie{
	
	Node rootNode;
	
	public Trie()
	{
		rootNode = new Node();
		
		this.rootNode.data = ' ';
	}
	
	// ���� ����
	public void insert(String str)
	{
		Node curNode = this.rootNode; // ��Ʈ�����ͽ���
		
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			
			int num = c - 'A';
			
			if(curNode.child[num] != null) // ���� �ش� �ڽ��� ������ ���ٸ�
			{
				curNode.child[num] = new Node(); // �ڽ��� �������ش�.
				
				curNode.child[num].data = c; // �ش� ���� �����͸� �־��ش�.
			}
			
			curNode = curNode.child[num]; // ���� ���� �̵�
		}
		
		// ���� �����ٸ� ������ �˷��ش�.
		curNode.isLastWord = true;
	}
}

// ����� ����
class Node{
	Node[] child = new Node[26]; // �ش� ����� �ڽ��� ����
	boolean isLastWord = false;
	boolean isCheckWord = false; // �ѹ��̶� �ϼ��� ��������
	char data; // ���� ��忡�� �����ϰ� �ִ� �ܾ�
}

