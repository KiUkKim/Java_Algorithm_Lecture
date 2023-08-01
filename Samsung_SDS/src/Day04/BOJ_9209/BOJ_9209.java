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
		
		// 트라이를 만들어줘야한다.
		// 0번은 루트 노드이다.
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
				Node findNode = trie.rootNode; // 해당 노드를 복사해서 사용
				
				char tmp_c = boggle[i][j];
				
				score = 0;
				cnt = 0;
				tmp = new StringBuilder();
				ans_tmp = "";
				
				// 만약 해당 글자를 찾을 수 있다면 시작점
				if(findNode.child[tmp_c - 'A'] != null)
				{
					dfs(i, j, findNode, 1);
				}
			}
		}
	}
	
	static void dfs(int y, int x, Node findNode, int length)
	{
		// 체크인
		visited[y][x] = true;
		
		// 탐색하려는 노드로 들어가줘야한다.
		char tmp_c = boggle[y][x];
		
		// 그 현재 노드로 들어간다.
		findNode = findNode.child[tmp_c - 'A'];
		
		// 목적지인가? // 현재 tri에서 멈춘곳이 끝이라면 -> 정답이다.
		if(findNode != null && findNode.isLastWord)
		{
			score += get_score(length);
			
			cnt++;
			
			if(ans_tmp.length() < sb.toString().length())
			{
				ans_tmp = sb.toString();
			}
		}
		
		// 연결된곳을 순외
		for(int i = 0; i < 8; i++)
		{
			int ny = y + dx[i];
			int nx = x + dx[i];
			
			// 맵 영역
			if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4)
				continue;
			
			// 방문 체크
			if(visited[ny][nx])
				continue;
			
			// 다음으로 가는 구간의 글자를 가지고 있는가?
			Node cur = findNode.child[tmp_c];
			
			char next_char = boggle[ny][nx];
			
			// 갈 수 있는가
			// 그 글자를 가지고 있는가
			if(cur.child[next_char] != null)
			{
				// 간다.
				dfs(ny, nx, cur, length + 1);
			}
		}
		
		// 체크아웃
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
	
	// 글자 삽입
	public void insert(String str)
	{
		Node curNode = this.rootNode; // 루트노드부터시작
		
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			
			int num = c - 'A';
			
			if(curNode.child[num] != null) // 만약 해당 자식의 구간이 없다면
			{
				curNode.child[num] = new Node(); // 자식을 생성해준다.
				
				curNode.child[num].data = c; // 해당 곳에 데이터를 넣어준다.
			}
			
			curNode = curNode.child[num]; // 다음 노드로 이동
		}
		
		// 이제 끝났다면 끝임을 알려준다.
		curNode.isLastWord = true;
	}
}

// 노드의 정보
class Node{
	Node[] child = new Node[26]; // 해당 노드의 자식이 담길곳
	boolean isLastWord = false;
	boolean isCheckWord = false; // 한번이라도 완성된 글자인지
	char data; // 현재 노드에서 참고하고 있는 단어
}

