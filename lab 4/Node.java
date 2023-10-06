import java.util.ArrayList;

public class Node 
{
	int x;
	int y;
	int radius;
	int name;
	ArrayList<Node> neighbors;

	public Node(int x, int y, int radius, int Name) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.name = Name;
		neighbors = new ArrayList<Node>();
	}

	
    public int getName()
    {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    void print()
    {
        System.out.println("Name: "+name);
        System.out.println("X: "+x);
        System.out.println("Y: "+y);
        System.out.println("Radius: "+radius);
    }

}
