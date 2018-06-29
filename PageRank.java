public class PageRank {

    /**
     * 矩阵g乘以矩阵p

     * @param g
     * @param p
     * @return 矩阵g乘以矩阵p的结果矩阵
     */
    private static double[] multiMatrix(double[][] g, double[] p){
        double[] multiResult = new double[p.length];
        for(int i=0; i<g.length; i++){
            double rowResult = 0.0f;
            for(int j=0; j<g.length; j++){
                rowResult+=g[i][j]*p[j];
            }
            multiResult[i] = rowResult;
        }
        return multiResult;
    }

    /**
     * 根据初始矩阵计算真正的Google矩阵
     * @param 初始矩阵
     * @param weight
     * @param oneMatrix
     * @return 真正的Google矩阵
     */
    private static void getGoogleMatrix(double[][] transitionMatrix, double weight){

        //transitionMatrix*weight
        for(int i=0; i<transitionMatrix.length; i++){
            for(int j=0; j<transitionMatrix.length; j++){
                transitionMatrix[i][j] *= weight;
                transitionMatrix[i][j] += (1-weight)/transitionMatrix.length;
            }
        }
    }

    /**
     * 如果pageRankN=pageRankN_1,返回true；否则，返回false

     * @param pageRankN
     * @param pageRankN_1
     * @return
     */
    private static boolean compareMatrix(double[] pageRankN, double[] pageRankN_1){
        for(int i=0; i<pageRankN.length; i++){
            if(pageRankN[i]-pageRankN_1[i]>0.0000001){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[][] transitionMatrix={{0,0,0,0,1/5f},{1/3f,0,0,1,1/5f},{1/3f,0,0,0,1/5f},{0,1,1,0,1/5f},{1/3f,0,0,0,1/5f}};//初始矩阵
        double[] p={1,1,1,1,1};
        double weight = 0.85f; //a的值


        //真正的Google矩阵
        getGoogleMatrix(transitionMatrix, weight);

        //输出看一下
//        for(int i=0; i<transitionMatrix.length; i++){
//            for(int j=0; j<transitionMatrix.length; j++){
//              System.out.print(transitionMatrix[i][j]);
//              System.out.print("  ");
//            }
//            System.out.println();
//        }

        //q（n)=G*q(n-1),如果q(n)=q(n-1),q(n)是PageRank
        double[] pageRank = multiMatrix(transitionMatrix, p);
        while(!compareMatrix(pageRank, p)){
            p = pageRank;
            pageRank = multiMatrix(transitionMatrix, p);
        }

        for(int i=0; i<pageRank.length; i++){
            System.out.println(pageRank[i]);
        }
    }

}