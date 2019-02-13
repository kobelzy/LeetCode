package arithmetic;

/**
 * 并查集，路径压缩
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2019-01-10 09:25
 **/
public class QuickUnionUF {
    private int[] roots;

    /**
     * 初始化，每个节点指向自己
     * @param N
     */
    public QuickUnionUF(int N){
        roots=new int[N];
        for(int i=0;i<N;i++){
            roots[i]=i;
        }
    }

    private int findRoot(int i){
        int root =i;
        while(root !=roots[root]) root =roots[root];
        while (i!=roots[i]){
            int tmp =roots[i];
            roots[i]=root;
            i=tmp;
        }
            return root;
    }

    public boolean connected(int p,int q){
        return findRoot(p)==findRoot(q);
    }

    public void union(int p,int q ){
        int qroot=findRoot(q);
        int proot=findRoot(p);
        roots[proot]=qroot;
    }

}
