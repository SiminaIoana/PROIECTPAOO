package AI;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

//
public class FindingPath {

    GamePanel gp;
    Node [][] node;
    ArrayList<Node> openList= new ArrayList<>();
    public ArrayList<Node> pathList= new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step=0;

    public FindingPath(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();

    }

    //initializează matricea de noduri. Parcurge toate celulele și creează un nod pentru fiecare poziție.
    public void instantiateNodes(){
        node=new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col=0;
        int row=0;

        while(col < gp.maxWorldCol && row<gp.maxWorldRow) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }


    //Resetează stările nodurilor și listele pentru o nouă căutare. Resetează și flag-ul goalReached și contorul step.
    public void resetNodes(){
        int col=0;
        int row=0;

        while(col < gp.maxWorldCol && row<gp.maxWorldRow) {
            //Reset open, checked and solid state
            node[col][row].open=false;
            node[col][row].checked=false;
            node[col][row].solid=false;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        //Reset other settings
        openList.clear();
        pathList.clear();
        goalReached=false;
        step=0;
    }

    // Setează nodurile de start și final și adaugă nodul de start în lista deschisă. Parcurge toate celulele pentru a seta nodurile solide și a calcula costurile G, H și F.
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();

        //set start anf goal node
        startNode=node[startCol][startRow];
        currentNode=startNode;
        goalNode=node[goalCol][goalRow];
        openList.add(currentNode);

        int col=0;
        int row=0;

        while(col < gp.maxWorldCol && row<gp.maxWorldRow) {
            //SET SOLID NODE
            //CHECK TILES
            int tileNum=gp.tileManager.mapTileNum[gp.currentMap][col][row];
            if(gp.tileManager.tile[tileNum].collision==true){
                node[col][row].solid=true;
            }

            //SET COST
            getCost(node[col][row]);
            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

   // Calculează costurile G (distanța de la nodul de start), H (distanța de la nodul țintă) și F (suma dintre G și H).
    public void getCost(Node node){
        //G cost
        int xDistance= Math.abs(node.col - startNode.col);
        int yDistance=Math.abs(node.row - startNode.row);
        node.gCost=xDistance+yDistance;
        //H cost
        xDistance= Math.abs(node.col - goalNode.col);
        yDistance=Math.abs(node.row - goalNode.row);
        node.hCost=xDistance+yDistance;
        //F cost
        node.fCost=node.gCost+ node.hCost;
    }


//Caută drumul de la nodul de start la nodul țintă folosind o abordare de tip A*. Parcurge nodurile din lista deschisă,
// deschide nodurile vecine și selectează nodul cu cel mai mic cost F pentru a continua căutarea.
    public boolean search() {
        while (goalReached == false && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            //Check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //Open the Up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            //Open the left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            //Open the down node
            if (row + 1 < gp.maxWorldRow) {
                openNode(node[col][row + 1]);
            }
            //Open the right node
            if (col + 1 < gp.maxWorldCol) {
                openNode(node[col + 1][row]);
            }

            //Find the best node
            int bestNodeIndex=0;
            int bestNodeCost=999;

            for(int i=0; i<openList.size(); i++){
                //check if the node's F is better
                if(openList.get(i).fCost < bestNodeCost){
                    bestNodeIndex=i;
                    bestNodeCost=openList.get(i).fCost;
                }
                //if F is equal, check the G cost
                else if(openList.get(i).fCost == bestNodeCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;
                    }
                }
            }
            //if there is no node in the openList, end the loop
            if(openList.size()==0){
                break;
            }
            //after the loop, openList[bestNodeIndex] is the next steo(=currentNode)
            currentNode=openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached=true;
                trackThePath();
            }
            step++;

        }
        return goalReached;
    }


    //Deschide un nod (dacă nu este deja deschis, verificat sau solid), setează părintele nodului curent și adaugă nodul în lista deschisă.
    public void openNode(Node node){
        if(node.open== false && node.checked == false && node.solid==false){
            node.open=true;
            node.parent=currentNode;
            openList.add(node);
        }
    }

    //econstruiește calea de la nodul țintă la nodul de start urmărind părinții fiecărui nod și adăugându-le în pathList.
    public void trackThePath(){
        Node current=goalNode;

        while(current!=startNode){
            pathList.add(0,current);
            current=current.parent;
        }
    }
}
