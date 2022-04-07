package CS61B.Lec_2a_SLList;

//SLList is a list of integers, which hides the terrible truth of the nakedness within//
public class SLList
{
    //private IntNode first;
    private IntNode sentinel;
    private int size;

    public SLList()
    {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    public SLList(int x)
    {
        //first = new IntNode(x, null);
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    // nested class and can make it private
    private static class IntNode
    {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n)
        {
            item = i;
            next = n;
        }
    }

    // adds x to the front of the list
    public void addFirst(int x)
    {
        //first = new IntNode(x, first);
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    // adds x to the end of the list
    public void addLast(int x)
    {
        size += 1;
        //IntNode p = first;
        IntNode p = sentinel;
        while (p.next != null)
        {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    // recursively call and compute size
//    private static int size(IntNode p)
//    {
//        if (p.next == null)
//        {
//            return 1;
//        }
//        return 1 + size(p.next);
//    }

    // Improvement #5 fast size
    public int size()
    {
        return size;
    }


    // return the first item in the list
    public int getFirst()
    {
        //return first.item;
        return sentinel.next.item;
    }

    public static void main(String[] args)
    {
        SLList L = new SLList();
        L.addFirst(10);
        L.addFirst(50);
        System.out.println(L.getFirst());
        System.out.println(L.size());
    }

}
