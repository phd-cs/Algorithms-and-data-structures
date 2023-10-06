import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graf {
	int bigY;
	int bigX;
	int p;
	static ArrayList<Node> list;

	public Graf(String fileName) throws FileNotFoundException {
		list = new ArrayList<Node>();
		read_map(new File(fileName));
	}

	public void read_map(File mapOne) throws FileNotFoundException {
		Scanner scan = new Scanner(mapOne);
		int x;
		int y;
		int radius;
		int name;

		while (scan.hasNext()) {
			scan.next();
			name = scan.nextInt();
			scan.next();
			x = scan.nextInt();
			scan.next();
			y = scan.nextInt();
			scan.next();
			radius = scan.nextInt();

			if (x > bigX)
				bigX = x;
			if (y > bigY)
				bigY = y;
			list.add(new Node(x, y, radius, name));
		}
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (i == j) {
					//
				}
				else if (overlaps(list.get(i), list.get(j))) {
					list.get(i).neighbors.add(list.get(j));
					p++;
				}
			}
		}
	}

	public void draw_map() {
		StdDraw.setXscale(-60, bigX + 60);
		StdDraw.setYscale(-60, bigY + 60);

		for (int i = 0; i < list.size(); i++) {
			StdDraw.setPenColor(StdDraw.GREEN);
			Node node = list.get(i);
			StdDraw.circle(node.x, node.y, node.radius);
			String string = "" + node.name;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(new Font("Arial", Font.PLAIN, 10));
			StdDraw.text(node.x, node.y, string);
			StdDraw.setPenColor(StdDraw.BLUE);
			
			for (int j = 0; j < node.neighbors.size(); j++)
				StdDraw.line(node.x, node.y, node.neighbors.get(j).x, node.neighbors.get(j).y);
		}
		StdDraw.show();
	}

	public static boolean overlaps(Node node_one, Node node_two) {
		int dist = (int) Math.sqrt((Math.pow(node_two.x - node_one.x, 2) + (Math.pow(node_two.y - node_one.y, 2))));
		int min_dist = Math.max(node_two.radius, node_one.radius) - Math.min(node_two.radius, node_one.radius);

		if (dist <= (node_one.radius + node_two.radius))
			return true;
		else
			return false;
	}

	public static boolean connected(Node node_one, Node node_two) {
		boolean[] mark = new boolean[list.size()];
		Stack<Node> stack = new Stack<Node>();
		Node curr;
		stack.push(node_one);
		while (!stack.empty()) {
			curr = stack.pop();
			for (int i = 0; i < curr.neighbors.size(); i++) {
				Node node = curr.neighbors.get(i);
				if (!mark[node.name])
					stack.push(node);
				if (node.equals(node_two))
					return true;
			}
			mark[curr.name] = true;
		}
		return false;
	}

	public static int connected_net() {
		int amountNet = 0;
		Stack<Node> stack = new Stack<Node>();
		boolean[] mark = new boolean[list.size()];

		for (int i = 0; i < list.size(); i++) {
			if (!mark[list.get(i).name]) {
				Node curr;
				stack.push(list.get(i));
				if (list.get(i).neighbors.size() != 0)
					amountNet++;

				while (!stack.empty()) {
					curr = stack.pop();
					for (int j = 0; j < curr.neighbors.size(); j++) {
						Node node = curr.neighbors.get(j);
						if (!mark[node.name])
							stack.push(node);
					}
					mark[curr.name] = true;
				}
			}
		}
		return amountNet;
	}

	public static double distance(Node node_one, Node node_two) {
		double dist = Math.sqrt((Math.pow(node_two.x - node_one.x, 2) + (Math.pow(node_two.y - node_one.y, 2))));
		return dist;
	}

	public static Node farthest_node(Node node_one) {
		boolean[] mark = new boolean[list.size()]; 
		Stack<Node> stack = new Stack<Node>(); 
		Node real = node_one;
		double maxDist = 0;
		Node curr;
		stack.push(node_one);
		Node now_node = real;

		while (!stack.empty()) {
			curr = stack.pop();
			for (int i = 0; i < curr.neighbors.size(); i++) {
				Node node = curr.neighbors.get(i);
				if (!mark[node.name])
					stack.push(node);
				double now = distance(real, node);
				if (maxDist < now) {
					maxDist = now;
					now_node = node;
				}
				mark[curr.name] = true;
			}
		}
		return now_node;
	}

	public static int shortest_path(Node node_one, Node node_two) throws Exception {
		if (!connected(node_one, node_two))
			throw new Exception("Nodes not connected");

		boolean[] mark = new boolean[list.size()]; 
		int[] edgeTo = new int[list.size()]; 
		Queue<Node> queue = new ArrayDeque<Node>();
		Node curr;
		queue.add(node_one);
		mark[node_one.name] = true;

		while (!queue.isEmpty()) {
			curr = queue.remove();
			for (int i = 0; i < curr.neighbors.size(); i++) {
				Node node = curr.neighbors.get(i);
				if (!mark[node.name]) {
					edgeTo[node.name] = curr.name;
					mark[node.name] = true;
					queue.add(node);
				}
			}
		}

		int node_number = node_two.name;
		int numberOfNodes = -1;
		while (node_number != node_one.name) {
			node_number = edgeTo[node_number];
			numberOfNodes++;
		}
		return numberOfNodes;
	}

	public static void main(String[] args) throws Exception {
		Graf graph = new Graf("map1.txt");
		graph.draw_map();
		System.out.println("Does circle 15 overlap with circle 34? " + connected(list.get(15), list.get(34)));
		System.out.println(connected_net());
		System.out.println("Node: " + farthest_node(list.get(15)).name + ", " + "Distance: " + distance(list.get(15), farthest_node(list.get(15))));
		System.out.println(shortest_path(list.get(15), list.get(34)));
	}
}
